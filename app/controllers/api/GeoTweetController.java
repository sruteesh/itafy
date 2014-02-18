package controllers.api;

import java.util.ArrayList;
import models.Category.AvaibleCategories;
import models.GeoTweet;
import models.Location.AvaibleLocations;
import models.data.GeoTweetData;
import org.codehaus.jackson.JsonNode;
import play.mvc.Result;
import utils.Helper;

/**
 * API definition for geoTweets.
 * <p>
 * Inherited methods
 * <ul>
 *  <li> <code>boolean isKnownLocation(AvaibleLocations)</code>
 *  <li> <code>ArrayList avaibleLocations()</code>
 *  <li> <code>isKnownCategory(AvaibleCategories)</code>
 *  <li> <code>ArrayList avaibleCategories()</code>
 * </ul>
 *
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 * @see config/routes
 * @see ApiController
 */
public class GeoTweetController extends ApiController {

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
	public static Result index(){
		AvaibleLocations trustedArea = isKnownLocation(request().getQueryString("area"));
		AvaibleCategories trustedCategory = isKnownCategory(request().getQueryString("category"));

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
	 * GET /api/geotweets/help
	 *
	 * @return (Result) static help page
	 */
	public static Result help() {
		return TODO;
	}

	/**
	 * GET /api/geotweets/show/:id
	 *
	 * @param id which geoTweet
	 * @return (Result) JSON format
	 */
	public static Result show(String id) {
		GeoTweet geoTweet = GeoTweetData.getGeoTweetById(id);
		JsonNode response = Helper.asJson(geoTweet);
		return ok(response);
	}

}
