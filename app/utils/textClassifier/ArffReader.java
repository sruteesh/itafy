package utils.textClassifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import weka.core.Instances;

/**
 * Could read a <code>arff</code> file and extract a Weka's dataset from it.
 * <p>
 * The file is expected with the Weka's header (relation name, attributes declaration);
 * the class is expected as the last attribute
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class ArffReader {

	/**
	 * @param path the path to the arff file
	 * @return dataset build from the arff file or null if error
	 */
	public Instances buildDatasetFromFile(String path) {
		Instances dataset = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			dataset = new Instances(reader);
			reader.close();
			// setting class attribute, expected as last attribute
			dataset.setClassIndex(dataset.numAttributes() - 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataset;
	}

}
