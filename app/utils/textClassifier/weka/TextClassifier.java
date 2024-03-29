package utils.textClassifier.weka;

import java.io.Serializable;
import java.util.HashMap;
import models.categories.AvaibleCategories;
import weka.classifiers.Classifier;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.NNge;
import weka.classifiers.trees.FT;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * TODO class description
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see <a href="https://svn.cms.waikato.ac.nz/svn/weka/branches/stable-3-6/wekaexamples/src/main/java/wekaexamples/"> weka's examples</a>
 * @see Stackoverflow #17596492
 */
public class TextClassifier implements Serializable {
	private static final long serialVersionUID = -5296895910740177580L; // serialization

	/* Weka's objects */
	private Instances dataset; // weka's model
	private StringToWordVector filter; // used to generate the words count
	private Classifier rulesClassifier; // weka's magic: classifier based on rules
	private Classifier tableClassifier; // weka's magic: classifier based on a decision table
	private Classifier treeClassifier;  // weka's magic: classifier based on a tree
	private boolean isUpToDate; // is the model up to date? when new data is stored in the model, we need to actualize

	// Last query's distributions
	private double[] rulesDistribution;
	private double[] tableDistribution;
	private double[] treeDistribution;

	// wizard
	private WekaEvaluator evaluator;

	public TextClassifier() {
		dataset = MsgClassificationDataset.getDataset();
		filter = new StringToWordVector();
		rulesClassifier = new NNge();
		tableClassifier = new DecisionTable();
		treeClassifier = new FT();
		evaluator = new WekaEvaluator();
		isUpToDate = false;
	}

	public TextClassifier(String pathToArffFile) {
		ArffReader fileReader = new ArffReader();
		dataset = fileReader.buildDatasetFromFile(pathToArffFile);
		filter = new StringToWordVector();
		rulesClassifier = new NNge();
		tableClassifier = new DecisionTable();
		treeClassifier = new FT();
		evaluator = new WekaEvaluator();
		isUpToDate = false;
	}

	public void updateDataset(String pathToArffFile) {
		ArffReader fileReader = new ArffReader();
		Instances dataFromFile = fileReader.buildDatasetFromFile(pathToArffFile);
		try {
			mergeDataSet(dataFromFile);
		} catch (Exception e) {
			System.err.println("TextClassifier: mergeDataSet("+ pathToArffFile +")");
			e.printStackTrace();
		}
		isUpToDate = false;
	}

	/**
	 * Change <code>this.dataset</code>
	 * @see Stackoverflow #*/
	private void mergeDataSet(Instances data) throws Exception {
		for (int i=0; i < data.numInstances(); i++) {
			Instance instance = data.instance(i);
			makeInstance(instance);
			dataset.add(instance);
		}
		isUpToDate = false;
	}

	/**
	 * Black magic.
	 * For some reason when 2 datasets were merged a <code>wekas.core.exception</code> were raised.
	 * The solution was think in the instance we were adding as a completly new instance.
	 * <p>
	 * Takes the data (msg and class) of the input instance and creates a new instance
	 * <p>
	 * 
	 * @param instance contains the data for the new instance
	 * @see Stackoverflow #20813069
	 */
	private void makeInstance(Instance instance) {
		Attribute messageAttribute = dataset.attribute(MsgClassificationConstants.MESSAGE);
		instance.setValue(messageAttribute, messageAttribute.addStringValue(getMsg(instance)));
		instance.setDataset(dataset);
		instance.setClassValue(getClassValue(instance));
	}

	private String getMsg(Instance instance) {
		int msgIndex = dataset.attribute(MsgClassificationConstants.MESSAGE).index();
		return instance.stringValue(msgIndex);
	}

	private String getClassValue(Instance instance) {
		int classIndex = dataset.classIndex();
		return instance.stringValue(classIndex);
	}

	/**
	 * Updates model using the given training message
	 * <p>
	 * After adding a new instance to dataset, it needs to be rebuild
	 * 
	 * @param text	the message content
	 * @param classValue	the class label
	 */
	public void updateData(String text, String classValue) {
		// 1) Make message into instance.
		Instance instance = makeInstance(text, dataset);
		instance.setClassValue(classValue);
		// 2) Add instance to training data.
		dataset.add(instance);
		isUpToDate = false;
	}

	/**
	 * main method
	 * @param text
	 * @return
	 */
	public String classifyAndEvaluateMsg(String text) {
		HashMap<String, Double> averageDistribution = classifyMessage(text);
		String response = evaluator.evaluate(averageDistribution);
		return response;
	}

	/**
	 * Classifies a given message and return the classification distribution for each possible class.
	 * <p>
	 * The distribution for each category is a double between
	 * 0.0 (it has nothing to do) and 1.0 (we are pretty sure about it)
	 * <p>
	 * If there is no dataset build, or there are no instances, or something goes worng while weka's work;
	 * the response would be <code>null</code>
	 * <p>
	 * The result <code>HashMap</code> will be smth like the following
	 * (note that is up to the caller decide which category is the best option)
	 * 
	 * <pre>
	 * | category1 | 2.5 |
	 * | category2 | 4.0 |
	 * | category3 | 2.5 |
	 * | category4 | 0.0 |
	 * | category5 | 1.0 |
	 * </pre>
	 * 
	 * @param text the message content
	 * @return HashMap containing distribution for each class. null if error while execution
	 */
	public HashMap<String,Double> classifyMessage(String text) {

		// 1) Check whether classifier has been built.
		if (dataset.numInstances() == 0) {
			return null;
		}

		// 2) Check whether classifier and filter are up to date.
		if (!isUpToDate) {
			try {
				rebuildClassifier();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		// 3) Make separate little test set so that message does not get added to string attribute
		Instances testset = dataset.stringFreeStructure();
		Instance instance = makeInstance(text, testset);

		// 4 classify
		HashMap<String, Double> response = new HashMap<String, Double>();
		try {
			response = classifyInstance(instance);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return response;
	}

	public int getNumInstances() { return dataset.numInstances(); }
	public int getNumAttributes() { return dataset.numAttributes(); }
	public int getNumClasses() { return dataset.numClasses(); }

	public HashMap<String, Double> getRulesResults() {
		return zipDistributions(rulesDistribution);
	}

	public HashMap<String, Double> getTreeResults() {
		return zipDistributions(treeDistribution);
	}

	public HashMap<String, Double> getTableResults() {
		return zipDistributions(tableDistribution);
	}

	public HashMap<String, Double> getAverageResults() {
		return evaluator.getEvaluableData();
	}

	@Override
	public String toString() {
		return
				"Att: " + this.getNumAttributes() + "\n" +
				"Classes: " + this.getNumClasses() + "\n" +
				"Instances: " + this.getNumInstances() + "\n";
	}


	private HashMap<String, Double> classifyInstance(Instance instance) throws Exception {
		Instance filteredInstance = filterInstance(instance);
		rulesDistribution = rulesClassifier.distributionForInstance(filteredInstance);
		treeDistribution = treeClassifier.distributionForInstance(filteredInstance);
		tableDistribution = tableClassifier.distributionForInstance(filteredInstance);
		return buildDistributionMap(rulesDistribution, treeDistribution, tableDistribution);
	}

	@SuppressWarnings("unused")
	@Deprecated
	private String getClassName(double predicted) throws Exception {
		return dataset.classAttribute().value((int) predicted);
	}

	private Instance filterInstance(Instance instance) throws Exception {
		filter.input(instance);
		Instance filteredInstance = filter.output();
		return filteredInstance;
	}

	private HashMap<String, Double> buildDistributionMap(double[]... distributions) {
		HashMap<String, Double> response = new HashMap<String, Double>();
		String[] categoriesNames = AvaibleCategories.names();
		int length = categoriesNames.length;
		for (int i = 0; i < length; i++) {
			double sum = 0.0;
			for (double[] distribution : distributions) {
				if (distribution.length != length) {
					return null;
				}
				sum += distribution[i];
			}
			Double averageValue = Double.valueOf(sum / distributions.length);
			response.put(categoriesNames[i], averageValue);
		}
		return response;
	}

	private HashMap<String, Double> zipDistributions(double[] d) {
		String[] categoriesNames = AvaibleCategories.names();
		if ((d == null) || (d.length != categoriesNames.length)) {
			return null;
		}
		HashMap<String, Double> response = new HashMap<String, Double>();
		for (int i = 0; i < d.length; i++) {
			Double value = Double.valueOf(d[i]);
			response.put(categoriesNames[i], value);
		}
		return response;
	}

	/**
	 * <ol>
	 *  <li> Initialize filter and tell it about the input format
	 *  <li> Generate word counts from the training data
	 *  <li> Rebuild the 3 classifiers
	 * </ol>
	 * 
	 * @throws Exception
	 */
	public void rebuildClassifier() throws Exception {
		filter.setInputFormat(dataset);
		Instances filteredData = Filter.useFilter(dataset, filter);
		System.out.println("Building classifier");
		rulesClassifier.buildClassifier(filteredData); System.out.println("rules done");
		tableClassifier.buildClassifier(filteredData); System.out.println("table done");
		treeClassifier.buildClassifier(filteredData); System.out.println("tree done");
		isUpToDate = true;
	}

	/**
	 * Method that converts a text message into an instance.
	 * 
	 * @param text	the message content to convert
	 * @param data	the header information
	 * @return the generated Weka's Instance
	 */
	private Instance makeInstance(String text, Instances data) {
		Instance instance = new Instance(2); // two attributes
		Attribute messageAttribute = data.attribute(MsgClassificationConstants.MESSAGE);
		instance.setValue(messageAttribute, messageAttribute.addStringValue(text));
		instance.setDataset(data);
		return instance;
	}

}
