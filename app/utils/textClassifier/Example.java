package utils.textClassifier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import weka.core.SerializationHelper;
import weka.core.Utils;


public class Example {

	// private static final String MODEL_PATH = "/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/example-model.arff";

	private static final String QUERY_1_PATH = "/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/query1.txt";
	private static final String QUERY_2_PATH = "/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/query2.txt";
	private static final String HIT_1_PATH = "/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/hit1.txt";
	private static final String MISS_1_PATH = "/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/miss1.txt";
	private static final String MISS_2_PATH = "/Users/manutero/workspace/itafy/app/utils/textClassifier/exampleFiles/miss2.txt";

	public static void main(String[] args) {

		Example example = new Example();
		String[] miss1 = {"-m", MISS_1_PATH, "-c", "miss", "-t", "example"};
		example.execute(miss1);
		String[] miss2 = {"-m", MISS_2_PATH, "-c", "miss", "-t", "example"};
		example.execute(miss2);
		String[] hit1 = {"-m", HIT_1_PATH, "-c", "hit", "-t", "example"};
		example.execute(hit1);
		String[] query = {"-m", QUERY_1_PATH, "-t", "example"};
		System.out.println("Query, we do know it's a miss");
		example.execute(query);
		String[] query2 = {"-m", QUERY_2_PATH, "-t", "example"};
		System.out.println("Query, we do know it's a miss");
		example.execute(query2);
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
				messageCl.classifyMessage(message.toString());
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
