package controllers.api;

import java.util.ArrayList;
import models.categories.AvaibleCategories;
import models.data.HashtagData;
import models.geoLocation.AvaibleLocations;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Helper;


/**
 * API definition for hashtags.
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class HashtagController extends Controller {

	/**
	 * GET /api/hashtags/
	 * 
	 * Possible queries
	 * <ul>
	 *  <li> area
	 *  <li> category
	 * </ul>
	 * 
	 * @return (Result) index page
	 */
	public static Result index() {
		AvaibleLocations trustedArea = AvaibleLocations.asLocation(request().getQueryString("area"));
		AvaibleCategories trustedCategory = AvaibleCategories.asCategory(request().getQueryString("category"));

		ArrayList<Object> hashtags;
		if ((trustedArea != null) && (trustedCategory != null)) {
			hashtags = HashtagData.getAllHashtags(trustedArea, trustedCategory);
		} else if (trustedArea != null) {
			hashtags = HashtagData.getAllHashtags(trustedArea);
		} else if (trustedCategory != null) {
			hashtags = HashtagData.getAllHashtags(trustedCategory);
		} else {
			hashtags = HashtagData.getAllHashtags();
		}

		JsonNode response = Helper.asJson(hashtags);
		return ok(response);
	}

	/**
	 * GET /api/hashtags/:id
	 *
	 * @param id (String) which geoTweet
	 * @return (Result) JSON format
	 */
	public static Result show(String id) {
		return TODO;
	}

}
