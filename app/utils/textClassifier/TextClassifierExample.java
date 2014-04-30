package utils.textClassifier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import utils.helpers.FileHelper;
import weka.core.SerializationHelper;
import weka.core.Utils;


public class TextClassifierExample {

	// FIXME: relative paths instead of absolute local paths
	// Stackoverflow#3844307

	private static final String MODEL_PATH =
			//"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/example-model.arff";
			"/Users/manutero/workspace/itafy/weka-data/2014_04_29.arff";

	private static final String POLITIC_QUERY_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/query_politica.txt";
	private static final String ECONOMY_QUERY_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/query_economia.txt";
	private static final String CULTURE_QUERY_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/query_cultura.txt";
	private static final String SPORT_QUERY_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/query_deportes.txt";

	private static final String POLITIC_TEXT_1_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/politica_1.txt";
	private static final String POLITIC_TEXT_2_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/politica_2.txt";
	private static final String POLITIC_TEXT_3_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/politica_3.txt";

	private static final String ECONOMY_TEXT_1_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/economia_1.txt";
	private static final String ECONOMY_TEXT_2_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/economia_2.txt";
	private static final String ECONOMY_TEXT_3_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/economia_3.txt";

	private static final String CULTURE_TEXT_1_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/cultura_1.txt";
	private static final String CULTURE_TEXT_2_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/cultura_2.txt";

	private static final String SPORT_TEXT_1_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/deportes_1.txt";
	private static final String SPORT_TEXT_2_PATH =
			"/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/deportes_2.txt";

	public static void main(String[] args) {

		TextClassifier classifier = new TextClassifier(MODEL_PATH);
		System.out.println("Att: " + classifier.getNumAttributes());
		System.out.println("Classes: " + classifier.getNumClasses());
		System.out.println("Instances: " + classifier.getNumInstances());
		System.out.println("----------------------------------");

		HashMap<String, Double> response = new HashMap<String, Double>();

		String cultureQuery = FileHelper.readTextFile(CULTURE_QUERY_PATH);
		response = classifier.classifyMessage(cultureQuery);
		System.out.println("We do know it's a cultural text");
		System.out.println(response);

		String PoliticQuery = FileHelper.readTextFile(POLITIC_QUERY_PATH);
		response = classifier.classifyMessage(PoliticQuery);
		System.out.println("We do know it's a politic text");
		System.out.println(response);

		//		String economyQuery = FileHelper.readTextFile(ECONOMY_QUERY_PATH);
		//		response = classifier.classifyMessage(economyQuery);
		//		System.out.println("We do know it's a economic text");
		//		System.out.println(response);

		String sportQuery = FileHelper.readTextFile(SPORT_QUERY_PATH);
		response = classifier.classifyMessage(sportQuery);
		System.out.println("We do know it's a sport text");
		System.out.println(response);

		System.out.println("END");
	}

	public static void _main(String[] args) {

		TextClassifierExample example = new TextClassifierExample();

		String[] politica1 = {"-m", POLITIC_TEXT_1_PATH, "-c", "POLITICA", "-t", "example"};
		example.execute(politica1);
		String[] politica2 = {"-m", POLITIC_TEXT_2_PATH, "-c", "POLITICA", "-t", "example"};
		example.execute(politica2);
		String[] politica3 = {"-m", POLITIC_TEXT_3_PATH, "-c", "POLITICA", "-t", "example"};
		example.execute(politica3);

		String[] economia1 = {"-m", ECONOMY_TEXT_1_PATH, "-c", "ECONOMIA", "-t", "example"};
		example.execute(economia1);
		String[] economia2 = {"-m", ECONOMY_TEXT_2_PATH, "-c", "ECONOMIA", "-t", "example"};
		example.execute(economia2);
		String[] economia3 = {"-m", ECONOMY_TEXT_3_PATH, "-c", "ECONOMIA", "-t", "example"};
		example.execute(economia3);

		String[] cultura1 = {"-m", CULTURE_TEXT_1_PATH, "-c", "CULTURA", "-t", "example"};
		example.execute(cultura1);
		String[] cultura2 = {"-m", CULTURE_TEXT_2_PATH, "-c", "CULTURA", "-t", "example"};
		example.execute(cultura2);

		String[] deportes1 = {"-m", SPORT_TEXT_1_PATH, "-c", "DEPORTES", "-t", "example"};
		example.execute(deportes1);
		String[] deportes2 = {"-m", SPORT_TEXT_2_PATH, "-c", "DEPORTES", "-t", "example"};
		example.execute(deportes2);


		String[] queryPolitica = {"-m", POLITIC_QUERY_PATH, "-t", "example"};
		System.out.println("Query, we do know it's <politica> ");
		example.execute(queryPolitica);

		String[] queryEconomia = {"-m", ECONOMY_QUERY_PATH, "-t", "example"};
		System.out.println("Query, we do know it's <economia> ");
		example.execute(queryEconomia);

		String[] queryCultura = {"-m", CULTURE_QUERY_PATH, "-t", "example"};
		System.out.println("Query, we do know it's <cultura> ");
		example.execute(queryCultura);

		String[] queryDeportes = {"-m", SPORT_QUERY_PATH, "-t", "example"};
		System.out.println("Query, we do know it's <deportes> ");
		example.execute(queryDeportes);

		System.out.println("END");
	}

	/**
	 * Main method. The following parameters are recognized:
	 * <ul>
	 *   <li>
	 *      <code>-m messagefile</code><br/>
	 *      Points to the file containing the message to classify or use for
	 *      updating the model.
	 *   </li>
	 *   <li>
	 *      <code>-c classlabel</code><br/>
	 *      The class label of the message if model is to be updated. Omit for
	 *      classification of a message.
	 *   </li>
	 *   <li>
	 *      <code>-t modelfile</code><br/>
	 *      The file containing the model. If it doesn't exist, it will be
	 *      created automatically.
	 *   </li>
	 * </ul>
	 */
	public void execute(String[] args) {
		try {
			// Read message file into string.
			String messageName = Utils.getOption('m', args);
			if (messageName.length() == 0) {
				throw new Exception("Must provide name of message file ('-m <file>').");
			}
			FileReader m = new FileReader(messageName);
			StringBuffer message = new StringBuffer();
			int l;
			while ((l = m.read()) != -1) {
				message.append((char) l);
			}
			m.close();

			// Check if class value is given.
			String classValue = Utils.getOption('c', args);

			// If model file exists, read it, otherwise create new one.
			String modelName = Utils.getOption('t', args);
			if (modelName.length() == 0) {
				throw new Exception("Must provide name of model file ('-t <file>').");
			}
			TextClassifier messageCl;
			try {
				messageCl = (TextClassifier) SerializationHelper.read(modelName);
			} catch (FileNotFoundException e) {
				System.out.println("NEW Classifier");
				messageCl = new TextClassifier();
			}

			// Check if there are any options left
			Utils.checkForRemainingOptions(args);

			// Process message.
			if (classValue.length() != 0) {
				messageCl.updateData(message.toString(), classValue);
			} else {
				HashMap<String, Double> r = messageCl.classifyMessage(message.toString());
				System.out.println(r);
			}

			// Save message classifier object only if it was updated.
			if (classValue.length() != 0) {
				SerializationHelper.write(modelName, messageCl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
