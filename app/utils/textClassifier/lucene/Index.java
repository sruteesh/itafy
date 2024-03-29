package utils.textClassifier.lucene;

import java.io.IOException;

import models.categories.AvaibleCategories;
import models.entities.Tweet;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

import play.Logger;

/**
 * <i>At the heart of Lucene there is a Index</i>.<br>
 * Data is stored in Lucene's index as documents, then, searches are possible on
 * the index.
 * <p>
 * The directory where the data is stored is a RAMDirectory by default
 * <p>
 * The analyzer is defined in <code>utils.textSearch.Analyzer</code> <br>
 * The Lucene's version is defined in <code>utils.LuceneVersion</code>
 * <p>
 * Useful Help
 * <ul>
 * <li>Great Lucene's tutorial: http://oak.cs.ucla.edu/cs144/projects/lucene/
 * <li>About Lucene's analyzers: Stackoverflow #5483903
 * </ul>
 * 
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 * @see Directory
 * @see Analyzer
 * @see LuceneVersion
 */
public class Index {
	public static final String TEXT = "text";
	public static final String CATEGORY = "category";

	private IndexWriter writer = null;
	private boolean initialized = false;

	public Index() {
		try {
			initialization();
		} catch (IOException e) {
			Logger.error("textSearch.Index()");
			e.printStackTrace();
		}
	}

	private void initialization() throws IOException {
		IndexWriterConfig config;
		config = new IndexWriterConfig(LuceneVersion.VERSION, Analyzer.getAnalyzer());
		writer = new IndexWriter(LuceneDirectory.getDirectory(), config);
		initialized = true;
	}

	public boolean addText(String text, AvaibleCategories category) {
		boolean response = false;
		try {
			if (!initialized) {
				initialization();
			} else {
				Document doc = new Document();
				doc.add(new TextField(TEXT, text, Field.Store.NO));
				doc.add(new TextField(CATEGORY, category.name(), Field.Store.YES));
				writer.addDocument(doc);
				response = true;
			}
		} catch (IOException e) {
			Logger.error("EXCEPTION: utils.textSearch.Index.addTweet(" + text + ")");
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Adds the tweet to the index. In order to use the less the better, just
	 * index (time) the text - not the mongo's id - and only save (space) the id
	 * 
	 * @param tweet
	 *            the tweet we want to save in the index
	 * @return success; false if the index is not initialized yet or exception
	 *         raises
	 */
	public boolean addTweet(Tweet tweet, AvaibleCategories category) {
		return addText(tweet.getText(), category);
	}

	public void closeWriter() {
		initialized = false;
		try {
			writer.close();
		} catch (IOException e) {
			Logger.error("EXCEPTION: utils.textSearch.Index.closeWriter()");
			e.printStackTrace();
		}
	}

}
