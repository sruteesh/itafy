package utils.helpers;

import java.util.HashMap;
import java.util.Map;
import models.categories.Category;
import models.geoLocation.Area;
import models.geoLocation.AvaibleLocations;
import twitter4j.GeoLocation;


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

	public boolean has(String key) {
		return params.get(key) != null;
	}

	public Area defineArea() {
		Area area = null;
		if (has("area")) {
			AvaibleLocations trustedLocation = AvaibleLocations.asLocation(params.get("area"));
			area = Area.createLocation(trustedLocation);
		} else if ((has("latitude")) && (has("longitude")) && has("radio")) {
			double latitude = Double.valueOf(params.get("latitude")).doubleValue();
			double longitude = Double.valueOf(params.get("longitude")).doubleValue();
			double radio = Double.valueOf(params.get("radio")).doubleValue();
			GeoLocation coordenates = new GeoLocation(latitude, longitude);
			area = Area.createLocation(coordenates, radio);
		}
		return area;
	}

	// FIXME return Enum instead of String
	public String defineGenre() {
		String genre = null;
		if (has("genre")) {
			genre = params.get("genre");
		}
		return genre;
	}

	public String defineUser() {
		String userName = null;
		if (has("user")) {
			userName = params.get("user");
		}
		return userName;
	}

	// XXX check this
	public Category defineCategory() {
		Category category = null;
		if (has("category")) {
			Category.createCategory(params.get("category"));
		}
		return category;
	}

}
