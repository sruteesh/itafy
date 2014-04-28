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

/**
 * <ol>
 *  <li> Reads a configuration file with a list of rss links classified by category
 *  <li> For each rss link, parse the feed
 *  <li> creates a output arff file using {@link utils.fileWriter.ArffWriter ArffWritter class}
 * </ol>
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see ArffWriter
 * @see AvaibleCategories
 */
public class FeedReader {
	// FIXME relative path
	protected static String RSS_CONF_FILE = "/Users/manutero/workspace/itafy/conf/rss.txt";
	protected static String OUTPUT_FILE = "/Users/manutero/workspace/itafy/conf/weka_model.arff";

	/**
	 * rss.keySet() => categories
	 * rss.get("category") => links
	 */
	protected HashMap<String, ArrayList<String>> rss;

	public FeedReader() {
		try {
			rss = readConfigurationFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public FeedReader(String rssConfigurationFile) {
		RSS_CONF_FILE = rssConfigurationFile;
		try {
			rss = readConfigurationFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * TODO: description
	 * 
	 * @param outputFile
	 * @return
	 */
	public boolean createModel(String outputFile) {
		OUTPUT_FILE = outputFile;
		return createModel();
	}

	/**
	 * Main method
	 * TODO: description
	 * 
	 * @return false if IOException raised while execution
	 */
	public boolean createModel() {
		if (rss.isEmpty()) {
			return false;
		}
		ArrayList<WritableElement> data = parseFeedForEachRssLink();
		return buildModelAsFile(data);
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
					// possible error in RSS from provider, not our guilt
					continue;
				}
			}
		}

		return response;
	}



	private ArrayList<WritableElement> getWritableElementsForFeed(Feed feed, String categoryAsString) {
		ArrayList<WritableElement> response = new ArrayList<WritableElement>();
		for (FeedMsg msg : feed.getMessages()) {
			String comment = msg.buildComment();
			WritableElement element = new WritableElement(comment, msg.getDescription(), categoryAsString);
			response.add(element);
		}
		return response;
	}

	private boolean buildModelAsFile(ArrayList<WritableElement> data) {
		ArffWriter writer = new ArffWriter(OUTPUT_FILE);
		boolean fail = false;
		for(WritableElement element : data) {
			fail |= writer.writeComment(element.comment);
			fail |= writer.writeData(element.text, element.classification);
			writer.writeEnter();
		}
		return !fail;
	}

	/**
	 * The response is similar to:
	 * <pre>
	 * {
	 *   CULTURA => [link1, link2, ..., linkN]
	 *   DEPORTES => [... linkM]
	 *   ...
	 * }
	 * </pre>
	 * @throws IOException
	 */
	private HashMap<String, ArrayList<String>> readConfigurationFile() throws IOException {
		HashMap<String, ArrayList<String>> response = new HashMap<String, ArrayList<String>>();

		FileReader fileReader = new FileReader(new File(RSS_CONF_FILE));
		BufferedReader br = new BufferedReader(fileReader);
		String line = null;
		List<String> linksOfLastCategory = new ArrayList<String>();
		String lastCategory = "";

		// if no more lines the readLine() returns null
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
		for (String categoryName: AvaibleCategories.names()) {
			if (categoryName.equals(line)) {
				return true;
			}
		}
		return false;
	}

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
