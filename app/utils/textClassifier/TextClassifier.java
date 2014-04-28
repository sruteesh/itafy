package utils.textClassifier;

import java.io.Serializable;
import java.util.HashMap;
import models.categories.AvaibleCategories;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
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
	private Classifier classifier; // weka's magic
	private boolean isUpToDate; // is the model up to date? when new data is stored in the model, we need to actualize


	public TextClassifier() {
		dataset = MsgClassificationDataset.getDataset();
		filter = new StringToWordVector();
		classifier = new J48();
		isUpToDate = false;
	}

	public TextClassifier(String pathToArffFile) {
		ArffReader fileReader = new ArffReader();
		dataset = fileReader.buildDatasetFromFile(pathToArffFile);
		filter = new StringToWordVector();
		classifier = new J48();
		isUpToDate = false;
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
				isUpToDate = true;
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


	private HashMap<String, Double> classifyInstance(Instance instance) throws Exception {
		Instance filteredInstance = filterInstance(instance);
		double[] probabilityDistribution = classifier.distributionForInstance(filteredInstance);
		return buildDistributionMap(probabilityDistribution);
	}

	@SuppressWarnings("unused")
	private String predictedClassName(Instance filteredInstance) throws Exception {
		double predicted = classifier.classifyInstance(filteredInstance);
		String predictedClassName = dataset.classAttribute().value((int) predicted);
		return predictedClassName;
	}

	private Instance filterInstance(Instance instance) throws Exception {
		filter.input(instance);
		Instance filteredInstance = filter.output();
		return filteredInstance;
	}

	private HashMap<String, Double> buildDistributionMap(double[] distribution) {
		HashMap<String, Double> response = new HashMap<String, Double>();
		String[] categoriesNames = AvaibleCategories.names();
		if (distribution.length != categoriesNames.length) {
			return null;
		}
		// zip(distribution, names)
		for (int i = 0; i < distribution.length; i++) {
			response.put(categoriesNames[i], distribution[i]);
		}
		return response;
	}

	/**
	 * 1. Initialize filter and tell it about the input format </br>
	 * 2. Generate word counts from the training data </br>
	 * 3. Rebuild classifier. </br>
	 * 
	 * @throws Exception
	 */
	private void rebuildClassifier() throws Exception {
		filter.setInputFormat(dataset);
		Instances filteredData  = Filter.useFilter(dataset, filter);
		classifier.buildClassifier(filteredData);
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