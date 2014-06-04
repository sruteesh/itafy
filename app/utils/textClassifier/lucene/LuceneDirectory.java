package utils.textClassifier.lucene;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * Defines a single Lucene's Directory for the entire app.
 *
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 */
public class LuceneDirectory {
	private static Directory directory;
	public static Directory getDirectory() {
		if (directory == null) {
			directory = new RAMDirectory();
		}
		return directory;
	}
}
