package benchmarks.textClassification;

import java.io.File;
import java.util.HashMap;
import utils.helpers.FileHelper;
import utils.textClassifier.TextClassifier;

/**
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class TextClassificationBenchmark {
	private TextClassifier classifier;

	public TextClassificationBenchmark() {
		classifier = new TextClassifier();
	}

	public void runBenchmarks() {
		train(new File(DATA_FOLDER));
		printClassifier();
		politicTest();
		cultureTest();
		sportTest();
		uncategorizedTest();
	}

	private void uncategorizedTest() {
		System.out.println("UNCATEGORIZED\n------------------\n");
		File uncategorizedFolder = new File(UNCATEGORIZED_TWEETS);
		test(uncategorizedFolder);
	}

	private void sportTest() {
		System.out.println("SPORT\n------------------\n");
		File sportFolder = new File(SPORT_TWEETS);
		test(sportFolder);
	}

	private void cultureTest() {
		System.out.println("CULTURE\n------------------\n");
		File cultureFolder = new File(CULTURE_TWEETS);
		test(cultureFolder);
	}

	private void politicTest() {
		System.out.println("POLITIC\n------------------\n");
		File politicFolder = new File(POLITIC_TWEETS);
		test(politicFolder);
	}

	private void test(File folder) {
		HashMap<String, Double> response = new HashMap<String, Double>();
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				String text = FileHelper.readTextFile(fileEntry.getPath());
				System.out.println(text);
				response = classifier.classifyMessage(text);
				System.out.println(response);
				System.out.println(classifier.getLastClassName());
				System.out.println("+++++++++++++++++++++++");
			}
		}
	}

	private void train(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				classifier.updateDataset(fileEntry.getPath());
			}
		}
	}

	private void printClassifier() {
		System.out.println(classifier.toString());
	}


	//FIXME relative
	private static final String DATA_FOLDER = "/Users/manutero/workspace/itafy/weka-data";

	private static final String CULTURE_TWEETS =
			TextClassificationBenchmark.class.getResource("tweets/culture").getPath();

	private static final String POLITIC_TWEETS =
			TextClassificationBenchmark.class.getResource("tweets/politic").getPath();

	private static final String SPORT_TWEETS =
			TextClassificationBenchmark.class.getResource("tweets/sport").getPath();

	private static final String UNCATEGORIZED_TWEETS =
			TextClassificationBenchmark.class.getResource("tweets/uncategorized").getPath();

	public static void main(String[] args) {
		TextClassificationBenchmark benchmark = new TextClassificationBenchmark();
		benchmark.runBenchmarks();
		System.out.println("END");
	}

}
