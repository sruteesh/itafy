package utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author m.artero@estumail.ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class ParamsHelper {
	private final Map<String, String> params;

	/** @see Stackoverflow #15907996 */
	public ParamsHelper(Map<String, String[]> map) {
		params = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			final String key = entry.getKey();
			//String value = Arrays.toString(entry.getValue());
			String value = entry.getValue()[0];
			//String[] cleanedValues = removeDuplicates(map.get(key));
			params.put(key, value);
		}
	}

	private String[] removeDuplicates(String[] values) {
		//TODO
		return values;
	}

	public boolean areEmptyParams() {
		return params.isEmpty();
	}

	public String get(String key) {
		System.out.println(key + "  =>  " + params.get(key)); //XXX
		return params.get(key);
	}

	public boolean has(String key) {
		return params.get(key) != null;
	}

}
