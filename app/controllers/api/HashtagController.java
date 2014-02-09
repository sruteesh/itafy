package controllers.api;

import play.mvc.Result;


/**
 * API definition for hashtags
 *
 * Inherited methods
 *  - boolean isKnownLocation(AvaibleLocations location)
 *  - ArrayList avaibleLocations()
 *  - isKnownCategory(AvaibleCategories category)
 *  - ArrayList avaibleCategories()
 *
 * @see conf/routes
 * @author martero@ucm.es & raul.maarcos@ucm.es
 */
public class HashtagController extends ApiController {

  /**
   * GET /api/hashtags/
   *
   * @return (Result) index page
   */
  public static Result index() {
    return redirect("/api/hashtags/help");
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
   * GET /api/hashtags/all
   *
   * @return (Result) JSON format
   */
  public static Result listAll() {
    return TODO;
  }

  /**
   * GET /api/hashtags/area/:area
   *
   * @param area which area
   * @return (Result) JSON format
   */
  public static Result area(String area) {
    return TODO;
  }

  /**
   * GET /api/hashtags/category/:category
   *
   * @param category which category
   * @return (Result) JSON format
   */
  public static Result category(String category) {
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
