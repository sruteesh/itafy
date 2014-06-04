package benchmarks.textClassification.weka;

import java.io.File;
import utils.helpers.FileHelper;
import utils.helpers.NormalizeHelper;
import utils.textClassifier.weka.TextClassifier;
import benchmarks.textClassification.TextClassificationBenchmark;

/**
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class WekaBenchmark extends TextClassificationBenchmark {
	private TextClassifier classifier;

	public WekaBenchmark() {
		classifier = new TextClassifier();
		hits = 0;
		misses = 0;
		falseNews = 0;
		falseSports = 0;
	}

	@Override
	public void runBenchmarks() {
		try {
			train();
			printClassifier();
			politicTest();
			cultureTest();
			sportTest();
			uncategorizedTest();
			printResults();
		} catch (Exception e) {
			e.printStackTrace();return;
		}
	}

	@Override
	protected void test(File folder, String expected) {
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

	@Override
	protected void train() throws Exception {
		classifier.updateDataset(FINAL_CORPUS);
		System.out.println("Model build");
		classifier.rebuildClassifier();
		System.out.println("Classifer ready");
	}

	protected void train(final File folder) throws Exception {
		for (final File fileEntry : folder.listFiles()) {
			if (FileHelper.isNormalFile(fileEntry)) {
				classifier.updateDataset(fileEntry.getPath());
			}
		}
		System.out.println("Model build");
		classifier.rebuildClassifier();
		System.out.println("Classifer ready");
	}

	public void printClassifier() {
		System.out.println(classifier.toString());
	}

	public static void main(String[] args) {
		WekaBenchmark benchmark = new WekaBenchmark();
		benchmark.runBenchmarks();
		System.out.println("END");
	}

}
