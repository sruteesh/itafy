package controllers.api;

import java.util.ArrayList;

import models.Category.AvaibleCategories;
import models.GeoTweet;
import models.Location.AvaibleLocations;
import models.data.GeoTweetData;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import utils.Helper;

/**
 * API definition for GeoTweets
 * 
 * @author martero@ucm.es & raul.marcos@ucm.es
 */
public class GeoTweetController extends Controller {

  /**
   * GET /api/geotweets/index
   * 
   * @return (Result) JSON format
   */
  public static Result index() {
    ArrayList<Object> geoTweets = GeoTweetData.getAllGeoTweets();
    JsonNode response = Helper.asJson(geoTweets);
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


  private static boolean isKnownLocation(AvaibleLocations location) {
    return location != null;
  }

  private static ArrayList<String> avaibleLocations() {
    ArrayList<String> avaibleLocations = new ArrayList<String>();
    avaibleLocations.add("madrid");
    return avaibleLocations;
  }

  private static boolean isKnownCategory(AvaibleCategories category) {
    return category != null;
  }

  private static ArrayList<String> avaibleCategories() {
    ArrayList<String> avaibleCategories = new ArrayList<String>();
    avaibleCategories.add("politica");
    avaibleCategories.add("economia");
    avaibleCategories.add("deportes");
    avaibleCategories.add("cultura");
    return avaibleCategories;
  }
}

