package controllers.api;

import play.mvc.Result;


/**
 * API definition for users
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
public class UserController extends ApiController {

  /**
   * GET /api/users/
   *
   * @return (Result) index page
   */
  public static Result index() {
    return redirect("/api/users/help");
  }

  /**
   * GET /api/users/help
   *
   * @return (Result) static help page
   */
  public static Result help() {
    return TODO;
  }

  /**
   * GET /api/users/all
   *
   * @return (Result) JSON format
   */
  public static Result listAll() {
    return TODO;
  }

  /**
   * GET /api/users/area/:area
   *
   * @param area which area
   * @return (Result) JSON format
   */
  public static Result area(String area) {
    return TODO;
  }

  /**
   * GET /api/users/category/:category
   *
   * @param category which category
   * @return (Result) JSON format
   */
  public static Result category(String category) {
    return TODO;
  }

  /**
   * GET /api/users/show/:id
   *
   * @param id which geoTweet
   * @return (Result) JSON format
   */
  public static Result show(String id) {
    return TODO;
  }

}
