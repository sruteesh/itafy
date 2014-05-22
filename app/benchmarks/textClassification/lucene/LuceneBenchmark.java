package benchmarks.textClassification.lucene;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import models.categories.AvaibleCategories;
import utils.helpers.FileHelper;
import utils.helpers.NormalizeHelper;
import utils.textSearch.LuceneEvaluator;


public class LuceneBenchmark {
	private int hits;
	private int misses;
	private LuceneEvaluator evaluator;

	public static void main(String[] a) {
		LuceneBenchmark benchmark = new LuceneBenchmark();
		benchmark.runBenchmarks();
		System.out.println("FIN");
	}

	public LuceneBenchmark() {
		hits = 0;
		misses = 0;
		evaluator = new LuceneEvaluator();
	}

	public void runBenchmarks() {
		fillIndex(new File(CULTURE_TWEETS), AvaibleCategories.ACTUALIDAD);
		fillIndex(new File(SPORT_TWEETS), AvaibleCategories.DEPORTES);
		HashMap<String, ArrayList<Float>> response = new HashMap<String, ArrayList<Float>>();
		response = evaluator.query("Atletico campeon de copa");
		System.out.println(response);

		fillIndex(new File(POLITIC_TWEETS), AvaibleCategories.ACTUALIDAD);
		response = evaluator.query("Atletico campeon de copa");
		System.out.println(response);
	}

	private void fillIndex(File folder, AvaibleCategories category) {
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				String realText = FileHelper.readTextFile(fileEntry.getPath());
				String text = NormalizeHelper.normalizeText(realText);
				evaluator.addText(text, category);
			}
		}
	}



	private static final String CULTURE_TWEETS =
			LuceneBenchmark.class.getResource("../tweets/culture").getPath();

	private static final String POLITIC_TWEETS =
			LuceneBenchmark.class.getResource("../tweets/politic").getPath();

	private static final String SPORT_TWEETS =
			LuceneBenchmark.class.getResource("../tweets/sport").getPath();

	private static final String UNCATEGORIZED_TWEETS =
			LuceneBenchmark.class.getResource("../tweets/uncategorized").getPath();

}
