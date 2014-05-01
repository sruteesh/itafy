package utils.helpers;

import java.util.ArrayList;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Auxiliar class which defines possible useful common methods for manipulating JSONs
 * <p>
 * Note: all methods would be static; not reason to instanciate or extend this class
 * 
 * @see Stackoverflow #1844355
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public final class JsonHelper {

	/**
	 * Suppress default constructor for noninstantiability; "Effective Java" Item 4.
	 * @throws AssertionError
	 */
	private JsonHelper() {
		throw new AssertionError();
	}

	/** useful for JSON manipulation */
	private static final ObjectMapper mapper = new ObjectMapper();


	/**
	 * Converts the parameter object to JSON.
	 * 
	 * @param object expected instance of any model
	 * @return (JsonNode)
	 */
	public static JsonNode asJson(Object object) {
		return JsonHelper.mapper.valueToTree(object);
	}


	/**
	 * Returns an empty JSON "{}"
	 * 
	 * @return (JsonNode) empty JSON
	 */
	public static JsonNode emptyJson() {
		ArrayList<Object> emptyArray = new ArrayList<Object>();
		return JsonHelper.mapper.valueToTree(emptyArray);
	}

}
