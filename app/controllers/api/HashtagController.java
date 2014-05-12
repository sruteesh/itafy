package controllers.api;

import java.util.ArrayList;
import models.categories.Category;
import models.data.HashtagData;
import models.geoLocation.Area;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import utils.helpers.JsonHelper;
import utils.helpers.ParamsHelper;


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
		ParamsHelper params = new ParamsHelper(request().queryString());
		Area area = params.defineArea();
		Category category = params.defineCategory();
		ArrayList<Object> hashtags = executeQueryDependingOn(area, category);
		JsonNode response = JsonHelper.asJson(hashtags);
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


	private static ArrayList<Object> executeQueryDependingOn(Area area, Category category) {
		ArrayList<Object> hashtags;
		if ((area != null) && (category != null)) {
			hashtags = HashtagData.getHashtags(area, category);
		} else if (area != null) {
			hashtags = HashtagData.getHashtags(area);
		} else if (category != null) {
			hashtags = HashtagData.getHashtags(category);
		} else {
			hashtags = HashtagData.getHashtags();
		}
		return hashtags;
	}

}
