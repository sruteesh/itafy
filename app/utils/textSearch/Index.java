package utils.textSearch;

import java.io.IOException;
import models.data.TweetData;
import models.entities.Tweet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import play.Logger;


/**
 * <i>At the heart of Lucene there is a Index</i>.<br>
 * Data is stored in Lucene's index as documents, then, searches are possible on the index.
 * <p>
 * The directory where the data is stored is a RAMDirectory by default
 * <p>
 * The analyzer is defined in <code>utils.textSearch.Analyzer</code> <br>
 * The Lucene's version is defined in <code>utils.LuceneVersion</code>
 * <p>
 * Useful Help
 * <ul>
 * <li> Great Lucene's tutorial: http://oak.cs.ucla.edu/cs144/projects/lucene/
 * <li> About Lucene's analyzers: Stackoverflow #5483903
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
	public static final String INDEX = "index";

	private IndexWriter writer = null;
	private boolean initialized = false;

	public Index() {
		try {
			IndexWriterConfig config;
			config = new IndexWriterConfig(LuceneVersion.VERSION, Analyzer.getAnalyzer());
			writer = new IndexWriter(LuceneDirectory.getDirectory(), config);
			initialized = true;
		} catch (IOException e) {
			Logger.error("textSearch.Index()");
			e.printStackTrace();
		}
	}


	/**
	 * Search the tweet that corresponds to the input id. If the tweet does exist, calls and returns
	 * <code>addTweet(Tweet)</code>
	 * 
	 * @param tweetId Mongo's id as a String which corresponds to a Tweet object
	 * @return success; false if the tweet does not exist or <code>addTweet(Tweet)</code> fails
	 */
	public boolean addTweet(String tweetId) {
		boolean response = false;
		Tweet t = TweetData.findTweet(tweetId);
		if (t != null) {
			response = addTweet(t);
		}
		return response;
	}


	/**
	 * Adds the tweet to the index. In order to use the less the better,
	 * just index (time) the text - not the mongo's id - and only save (space) the id
	 *
	 * @param tweet the tweet we want to save in the index
	 * @return success; false if the index is not initialized yet or excepction raises
	 */
	public boolean addTweet(Tweet tweet) {
		boolean response = false;
		if (initialized) {
			try {
				Document doc = new Document();
				doc.add(new TextField(TEXT, tweet.getText(), Field.Store.NO));
				doc.add(new TextField(INDEX, tweet.getId(), Field.Store.YES));
				writer.addDocument(doc);
				response = true;
			} catch (IOException e) {
				Logger.error("EXCEPTION: utils.textSearch.Index.addTweet("+tweet.toString()+")");
				e.printStackTrace();
			}
		}
		return response;
	}


	public void closeWriter() {
		try {
			writer.close();
		} catch (IOException e) {
			Logger.error("EXCEPTION: utils.textSearch.Index.closeWriter()");
			e.printStackTrace();
		}
	}

}
