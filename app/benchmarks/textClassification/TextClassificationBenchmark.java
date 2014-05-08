package benchmarks.textClassification;

import java.io.File;
import models.categories.AvaibleCategories;
import utils.helpers.FileHelper;
import utils.helpers.NormalizeHelper;
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
		try {
			train(FINAL_CORPUS);
			printClassifier();
			politicTest();
			cultureTest();
			sportTest();
			uncategorizedTest();
			results();
		} catch (Exception e) {
			e.printStackTrace();return;
		}
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
		test(uncategorizedFolder, "NINGUNO");
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
		System.out.println("POLITIC\n-----------------------");
		File politicFolder = new File(POLITIC_TWEETS);
		test(politicFolder, "ACTUALIDAD");
	}

	private void test(File folder, String expected) {
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				String realText = FileHelper.readTextFile(fileEntry.getPath());
				String text = NormalizeHelper.normalizeText(realText);
				String got = classifier.classifyAndEvaluateMsg(text);
				print(got, expected, text);
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
		return s.equals("NINGUNO");
	}

	private void print(Object got, Object expected, Object text) {
		System.out.println("GOT: " + got + " =? " + expected + " (expected) \t" + text);
	}

	private void train(String oneFile) throws Exception {
		classifier.updateDataset(oneFile);
		System.out.println("Model build");
		classifier.rebuildClassifier();
		System.out.println("Classifer ready");
	}

	@SuppressWarnings("unused")
	private void train(final File folder) throws Exception {
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				classifier.updateDataset(fileEntry.getPath());
			}
		}
		System.out.println("Model build");
		classifier.rebuildClassifier();
		System.out.println("Classifer ready");
	}

	private void printClassifier() {
		System.out.println(classifier.toString());
	}


	//FIXME relative
	@SuppressWarnings("unused")
	private static final String DATA_FOLDER = "/Users/manutero/workspace/itafy/weka-data";
	private static final String FINAL_CORPUS = "/Users/manutero/workspace/itafy/weka-data/corpus_tratado.arff";

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
