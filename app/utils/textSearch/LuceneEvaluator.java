package utils.textSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import models.categories.AvaibleCategories;
import utils.helpers.CollectionHelper;

/**
 * FIXME a ingles
 * Esta es la clase MÁS superior al modulo de Lucene.
 * 
 * En código
 * <pre>
 * Evaluator e = new Evaluator();
 * // 1) fill with data: could be text by text or with a weka file.
 * e.addText(text, category);
 * e.trainWithFile(pathToFile);
 * // 2) make queries: could be the complete response or just a wining name
 * response = {}
 * response = e.query("categorizame esto")
 * response = ""
 * response = e.queryAndEvaluation("categorizame esto")
 * </pre>
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class LuceneEvaluator {
	private Index index = null;
	private HashMap<String, ArrayList<Float>> evaluableData;

	// XXX
	private static final double LIMIT = 0.15;
	private static final int MIN_DOCUMENTS = 1;
	private static final double MIN_DIFFERENCE = 0.05;
	private static final double MIN_VALUE = 0.03;

	public LuceneEvaluator() {
		index = new Index();
		evaluableData = new HashMap<String, ArrayList<Float>>();
	}


	/**
	 * TODO description. From arff (the same as Wekas) ...
	 * @param pathToFile
	 * @return
	 */
	public boolean trainWithFile(String pathToFile) {
		boolean response = true;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(pathToFile)));
			String line;
			while ((line = br.readLine()) != null) {
				process(line);
			}
			br.close();
		} catch(Exception e) {
			System.err.println("LuceneEvaluator.trainWithFile(" + pathToFile + ")");
			e.printStackTrace();
			response = false;
		}
		return response;
	}

	private void process(String line) {
		if (lineIsAText(line)) {
			String text = getText(line);
			String className = getClassName(line);
			addText(text, className);
		}
	}

	private boolean lineIsAText(String line) {
		return !line.isEmpty() && (line.charAt(0) == ((char) 34));
	}

	/**
	 * Line would be somthing like <br/>
	 * <code>"this is the text which, could have, multiple comas",CLASS_NAME</code>
	 * <p>
	 * Returns the class name looking for the last coma up to the end of the line
	 * <p>
	 * Note: <code>,</code> corresponds to 44 in ASCII
	 * @param line with the following format: <code>"text",class</code>
	 * @return the class name
	 */
	private String getClassName(String line) {
		return line.substring(line.lastIndexOf((char) 44) + 1);
	}

	/**
	 * line would be something like <br/>
	 * <code>"this is the text which, could have, multiple comas",CLASS_NAME</code>
	 * <p>
	 * Returns the text as a substring from the first character, up to the last coma.
	 * <p>
	 * Note: <code>,</code> corresponds to 44 in ASCII
	 * @param line with the following format: <code>"text",class</code>
	 * @return the text
	 */
	private String getText(String line) {
		return line.substring(0, line.lastIndexOf((char) 44));
	}


	/**
	 * Fill the Lucene index with data
	 * @return success
	 */
	public boolean addText(String text, String category) {
		AvaibleCategories asCategory = AvaibleCategories.valueOf(category);
		boolean response = false;
		if (asCategory != null) {
			response = addText(text, asCategory);
		}
		return response;
	}


	/**
	 * Fill the Lucene index with data
	 * @return success
	 */
	public boolean addText(String text, AvaibleCategories category) {
		return index.addText(text, category);
	}


	/**
	 * Query using Lucene, the response is the top 10 related documents
	 * @return for each category, the score for the top related documents
	 */
	public HashMap<String, ArrayList<Float>> query(String textQuery) {
		index.closeWriter();
		Searcher searcher = new Searcher();
		return searcher.search(textQuery);
	}


	/**
	 * 
	 * @param textQuery
	 * @return
	 */
	public String queryAndEvaluation(String textQuery) {
		HashMap<String, ArrayList<Float>> distribution = query(textQuery);
		return evaluate(distribution);
	}


	/**
	 * TODO
	 * 
	 * Example
	 * Politics => {0.9, 0.7, 0.6} (3/4)
	 * Sports => {0.8} (1/4)
	 * 
	 * @param distribution
	 * @return
	 */
	public String evaluate(HashMap<String, ArrayList<Float>> distribution) {
		evaluableData = distribution;
		HashMap<String, Double> possibleCategories = new HashMap<String, Double>();
		for (Entry<String, ArrayList<Float>> entry : evaluableData.entrySet()) {
			String key = entry.getKey();
			ArrayList<Float> distributionForOneCategory = entry.getValue();
			distributionForOneCategory = removeSuperLowValues(distributionForOneCategory);
			if (areAtLeastAMinimumOfDocuments(distributionForOneCategory)) {
				double sum = CollectionHelper.sumValues(distributionForOneCategory);
				sum += updateSumAccordingToNumberOfDocuments(distributionForOneCategory.size());
				if (sum > LIMIT) {
					possibleCategories.put(key, Double.valueOf(sum));
				}
			}
		}
		System.out.println(possibleCategories);
		return hungerGames(possibleCategories);
	}

	private ArrayList<Float> removeSuperLowValues(ArrayList<Float> values) {
		ArrayList<Float> response = new ArrayList<Float>();
		for (Float value : values) {
			if (value.floatValue() > MIN_VALUE) {
				response.add(value);
			}
		}
		return response;
	}


	private boolean areAtLeastAMinimumOfDocuments(ArrayList<Float> distributionForOneCategory) {
		return distributionForOneCategory.size() >= MIN_DOCUMENTS;
	}

	private double updateSumAccordingToNumberOfDocuments(int numberOfDocuments) {
		double response = 0.0;
		if (numberOfDocuments > 6) {
			response = 0.08;
		} else if (numberOfDocuments > 3){
			response = 0.03;
		}
		return response;
	}


	/** Only one will survive */
	private String hungerGames(HashMap<String, Double> candidates) {
		// FIXME
		try {
			double news = candidates.get(AvaibleCategories.ACTUALIDAD.name()).doubleValue();
			double spor = candidates.get(AvaibleCategories.DEPORTES.name()).doubleValue();
			if (Math.abs(news-spor)<MIN_DIFFERENCE) {
				return "";
			}
		} catch (Exception e) {

		}
		//		HashMap<String, Double> map = candidates;
		//		HashMap<String, Double> tmp;
		//		for (Entry<String, Double> entry : candidates.entrySet()) {
		//			String key = entry.getKey();
		//			double value = entry.getValue().doubleValue();
		//			tmp = (HashMap<String, Double>) map.clone();
		//			tmp.remove(key);
		//			for (Entry<String, Double> entry2 : tmp.entrySet()) {
		//				String key2 = entry2.getKey();
		//				double value2 = entry2.getValue().doubleValue();
		//				if (Math.abs(value - value2) < MIN_DIFFERENCE) {
		//					map.remove(key);
		//					map.remove(key2);
		//				}
		//			}
		//		}
		return CollectionHelper.max(candidates);
	}


	// getters & setters

	public ArrayList<Float> getEvaluableData(String category) {
		return evaluableData.get(category);
	}

	public Set<String> getCategories() {
		return evaluableData.keySet();
	}

}
