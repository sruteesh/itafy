package utils.textClassifier.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;


/**
 * Defines a single instance of Lucene's analyzer for the entire app.
 *
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 */
public class Analyzer {
	private static StandardAnalyzer analyzer;
	public static StandardAnalyzer getAnalyzer() {
		if (analyzer == null) {
			analyzer = new StandardAnalyzer(LuceneVersion.VERSION);
		}
		return analyzer;
	}
}
