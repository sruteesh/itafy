package controllers.api;

import play.mvc.Result;


/**
 * API definition for words
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
public class WordController extends ApiController {

  /**
   * GET /api/words/
   *
   * @return (Result) index page
   */
  public static Result index() {
    return redirect("/api/words/help");
  }

  /**
   * GET /api/words/help
   *
   * @return (Result) static help page
   */
  public static Result help() {
    return TODO;
  }

  /**
   * GET /api/words/all
   *
   * @return (Result) JSON format
   */
  public static Result listAll() {
    return TODO;
  }

  /**
   * GET /api/words/area/:area
   *
   * @param area which area
   * @return (Result) JSON format
   */
  public static Result area(String area) {
    return TODO;
  }

  /**
   * GET /api/words/category/:category
   *
   * @param category which category
   * @return (Result) JSON format
   */
  public static Result category(String category) {
    return TODO;
  }

  /**
   * GET /api/words/show/:id
   *
   * @param id which geoTweet
   * @return (Result) JSON format
   */
  public static Result show(String id) {
    return TODO;
  }

}
