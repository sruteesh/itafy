package controllers.api;

import play.mvc.Result;


/**
 * API definition for links
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
public class LinkController extends ApiController {

  /**
   * GET /api/links/
   *
   * @return (Result) index page
   */
  public static Result index() {
    return redirect("/api/links/help");
  }

  /**
   * GET /api/links/help
   *
   * @return (Result) static help page
   */
  public static Result help() {
    return TODO;
  }

  /**
   * GET /api/links/all
   *
   * @return (Result) JSON format
   */
  public static Result listAll() {
    return TODO;
  }

  /**
   * GET /api/links/area/:area
   *
   * @param area which area
   * @return (Result) JSON format
   */
  public static Result area(String area) {
    return TODO;
  }

  /**
   * GET /api/links/category/:category
   *
   * @param category which category
   * @return (Result) JSON format
   */
  public static Result category(String category) {
    return TODO;
  }

  /**
   * GET /api/links/show/:id
   *
   * @param id which geoTweet
   * @return (Result) JSON format
   */
  public static Result show(String id) {
    return TODO;
  }

}
