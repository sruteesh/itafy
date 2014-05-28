package utils.textClassifier.weka;

import java.io.File;
import java.util.HashMap;
import utils.helpers.FileHelper;

/**
 * Shows how to use the <code>TextClassifier</code> class
 * <p>
 * Contanis three examples
 * (there is no a super <code>main</code> method, just rename the main you want to execute)
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class TextClassifierExample {

	/**
	 * First example
	 * <p>
	 * Shows how to use the <code>textClassifier</code> class building initially an empty dataset
	 * and filling it merging multiple <em>arff</em> files
	 */
	public static void main(String[] args) {
		final File folder = new File(DATA_FOLER);
		TextClassifier classifier = new TextClassifier();
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				classifier.updateDataset(fileEntry.getPath());
			}
		}
		showStadistics(classifier);
		politicQuery(classifier);
		cultureQuery(classifier);
		sportQuery(classifier);
		System.out.println("END");
	}

	/**
	 * Second example
	 * <p>
	 * Shows how to use the <code>textClassifier</code> class building a dataset from a single
	 * <em>arff</em> file
	 */
	@SuppressWarnings("unused")
	public static void _main(String[] args) {
		TextClassifier classifier = new TextClassifier(EXAMPLE_MODEL);
		showStadistics(classifier);
		politicQuery(classifier);
		cultureQuery(classifier);
		sportQuery(classifier);
		System.out.println("END");
	}

	/**
	 * Third example
	 * <p>
	 * Shows how to use the <code>textClassifier</code> class building initially an empty dataset
	 * and filling it manually using <code>updateData(text, class)</code> method
	 */
	@SuppressWarnings("unused")
	public static void __main(String[] args) {
		TextClassifier classifier = new TextClassifier();
		updateDataWithExampleFiles(classifier);
		showStadistics(classifier);
		politicQuery(classifier);
		cultureQuery(classifier);
		sportQuery(classifier);
		System.out.println("END");
	}


	// private methods

	private static void showStadistics(TextClassifier classifier) {
		System.out.println("Att: " + classifier.getNumAttributes());
		System.out.println("Classes: " + classifier.getNumClasses());
		System.out.println("Instances: " + classifier.getNumInstances());
		System.out.println("----------------------------------");
	}

	private static void politicQuery(TextClassifier classifier) {
		HashMap<String, Double> response;
		System.out.println("Query, we do know it's a politic text");
		response = classifier.classifyMessage(FileHelper.readTextFile(POLITIC_QUERY));
		System.out.println(response);
	}

	private static void cultureQuery(TextClassifier classifier){
		HashMap<String, Double> response;
		System.out.println("Query, we do know it's a cultural text");
		response = classifier.classifyMessage(FileHelper.readTextFile(CULTURE_QUERY));
		System.out.println(response);
	}

	private static void sportQuery(TextClassifier classifier) {
		HashMap<String, Double> response;
		System.out.println("Query, we do know it's a sports text");
		response = classifier.classifyMessage(FileHelper.readTextFile(SPORT_QUERY));
		System.out.println(response);
	}

	private static void updateDataWithExampleFiles(TextClassifier classifier) {
		// politicS
		classifier.updateData(FileHelper.readTextFile(POLITIC_TEXT_1), MsgClassificationConstants.NEWS);
		classifier.updateData(FileHelper.readTextFile(POLITIC_TEXT_2), MsgClassificationConstants.NEWS);
		classifier.updateData(FileHelper.readTextFile(POLITIC_TEXT_3), MsgClassificationConstants.NEWS);

		// culture
		classifier.updateData(FileHelper.readTextFile(CULTURE_TEXT_1), MsgClassificationConstants.NEWS);
		classifier.updateData(FileHelper.readTextFile(CULTURE_TEXT_2), MsgClassificationConstants.NEWS);

		// sport
		classifier.updateData(FileHelper.readTextFile(SPORT_TEXT_1), MsgClassificationConstants.SPORTS);
		classifier.updateData(FileHelper.readTextFile(SPORT_TEXT_2), MsgClassificationConstants.SPORTS);
	}


	// constants

	private static final String DATA_FOLER = "/Users/manutero/workspace/itafy/weka-data";

	private static final String EXAMPLE_MODEL =
			TextClassifierExample.class.getResource("exampleFiles/example-model.arff").getPath();

	private static final String POLITIC_QUERY =
			TextClassifierExample.class.getResource("exampleFiles/query_politica.txt").getPath();

	private static final String CULTURE_QUERY =
			TextClassifierExample.class.getResource("exampleFiles/query_cultura.txt").getPath();

	private static final String SPORT_QUERY =
			TextClassifierExample.class.getResource("exampleFiles/query_deportes.txt").getPath();

	private static final String POLITIC_TEXT_1 =
			TextClassifierExample.class.getResource("exampleFiles/politica_1.txt").getPath();

	private static final String POLITIC_TEXT_2 =
			TextClassifierExample.class.getResource("exampleFiles/politica_2.txt").getPath();

	private static final String POLITIC_TEXT_3 =
			TextClassifierExample.class.getResource("exampleFiles/politica_3.txt").getPath();

	private static final String CULTURE_TEXT_1 =
			TextClassifierExample.class.getResource("exampleFiles/cultura_1.txt").getPath();

	private static final String CULTURE_TEXT_2 =
			TextClassifierExample.class.getResource("exampleFiles/cultura_2.txt").getPath();

	private static final String SPORT_TEXT_1 =
			TextClassifierExample.class.getResource("exampleFiles/deportes_1.txt").getPath();

	private static final String SPORT_TEXT_2 =
			TextClassifierExample.class.getResource("exampleFiles/deportes_2.txt").getPath();

}
