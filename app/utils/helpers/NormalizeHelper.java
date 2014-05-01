package utils.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Auxiliar class which defines possible useful common methods for normalizating text
 * <p>
 * Note: all methods would be static; not reason to instanciate or extend this class
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * @see Stackoverflow #1844355
 * @see Stackoverflow #6802483
 * @see Stackoverflow #5713558
 */
public final class NormalizeHelper {
	private static final String SPACE_CHAR = "\\s+";
	//FIXME: relative path
	private static final String STOP_LIST_PATH = "/Users/manutero/workspace/itafy/stop-list/stop-list.txt";
	private static final ArrayList<String> stopList;

	// Pattern for recognizing a URL, based off RFC 3986
	private static final Pattern URL_PATTERN = Pattern.compile(
			"(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)" + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*" + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

	/**
	 * Suppress default constructor for noninstantiability; "Effective Java" Item 4.
	 * @throws AssertionError
	 */
	private NormalizeHelper() {
		throw new AssertionError();
	}


	public static String normalizeVowels(String string) {
		return string
				.replace("á", "a")
				.replace("é", "e")
				.replace("í", "i")
				.replace("ó", "o")
				.replace("ú", "u")
				.replace("ә", "e")
				.replace("ε", "e")
				.replace("α", "a");
	}

	public static String normaliceAsciiChars(String string) {
		return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static String removeNonAlphabeticChars(String string) {
		return string.replaceAll("[^a-zA-Z]", " ");
	}

	/**
	 * Applies FIVE normalization functinos to each word of a text and return the normalized text
	 * <pre>
	 *  NormalizeHelper.breakDownUrls(s)
	 *  NormalizeHelper.isInStopList(s)
	 *  NormalizeHelper.normalizeVowels(s);
	 *  NormalizeHelper.normaliceAsciiChars(s);
	 *  NormalizeHelper.removeNonAlphabeticChars(s);
	 * </pre>
	 * <p>
	 * Note: we must check the stop list <strong>after</strong> removing non alphabetic chars;
	 * this, makes the algorithim slower but if the stop list is checked before the normalization,
	 * there will be <em>ninja</em> words.
	 * 
	 * @param text to be normalized
	 * @return normalized text
	 */
	public static String normalizeText(String text) {
		text = breakDownUrls(text);
		String response = "";
		String normalizedWord = "";
		for (String word : text.split(SPACE_CHAR)) {
			normalizedWord = NormalizeHelper.normalizeVowels(word);
			normalizedWord = NormalizeHelper.normaliceAsciiChars(normalizedWord);
			normalizedWord = NormalizeHelper.removeNonAlphabeticChars(normalizedWord);
			if (hasSemanticWeight(normalizedWord)) {
				// there will be an extra space at the end
				response += normalizedWord + " ";
			}
		}
		return removeLastChar(response);
	}

	/**
	 * Note: this function does NOT keep order in the String.
	 * @example breakDownUrls("this is a url http://www.themostamazingsiteontheinternet.com amazing")
	 * returns "this is a url amazing http www themostamazingsiteontheinternet com"
	 * @param text
	 * @return
	 */
	public static String breakDownUrls(String text) {
		ArrayList<String> urlsInTheText = removeUrlsFromText(text);
		String response = text;
		for (String url : urlsInTheText) {
			response += NormalizeHelper.removeNonAlphabeticChars(url);
		}
		return response;
	}

	/**
	 * @example <code>removeUrlsFromText("this is a url  amazing")</code>
	 * returns <code>[http://www.themostamazingsiteontheinternet.com]</code> and change the input argument
	 * to <code>"this is a url amazing"</code>
	 * @param text <strong>in/out</strong> argument
	 * @return
	 */
	public static ArrayList<String> removeUrlsFromText(String text) {
		Matcher matcher = URL_PATTERN.matcher(text);
		ArrayList<String> response = new ArrayList<String>();
		while (matcher.find()) {
			int matchStart = matcher.start(1);
			int matchEnd = matcher.end();
			response.add(text.substring(matchStart, matchEnd));
			text = removeSubString(text, matchStart, matchEnd);
		}
		return response;
	}

	private static String removeSubString(String text, int startIndex, int endIndex) {
		StringBuffer response = new StringBuffer(text);
		response.replace(startIndex, endIndex ,"");
		return response.toString();
	}

	/** 3 tests
	 * <ol>
	 *  <li> is this word in the stop list?
	 *  <li> is this word more than a single char?
	 *  <li> is this word more than just white spaces?
	 */
	public static boolean hasSemanticWeight(String word) {
		return !isInStopList(word) && (word.length() > 1) && (word.trim().length() > 0);
	}

	/** Is this word included in the stop list? */
	private static boolean isInStopList(String word) {
		return stopList.contains(word.toLowerCase());
	}

	/**
	 * @example <code>removeLastChar("I have a dogs")</code> returns <code>"I have a dog"</code>
	 * @param text
	 * @return text withiout last char
	 */
	private static String removeLastChar(String text) {
		String response = "";
		if ((text != null) && !text.isEmpty()){
			response = text.substring(0, text.length() - 1);
		}
		return response;
	}

	//stop list static initialization from stop-list/stop-list.txt
	static {
		stopList = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(STOP_LIST_PATH));
			String line;
			while ((line = br.readLine()) != null) {
				stopList.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
