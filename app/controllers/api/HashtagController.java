package controllers.api;

import java.util.ArrayList;
import models.categories.AvaibleCategories;
import models.data.HashtagData;
import models.geoLocation.AvaibleLocations;
import org.codehaus.jackson.JsonNode;
import play.mvc.Result;
import utils.Helper;


/**
 * API definition for hashtags.
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * 
 * @see ApiController
 */
public class HashtagController extends ApiController {

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
		AvaibleLocations trustedArea = translateToKnownLocation(request().getQueryString("area"));
		AvaibleCategories trustedCategory = translateToKnownCategory(request().getQueryString("category"));

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
	 * GET /api/hashtags/help
	 *
	 * @return (Result) static help page
	 */
	public static Result help() {
		return TODO;
	}

	/**
	 * GET /api/hashtags/show/:id
	 *
	 * @param id which geoTweet
	 * @return (Result) JSON format
	 */
	public static Result show(String id) {
		return TODO;
	}

}
