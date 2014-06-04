package benchmarks.textClassification;

import java.io.File;
import models.categories.AvaibleCategories;

public abstract class TextClassificationBenchmark {
	protected int hits;
	protected int misses;
	protected int falseNews;
	protected int falseSports;

	public abstract void runBenchmarks();

	protected abstract void train() throws Exception;

	protected void uncategorizedTest() {
		System.out.println("UNCATEGORIZED\n------------------\n");
		File uncategorizedFolder = new File(UNCATEGORIZED_TWEETS);
		test(uncategorizedFolder, "");
	}

	protected void sportTest() {
		File sportFolder = new File(SPORT_TWEETS);
		test(sportFolder, "DEPORTES");
	}

	protected void cultureTest() {
		File cultureFolder = new File(CULTURE_TWEETS);
		test(cultureFolder, "ACTUALIDAD");
	}

	protected void politicTest() {
		System.out.println("POLITIC\n-----------------------");
		File politicFolder = new File(POLITIC_TWEETS);
		test(politicFolder, "ACTUALIDAD");
	}

	protected abstract void test(File folder, String expected);

	protected void compare(String got, String expected) {
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

	protected void printResults() {
		System.out.println("HITS" + hits);
		System.out.println("MISS" + misses);
		System.out.println("NO - NEWS" + falseNews);
		System.out.println("NO - SPORT" + falseSports);
	}

	protected boolean mustBeUncategorized(String s) {
		return s.equals("");
	}

	protected void print(Object got, Object expected, Object text) {
		System.out.println("GOT: " + got + " =? " + expected + " (expected) \t" + text);
	}

	//FIXME relative
	protected static final String DATA_FOLDER = "/Users/manutero/workspace/itafy/weka-data";
	protected static final String FINAL_CORPUS = "/Users/manutero/workspace/itafy/weka-data/corpus_tratado.arff";

	protected static final String CULTURE_TWEETS =
			TextClassificationBenchmark.class.getResource("tweets/culture").getPath();

	protected static final String POLITIC_TWEETS =
			TextClassificationBenchmark.class.getResource("tweets/politic").getPath();

	protected static final String SPORT_TWEETS =
			TextClassificationBenchmark.class.getResource("tweets/sport").getPath();

	protected static final String UNCATEGORIZED_TWEETS =
			TextClassificationBenchmark.class.getResource("tweets/uncategorized").getPath();

}
