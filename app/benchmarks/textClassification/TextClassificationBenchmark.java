package benchmarks.textClassification;

import java.io.File;
import java.util.HashMap;
import models.categories.AvaibleCategories;
import utils.helpers.FileHelper;
import utils.textClassifier.TextClassifier;

/**
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class TextClassificationBenchmark {

	private int hits;
	private int misses;
	private int falseNews;
	private int falseSports;
	private TextClassifier classifier;

	public TextClassificationBenchmark() {
		classifier = new TextClassifier();
		hits = 0;
		misses = 0;
		falseNews = 0;
		falseSports = 0;
	}

	public void runBenchmarks() {
		train(new File(DATA_FOLDER));
		printClassifier();
		politicTest();
		cultureTest();
		sportTest();
		uncategorizedTest();
		results();
	}

	private void results() {
		System.out.println("HITS" + hits);
		System.out.println("MISS" + misses);
		System.out.println("NO - NEWS" + falseNews);
		System.out.println("NO - SPORT" + falseSports);
	}
	private void uncategorizedTest() {
		System.out.println("UNCATEGORIZED\n------------------\n");
		File uncategorizedFolder = new File(UNCATEGORIZED_TWEETS);
		test(uncategorizedFolder, "");
	}

	private void sportTest() {
		File sportFolder = new File(SPORT_TWEETS);
		test(sportFolder, "DEPORTES");
	}

	private void cultureTest() {
		File cultureFolder = new File(CULTURE_TWEETS);
		test(cultureFolder, "ACTUALIDAD");
	}

	private void politicTest() {
		File politicFolder = new File(POLITIC_TWEETS);
		test(politicFolder, "ACTUALIDAD");
	}

	private void test(File folder, String expected) {
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				String text = FileHelper.readTextFile(fileEntry.getPath());
				HashMap<String, Double> response = classifier.classifyMessage(text);
				String got = classifier.getLastClassName();
				print(got, expected, response);
				compare(got, expected);
			}
		}
	}

	private void compare(String got, String expected) {
		if (mustBeUncategorized(expected)) {
			if (got.equals(AvaibleCategories.ACTUALIDAD.name())) {
				this.falseNews++;
			} else if (got.equals(AvaibleCategories.DEPORTES.name())) {
				this.falseSports++;
			}
		} else {
			if (got.equals(expected)) {
				this.hits++;
			} else {
				this.misses++;
			}
		}
	}

	private boolean mustBeUncategorized(String s) {
		return s.isEmpty();
	}

	private void print(Object got, Object expected, Object details) {
		System.out.println(got + " =? " + expected + "\t(" + details + ")");
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
