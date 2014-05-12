package controllers.api;

import java.util.ArrayList;
import models.categories.Category;
import models.data.GeoTweetData;
import models.entities.GeoTweet;
import models.geoLocation.Area;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import utils.helpers.JsonHelper;
import utils.helpers.ParamsHelper;

/**
 * API definition for geoTweets.
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * 
 * @see ApiController
 */
public class GeoTweetController extends Controller {

	/**
	 * GET /api/geotweets/
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
		ArrayList<Object> geoTweets = executeQueryDependingOn(area, category);
		JsonNode response = JsonHelper.asJson(geoTweets);
		return ok(response);
	}

	/**
	 * GET /api/geotweets/show/:id
	 *
	 * @param id (String) which geoTweet
	 * @return (Result) JSON format
	 */
	public static Result show(String id) {
		GeoTweet geoTweet = GeoTweetData.findGeoTweet(id);
		JsonNode response = JsonHelper.asJson(geoTweet);
		return ok(response);
	}

	private static ArrayList<Object> executeQueryDependingOn(Area area, Category category) {
		ArrayList<Object> geoTweets;
		if ((area != null) && (category != null)) {
			geoTweets = GeoTweetData.getGeoTweets(area, category);
		} else if (area != null) {
			geoTweets = GeoTweetData.getGeoTweets(area);
		} else if (category != null) {
			geoTweets = GeoTweetData.getGeoTweets(category);
		} else {
			geoTweets = GeoTweetData.getGeoTweets();
		}
		return geoTweets;
	}

}
