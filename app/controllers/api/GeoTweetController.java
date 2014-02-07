package controllers.api;

import java.util.ArrayList;

import models.GeoTweet;
import models.data.GeoTweetData;
import models.definitions.Location;

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
   * GET /apo/geoTweets/area/:area
   * 
   * @param area which area
   * @return (Result) JSON format
   */
  public static Result area(String area) {
    JsonNode response;

    if (isKnownLocation(area)) {
      ArrayList<Object> geoTweets = GeoTweetData.getAllGeoTweets(area);
      response = Helper.asJson(geoTweets);
    } else {
      ArrayList<String> avaibleLocations = Location.avaibleLocations();
      response = Helper.asJson(avaibleLocations);
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

  private static boolean isKnownLocation(String queryArea) {
    if (Location.avaibleLocations().contains(queryArea)) {
      return true;
    }
    return false;
  }
}

