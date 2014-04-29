package controllers.api;

import java.util.ArrayList;
import models.categories.AvaibleCategories;
import models.data.GeoTweetData;
import models.entities.GeoTweet;
import models.geoLocation.AvaibleLocations;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import utils.helpers.JsonHelper;

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
		AvaibleCategories trustedCategory = AvaibleCategories.valueOf(request().getQueryString("category"));

		ArrayList<Object> geoTweets;
		if ((trustedArea != null) && (trustedCategory != null)) {
			geoTweets = GeoTweetData.getGeoTweets(trustedArea, trustedCategory);
		} else if (trustedArea != null) {
			geoTweets = GeoTweetData.getGeoTweets(trustedArea);
		} else if (trustedCategory != null) {
			geoTweets = GeoTweetData.getGeoTweets(trustedCategory);
		} else {
			geoTweets = GeoTweetData.getGeoTweets();
		}

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

}
