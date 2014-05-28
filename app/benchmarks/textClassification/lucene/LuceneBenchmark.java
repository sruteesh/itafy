package benchmarks.textClassification.lucene;

import java.io.File;
import models.categories.AvaibleCategories;
import utils.helpers.FileHelper;
import utils.helpers.NormalizeHelper;
import utils.textSearch.LuceneEvaluator;
import benchmarks.textClassification.TextClassificationBenchmark;


public class LuceneBenchmark extends TextClassificationBenchmark {
	private LuceneEvaluator classifier;

	public LuceneBenchmark() {
		hits = 0;
		misses = 0;
		classifier = new LuceneEvaluator();
	}

	@Override
	public void runBenchmarks() {
		try {
			train();
			politicTest();
			cultureTest();
			sportTest();
			uncategorizedTest();
			printResults();
		} catch (Exception e) {
			System.err.println("LuceneBenchmark.runBenchmarks()");
			e.printStackTrace();
			return;
		}
	}

	@Override
	protected void train() throws Exception {
		classifier.trainWithFile(FINAL_CORPUS);
		System.out.println("Classifer ready");
	}

	@Override
	protected void test(File folder, String expected) {
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				String realText = FileHelper.readTextFile(fileEntry.getPath());
				String text = NormalizeHelper.normalizeText(realText);
				String got = classifier.queryAndEvaluate(text);
				print(got, expected, text);
				compare(got, expected);
			}
		}
	}

	@SuppressWarnings("unused")
	@Deprecated
	private void train(File folder, AvaibleCategories category) {
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				String realText = FileHelper.readTextFile(fileEntry.getPath());
				String text = NormalizeHelper.normalizeText(realText);
				classifier.addText(text, category);
			}
		}
	}

	public static void main(String[] a) {
		LuceneBenchmark benchmark = new LuceneBenchmark();
		benchmark.runBenchmarks();
		System.out.println("FIN");
	}

}
