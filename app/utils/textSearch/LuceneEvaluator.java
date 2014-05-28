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
 * Main class to classify a text in one of the categories defined on
 * {@link models.categories.AvaibleCategories AvaibleCategories} using Lucene.
 * <p>
 * First, it is necessary to train the system. Two possibilities avaible:
 * <ol>
 *  <li> {@link utils.textSearch.LuceneEvaluator#addText(String, String) Add each text separately}
 *  <li> {@link utils.textSearch.LuceneEvaluator#trainWithFile(String) Add every text from a arff file}
 * </ol>
 * Once this is done, is possible to make queries. The response would be the most valuable category
 * corresponding to the text
 * <pre>
 * LuceneEvaluator e = new LuceneEvaluator();
 * e.trainWithFile("path_to_file");
 * String response = e.queryAndEvaluation("this is the text")
 * </pre>
 * It's possible to check the score we've got
 * <pre>
 * ArrayList score = e.getEvaluableData(response)
 * </pre>
 * It's also possible to get the intermediate data of the evaluation process
 * <pre>
 * HashMap scores = e.query("this is the text");
 * String response = e.evaluate(scores);
 * </pre>
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class LuceneEvaluator {
	private Index index = null;
	private HashMap<String, ArrayList<Float>> evaluableData;

	public LuceneEvaluator() {
		index = new Index();
		evaluableData = new HashMap<String, ArrayList<Float>>();
	}

	// getters & setters
	// --------------------

	public ArrayList<Float> getEvaluableData(String category) {
		return evaluableData.get(category);
	}

	public Set<String> getCategories() {
		return evaluableData.keySet();
	}


	// train the index
	// --------------------

	/**
	 * Fill the Lucene index with data (text and category of the text)
	 * 
	 * @param text supposed to be normalized and preprocessed
	 * @param category must be a representation of one category defined on AvaibleCategories
	 * @see AvaibleCategories
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
	 * Fill the Lucene index with data (text and category of the text)
	 * 
	 * @param text supposed to be normalized and preprocessed
	 * @param category
	 * @see AvaibleCategories
	 * @return success
	 */
	public boolean addText(String text, AvaibleCategories category) {
		return index.addText(text, category);
	}

	/**
	 * Fill the Lucene index with multiple data at the same time
	 * <p>
	 * The file would have the same format as in the text classifier based on
	 * {@link utils.textClassifier.ArffReader Weka} </br>
	 * <ul>
	 *  <li> Headings starting with <code>@</code> (will ignore)
	 *  <li> Comments starting with <code>%</code> (will ignore)
	 *  <li> Valuable data with the following format: <em>"text",CLASS_NAME</em>
	 * </ul>
	 * 
	 * @param pathToFile path to the arff file
	 * @return success
	 */
	public boolean trainWithFile(String pathToFile) {
		boolean response = true;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(pathToFile)));
			String line;
			while ((line = br.readLine()) != null) {
				processLine(line);
			}
			br.close();
		} catch(Exception e) {
			System.err.println("LuceneEvaluator.trainWithFile(" + pathToFile + ")");
			e.printStackTrace();
			response = false;
		}
		return response;
	}

	private void processLine(String line) {
		if (lineIsAText(line)) {
			String text = getText(line);
			String className = getClassName(line);
			addText(text, className);
		}
	}

	/** @example lineIsAText("this is the text,CLASS") => true */
	private boolean lineIsAText(String line) {
		return !line.isEmpty() && (line.charAt(0) == ((char) 34));
	}

	/**
	 * Returns the class name looking for the last coma up to the end of the line
	 * <p>
	 * Line is expected as <code>"this is the text which, could have, multiple comas",CLASS_NAME</code>
	 * <p>
	 * Note: <code>,</code> corresponds to 44 in ASCII
	 * @param line with the following format: <code>"text",class</code>
	 * @return the class name
	 */
	private String getClassName(String line) {
		return line.substring(line.lastIndexOf((char) 44) + 1);
	}

	/**
	 * Returns the text as a substring from the first character, up to the last coma.
	 * <p>
	 * line is expected as <code>"this is the text which, could have, multiple comas",CLASS_NAME</code>
	 * <p>
	 * Note: <code>,</code> corresponds to 44 in ASCII
	 * @param line with the following format: <code>"text",class</code>
	 * @return the text
	 */
	private String getText(String line) {
		return line.substring(0, line.lastIndexOf((char) 44));
	}


	// query and evaluation
	// --------------------


	/**
	 * Query using Lucene.
	 * The response is the top 10 related documents found by Lucene and the score for each one
	 * 
	 * @param textQuery supposed to be normalized and preprocessed
	 * @return for each category, the score for the top related documents
	 */
	public HashMap<String, ArrayList<Float>> query(String textQuery) {
		index.closeWriter();
		Searcher searcher = new Searcher();
		return searcher.search(textQuery);
	}

	/**
	 * Query using Lucene, the response is automatically evaluated by the
	 * {@link utils.textSearch.LuceneEvaluator#evaluate(HashMap) evaluation function}
	 * 
	 * @param textQuery supposed to be normalized and preprocessed
	 * @return determined class name for the text
	 */
	public String queryAndEvaluate(String textQuery) {
		HashMap<String, ArrayList<Float>> distribution = query(textQuery);
		return evaluate(distribution);
	}


	// evaluation constants
	// --------------------

	private static final double M = 0.15;
	private static final double LOWEST_SCORE_TO_CONSIDER = 0.03;
	private static final double MIN_DIFFERENCE_BETWEEN_2_CLASSES = 0.05;
	private static final int MIN_DOCUMENTS_NEED_TO_BE_FOUND = 1;
	private static final int SOME_DOCUMENTS_FOUND = 4;
	private static final double SOME_DOCUMENTS_FOUND_REWARD = 0.03;
	private static final int MANY_DOCUMENTS_FOUND = 7;
	private static final double MANY_DOCUMENTS_FOUND_REWARD = 0.08;

	/**
	 * Evaluates a distribution returned by Lucene in order to determine the most valuable category,
	 * or empty String if consider no winner. <br/>
	 * The function applied is shown by the following example
	 * 
	 * <pre>
	 * Initial distribution
	 * News => {0.09, 0.04, 0.04, 0.035, 0.03} (6 documents)
	 * Sports => {0.15, 0.01, 0.01, 0.01} (4 documents)
	 * </pre>
	 * 1) Removes the lowest values from each category (see <code>LOWEST_SCORE_TO_CONSIDER</code>)
	 * <pre>
	 * News => {0.09, 0.04, 0.04, 0.035} (4 documents)
	 * Sports => {0.15} (1 document)
	 * </pre>
	 * 2) Cheks that there is at last one document for each category
	 * (see <code>MIN_DOCUMENTS_NEED_TO_BE_FOUND</code>)
	 * <p>
	 * 3) Gets one total score for each category (sum)
	 * <pre>
	 * News => 0.205 (4 documents)
	 * Sports => 0.15 (1 document)
	 * </pre>
	 * 4) Consider a reward to those categories which have many scored documents
	 * (see <code>MANY_DOCUMENTS_FOUND_REWARD</code>)
	 * <pre>
	 * News => 0.285 (+0.08)
	 * Sports => 0.15 (+0.00)
	 * </pre>
	 * 5) For each score, checks if could be a candidate (see <code>M</code>)
	 * <pre>
	 * 0.285 >= 0.15 => candidate
	 * 0.150 >= 0.15 => candidate
	 * </pre>
	 * 6) Consider if there is a minimum of distance between the scores
	 * (see <code>MIN_DIFFERENCE_BETWEEN_2_CLASSES</code>)
	 * <pre>
	 * | {0.285} - {0.15} | > LIMIT
	 * </pre>
	 * 7) Gets the max (final answer)
	 * <pre>
	 * News => 0.285
	 * </pre>
	 * 
	 * @param distribution
	 * @return most valuable category or empty string
	 */
	public String evaluate(HashMap<String, ArrayList<Float>> distribution) {
		evaluableData = distribution;
		HashMap<String, Double> possibleCategories = new HashMap<String, Double>();
		for (Entry<String, ArrayList<Float>> entry : evaluableData.entrySet()) {
			String key = entry.getKey();
			ArrayList<Float> distributionForOneCategory = entry.getValue();
			distributionForOneCategory = removeSuperLowEntries(distributionForOneCategory);
			if (areAtLeastAMinimumOfDocuments(distributionForOneCategory)) {
				double sum = CollectionHelper.sumValues(distributionForOneCategory);
				sum += updateSumAccordingToNumberOfDocuments(distributionForOneCategory.size());
				if (sum > M) {
					possibleCategories.put(key, Double.valueOf(sum));
				}
			}
		}
		return hungerGames(possibleCategories);
	}

	/** Lucene retrieve the top N documents found... even if their score is extremly low */
	private ArrayList<Float> removeSuperLowEntries(ArrayList<Float> values) {
		ArrayList<Float> response = new ArrayList<Float>();
		for (Float value : values) {
			if (value.floatValue() > LOWEST_SCORE_TO_CONSIDER) {
				response.add(value);
			}
		}
		return response;
	}

	private boolean areAtLeastAMinimumOfDocuments(ArrayList<Float> distributionForOneCategory) {
		return distributionForOneCategory.size() >= MIN_DOCUMENTS_NEED_TO_BE_FOUND;
	}

	/** There is a reward to the score according to how many documents are found */
	private double updateSumAccordingToNumberOfDocuments(int numberOfDocuments) {
		double response = 0.0;
		if (areManyDocuments(numberOfDocuments)) {
			response = MANY_DOCUMENTS_FOUND_REWARD;
		} else if (areSomeDocuments(numberOfDocuments)){
			response = SOME_DOCUMENTS_FOUND_REWARD;
		}
		return response;
	}

	private boolean areManyDocuments(int n) {
		return n >= MANY_DOCUMENTS_FOUND;
	}

	private boolean areSomeDocuments(int n) {
		return n >= SOME_DOCUMENTS_FOUND;
	}


	// FIXME: hungerGames(HashMap)
	//
	// 1) Este metodo no es generico para N categorias posibles.
	// Considera que solo existiran las categorias ATUALIDAD y DEPORTES, generalizar para N
	// 2) Deberia comparar el valor de cada candidato con el resto de candidatos,
	// para cada comparacion, si no supera el umbral "MIN_DIFFERENCE_BETWEEN_2_CLASSES"
	// eliminar ambos candidatos.
	// Problemas
	// - eliminar parejas de candidaitos no considera que haya un tercero que haya que eliminar
	// - eliminar una posicion del HashMap en el bucle producira una "concurrentException"
	// 3) La solucion actual implica lanzar, a sabiendas, excepciones al acceder a campos que hemos
	// borrado potencialmente.
	// 4) No deberia haber un try-catch
	// ---------------------------------------------------------------------------------------

	/** Only one will survive */
	private String hungerGames(HashMap<String, Double> candidates) {
		try {
			double news = candidates.get(AvaibleCategories.ACTUALIDAD.name()).doubleValue();
			double spor = candidates.get(AvaibleCategories.DEPORTES.name()).doubleValue();
			if (Math.abs(news-spor)<MIN_DIFFERENCE_BETWEEN_2_CLASSES) {
				return "";
			}
		} catch (Exception e) {
			// see the fixme above
		}
		return CollectionHelper.max(candidates);
	}

}
