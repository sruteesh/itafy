package utils.feedReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.categories.AvaibleCategories;
import utils.fileWriter.ArffWriter;
import utils.helpers.DateHelper;

/**
 * Once initialized (with the default configuration file or a selected one), is
 * able to create an <em>arff</em> model (in the default path or a selected one)
 * <p>
 * The process is:
 * <ol>
 * <li>Reads a configuration file with a list of rss links classified by
 * {@link models.categories.AvaibleCategories category}
 * <li>For each rss link, parse the feed
 * <li>Creates a output arff file using {@link utils.fileWriter.ArffWriter
 * ArffWritter class}
 * <li>The arff file could be used by Weka
 * </ol>
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see ArffWriter
 * @see AvaibleCategories
 */
public class FeedReader {
	// FIXME relative paths
	private static final String DEFAULT_CONF_PATH = "/Users/raul/GitHub/itafy/conf/rss.txt";
	private static final String DEFAULT_OUT_PATH = "/Users/raul/GitHub/itafy/weka-data/";

	/**
	 * rss.keySet() => categories rss.get("category") => links for category
	 */
	private HashMap<String, ArrayList<String>> rss;
	private String confFilePath;
	private String outputFilePath;

	/**
	 * Initialize with the default configuration file <code>/conf/rss.txt</code>
	 */
	public FeedReader() {
		confFilePath = DEFAULT_CONF_PATH;
		outputFilePath = buildDefaultOutputPath();
		try {
			rss = readConfigurationFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Initialize with a determined configuration file
	 * 
	 * @param rssConfigurationFile
	 *            path to the configuration file
	 */
	public FeedReader(String rssConfigurationFile) {
		confFilePath = rssConfigurationFile;
		outputFilePath = buildDefaultOutputPath();
		try {
			rss = readConfigurationFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Creates a output arff file using {@link utils.fileWriter.ArffWriter
	 * ArffWritter class}</br> The arff file could be used by Weka
	 * 
	 * @param outputFile
	 *            path of the output arff file
	 * @return success; false if IOException raised while execution
	 */
	public void createModel(String outputFile) {
		outputFilePath = outputFile;
		createModel();
	}

	/**
	 * Creates a output arff file using {@link utils.fileWriter.ArffWriter
	 * ArffWritter class}</br> The arff file could be used by Weka as the data
	 * model
	 * <p>
	 * Note: the arff file will be saved in the default
	 * <code>DEFAULT_OUT_PATH</code>
	 * 
	 * @return success; false if IOException raised while execution or not
	 *         configuration file read
	 */
	public void createModel() {
		if (rss.isEmpty()) {
			System.err.println("FeedReader: no rss initialized");
		}
		ArrayList<WritableElement> data = parseFeedForEachRssLink();
		buildModelAsFile(data);
	}

	private ArrayList<WritableElement> parseFeedForEachRssLink() {
		ArrayList<WritableElement> response = new ArrayList<WritableElement>();
		FeedParser parser = new FeedParser();

		for (String categoryAsString : rss.keySet()) {
			List<String> linksForOneCategory = rss.get(categoryAsString);
			for (String link : linksForOneCategory) {
				try {
					Feed feed = parser.readFeed(link);
					ArrayList<WritableElement> valuableContent = getWritableElementsForFeed(feed, categoryAsString);
					response.addAll(valuableContent);
				} catch (Exception e) {
					// possible error in RSS from provider, not our guilt; just
					// continue your way
					System.err.println("FeedReader: rss error from provider (" + link + ")");
					continue;
				}
			}
		}

		return response;
	}

	/**
	 * Taking one <em>feed</em>, which contains several articles from
	 * newspapers, that corresponds to a <em>category</em>; get an array of
	 * <code>WritableElement</code> instances
	 * <p>
	 * Note a <code>WritableElement</code> describes the tuple {comment + text +
	 * category}
	 * 
	 * @param feed
	 *            one feed contains several articles (
	 *            <code>feed.getMessages()</code>)
	 * @param categoryAsString
	 *            corresponding to each article of the feed
	 * @return array of <code>WritableElement</code> instances {comment + text +
	 *         category}
	 */
	private ArrayList<WritableElement> getWritableElementsForFeed(Feed feed, String categoryAsString) {
		ArrayList<WritableElement> response = new ArrayList<WritableElement>();
		for (FeedMsg msg : feed.getMessages()) {
			String comment = msg.buildComment();
			String text = utils.helpers.NormalizeHelper.normalizeText(msg.getDescription());
			WritableElement element = new WritableElement(comment, text, categoryAsString);
			response.add(element);
		}
		return response;
	}

	private void buildModelAsFile(ArrayList<WritableElement> data) {
		ArffWriter writer = new ArffWriter(outputFilePath);
		for (WritableElement element : data) {
			writer.writeComment(element.comment);
			writer.writeData(element.text, element.classification);
			writer.writeEnter();
		}
	}

	/**
	 * The response is similar to:
	 * 
	 * <pre>
	 * {
	 *   CULTURA => [link1, link2, ..., linkN]
	 *   DEPORTES => [... linkM]
	 *   ...
	 * }
	 * </pre>
	 * 
	 * @throws IOException
	 */
	private HashMap<String, ArrayList<String>> readConfigurationFile() throws IOException {
		HashMap<String, ArrayList<String>> response = new HashMap<String, ArrayList<String>>();

		FileReader fileReader = new FileReader(new File(confFilePath));
		BufferedReader br = new BufferedReader(fileReader);
		String line = null;
		List<String> linksOfLastCategory = new ArrayList<String>();
		String lastCategory = "";

		// if no more lines readLine() returns null
		while ((line = br.readLine()) != null) {
			if (newCategoryDeclaration(line)) {
				if (lastCategory != "") {
					response.put(lastCategory, new ArrayList<String>(linksOfLastCategory));
				}
				lastCategory = line;
				linksOfLastCategory.clear();
			} else {
				if (!line.isEmpty()) {
					linksOfLastCategory.add(line);
				}
			}

		}

		br.close();
		return response;
	}

	private boolean newCategoryDeclaration(String line) {
		for (String categoryName : AvaibleCategories.names()) {
			if (categoryName.equals(line)) {
				return true;
			}
		}
		return false;
	}

	private String buildDefaultOutputPath() {
		return DEFAULT_OUT_PATH + DateHelper.today() + ".arff";
	}

	/**
	 * Describes the tuple {comment + text + category}
	 */
	private class WritableElement {
		String comment;
		String text;
		String classification;

		WritableElement(String comment, String text, String classification) {
			this.comment = comment;
			this.text = text;
			this.classification = classification;
		}
	}

}
