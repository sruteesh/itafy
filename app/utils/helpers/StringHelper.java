package utils.helpers;

import java.text.Normalizer;

/**
 * Auxiliar class which defines possible useful common methods for manipulating Strings
 * <p>
 * Note: all methods would be static; not reason to instanciate or extend this class
 * 
 * @see Stackoverflow #1844355
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public final class StringHelper {

	/**
	 * Suppress default constructor for noninstantiability; "Effective Java" Item 4.
	 * @throws AssertionError
	 */
	private StringHelper() {
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


}
