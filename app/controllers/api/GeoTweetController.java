package controllers.api;

import java.util.ArrayList;
import models.categories.AvaibleCategories;
import models.data.GeoTweetData;
import models.entities.GeoTweet;
import models.geoLocation.AvaibleLocations;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Helper;

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
		AvaibleLocations trustedArea = AvaibleLocations.asLocation(request().getQueryString("area"));
		AvaibleCategories trustedCategory = AvaibleCategories.asCategory(request().getQueryString("category"));

		ArrayList<Object> geoTweets;
		if ((trustedArea != null) && (trustedCategory != null)) {
			geoTweets = GeoTweetData.getAllGeoTweets(trustedArea, trustedCategory);
		} else if (trustedArea != null) {
			geoTweets = GeoTweetData.getAllGeoTweets(trustedArea);
		} else if (trustedCategory != null) {
			geoTweets = GeoTweetData.getAllGeoTweets(trustedCategory);
		} else {
			geoTweets = GeoTweetData.getAllGeoTweets();
		}

		JsonNode response = Helper.asJson(geoTweets);
		return ok(response);
	}

	/**
	 * GET /api/geotweets/show/:id
	 *
	 * @param id (String) which geoTweet
	 * @return (Result) JSON format
	 */
	public static Result show(String id) {
		GeoTweet geoTweet = GeoTweetData.getGeoTweetById(id);
		JsonNode response = Helper.asJson(geoTweet);
		return ok(response);
	}

}
