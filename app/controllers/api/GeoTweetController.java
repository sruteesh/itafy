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
 *
 * Inherited methods
 *  - boolean isKnownLocation(AvaibleLocations location)
 *  - ArrayList avaibleLocations()
 *  - isKnownCategory(AvaibleCategories category)
 *  - ArrayList avaibleCategories()
 *
 * @see confg/routes
 * @author martero@ucm.es & raul.marcos@ucm.es
 */
public class GeoTweetController extends ApiController {

  /**
   * GET /api/geotweets/
   *
   * @return (Result) index page
   */
  public static Result index(){
    return redirect("/api/geotweets/help");
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
   * GET /api/geotweets/all
   *
   * @return (Result) JSON format
   */
  public static Result listAll() {
    ArrayList<Object> geoTweets = GeoTweetData.getAllGeoTweets();
    JsonNode response = Helper.asJson(geoTweets);
    return ok(response);
  }

  /**
   * GET /api/geoTweets/area/:area
   *
   * @param area which area
   * @return (Result) JSON format
   */
  public static Result area(String area) {
    JsonNode response;
    AvaibleLocations trustedArea = AvaibleLocations.asLocation(area);

    if (isKnownLocation(trustedArea)) {
      ArrayList<Object> geoTweets = GeoTweetData.getAllGeoTweets(trustedArea);
      response = Helper.asJson(geoTweets);
    } else {
      ArrayList<String> avaibleLocations = avaibleLocations();
      response = Helper.asJson(avaibleLocations);
    }

    return ok(response);
  }

  /**
   * GET /api/geoTweets/category/:category
   *
   * @param category which category
   * @return (Result) JSON format
   */
  public static Result category(String category) {
    JsonNode response;
    AvaibleCategories trustedCategory = AvaibleCategories.asCategory(category);

    if (isKnownCategory(trustedCategory)) {
      ArrayList<Object> geoTweets = GeoTweetData.getAllGeoTweets(trustedCategory);
      response = Helper.asJson(geoTweets);
    } else {
      ArrayList<String> avaibleCategories = avaibleCategories();
      response = Helper.asJson(avaibleCategories);
    }

    return ok(response);
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
