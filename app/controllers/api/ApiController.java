package controllers.api;

import java.util.ArrayList;
import models.categories.AvaibleCategories;
import models.geoLocation.AvaibleLocations;
import play.mvc.Controller;

/**
 * Common logic for controllers of the public api
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public abstract class ApiController extends Controller {

	protected static AvaibleLocations translateToKnownLocation(String location) {
		if (location == null) {
			return null;
		}
		return AvaibleLocations.asLocation(location);
	}

	protected static ArrayList<String> avaibleLocations() {
		ArrayList<String> avaibleLocations = new ArrayList<String>();
		avaibleLocations.add("madrid");
		return avaibleLocations;
	}

	protected static AvaibleCategories translateToKnownCategory(String category) {
		if (category == null) {
			return null;
		}
		return AvaibleCategories.asCategory(category);
	}

	protected static ArrayList<String> avaibleCategories() {
		ArrayList<String> avaibleCategories = new ArrayList<String>();
		avaibleCategories.add("politica");
		avaibleCategories.add("economia");
		avaibleCategories.add("deportes");
		avaibleCategories.add("cultura");
		return avaibleCategories;
	}

}
