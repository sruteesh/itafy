package utils.textSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import models.categories.AvaibleCategories;

/**
 * FIXME a ingles
 * Esta es la clase MÁS superior al modulo de Lucene.
 * 
 * En código
 * <pre>
 * Evaluator e = new Evaluator();
 * // fill with data
 * e.addText(text, category);
 * response = {}
 * response = e.query("categorizame esto")
 * </pre>
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class LuceneEvaluator {
	private Index index = null;
	private static double LOW_LIMIT = 0.6;
	private HashMap<String, ArrayList<Float>> evaluableData;

	public LuceneEvaluator() {
		index = new Index();
		evaluableData = new HashMap<String, ArrayList<Float>>();
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

	public String queryAndEvaluation(String textQuery) {
		HashMap<String, ArrayList<Float>> distribution = query(textQuery);
		return evaluate(distribution);
	}

	/**
	 * TODO
	 * 
	 * Example
	 * News => {0.9, 0.7, 0.6} (3/4)
	 * Sports => {0.8} (1/4)
	 * 
	 * @param distribution
	 * @return
	 */
	public String evaluate(HashMap<String, ArrayList<Float>> distribution) {
		evaluableData = distribution;
		ArrayList<Float> newsValues = getEvaluableData(AvaibleCategories.ACTUALIDAD.name());
		ArrayList<Float> sportsValues = getEvaluableData(AvaibleCategories.DEPORTES.name());

		int totalSize = newsValues.size() + sportsValues.size();
		double newsModifier = newsValues.size() / totalSize;
		double sportsModifier = sportsValues.size() / totalSize;
		// ...
		return null;
	}


	// getters & setters

	public void setLowLimit(double v) { LOW_LIMIT = v; }

	public ArrayList<Float> getEvaluableData(String category) {
		return evaluableData.get(category);
	}

	public Set<String> getCategories() {
		return evaluableData.keySet();
	}


	// private

	@SuppressWarnings("unused")
	private ArrayList<Float> cleanLowValues(ArrayList<Float> values) {
		ArrayList<Float> response = new ArrayList<Float>();
		for (Float f : values) {
			if (f.floatValue() > LOW_LIMIT) {
				response.add(f);
			}
		}
		return response;
	}

}
