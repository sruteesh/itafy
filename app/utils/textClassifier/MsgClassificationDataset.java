package utils.textClassifier;

import models.categories.AvaibleCategories;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;

/**
 * Contains one static instance of a Weka's training dataset with the following properties:
 * 
 * <pre>
 * Name: MessageClassification
 * Attributes: 2
 * Index name: "class"
 * 
 * Attributes
 * --------------
 * message: String
 * class (index): Enum
 * 
 * | message 1 | class |
 * | message 2 | class |
 * |    ...    |  ...  |
 * | message N | class |
 * </pre>
 * 
 * <p>
 * The class attribute takes it's values from {@link  models.categories.AvaibleCategories AvaibleCategories}
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see models.categories.AvaibleCategories
 */
public class MsgClassificationDataset {

	private static Instances dataset = null;
	public static Instances getDataset() {
		if (dataset == null) {
			dataset = buildDataset();
		}
		return dataset;
	}

	protected static final int INITIAL_CAPACITY = 100;

	/**
	 * Create the dataset with initial capacity, and set "class" attribute as index.
	 * @return dataset
	 */
	protected static Instances buildDataset() {
		FastVector attributes = new FastVector(2);
		attributes.addElement(new Attribute(MsgClassificationConstants.MESSAGE, (FastVector) null)); // attributes.message
		FastVector classValues = new FastVector(AvaibleCategories.values().length);
		for (String categoryName : AvaibleCategories.names()) {
			classValues.addElement(categoryName);
		}
		attributes.addElement(new Attribute(MsgClassificationConstants.CLASS, classValues)); // attributes.class
		Instances response = new Instances(MsgClassificationConstants.DATASET_NAME, attributes, INITIAL_CAPACITY);
		response.setClassIndex(response.numAttributes() - 1);
		return response;
	}

}
