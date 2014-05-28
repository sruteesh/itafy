package utils.textClassifier.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import play.Logger;


/**
 * TODO
 *
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 * @see Analyzer
 * @see LuceneVersion
 */
public class Searcher {
	private QueryParser parser = null;
	private IndexSearcher searcher = null;
	private boolean initialized = false;

	public Searcher() {
		try {
			parser = new QueryParser(LuceneVersion.VERSION, Index.TEXT, Analyzer.getAnalyzer());
			searcher = new IndexSearcher(DirectoryReader.open(LuceneDirectory.getDirectory()));
			initialized = true;
		} catch (IOException e) {
			Logger.error("textSearch.utils.Searcher()");
			e.printStackTrace();
		}
	}

	/**
	 * Once the Lucene's index is filled with documents; do a text search and return
	 * <code>numberOfHits</code> results
	 * <p>
	 * Results are stored in a HashMap as {:tweet_id => score}
	 *
	 * @param textQuery (String) query
	 * @param numberOfHits (int) number of desired hits
	 * @return (HashMap) results or null if error ocurred (see logs)
	 */
	public HashMap<String, ArrayList<Float>> search(String textQuery, int numberOfHits) {
		if (initialized) {
			try {
				Query query = parser.parse(textQuery);
				TopScoreDocCollector collector = TopScoreDocCollector.create(numberOfHits, true);
				searcher.search(query, collector);
				return convertHitsInHash(collector.topDocs().scoreDocs);
			} catch (Exception e) {
				Logger.error("EXCEPTION textSearch.utils.Searcher.search()");
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Once the Lucene's index is filled with documents; do a text search and
	 * return 10 results
	 * <p>
	 * Results are stored in a HashMap as {:tweet_id => score}
	 *
	 * @param textQuery (String) query
	 * @return (HashMap) results or null if error ocurred (see logs)
	 */
	public HashMap<String, ArrayList<Float>> search(String textQuery) {
		return search(textQuery, 10);
	}


	private HashMap<String, ArrayList<Float>> convertHitsInHash(ScoreDoc[] hits) throws IOException {
		HashMap<String, ArrayList<Float>> response = new HashMap<String, ArrayList<Float>>();
		for(ScoreDoc hit : hits) {
			int docId = hit.doc;
			Document d = searcher.doc(docId);
			Float hitValue = Float.valueOf(hit.score);
			String category = d.get(Index.CATEGORY);
			if (response.get(category) == null) {
				response.put(category, new ArrayList<Float>());
			}
			ArrayList<Float> tmp = response.get(category);
			tmp.add(hitValue);
			response.put(category, tmp);
		}
		return response;
	}

}
