package controllers.api;

import play.mvc.Controller;
import play.mvc.Result;

public class HelpController extends Controller {

  /**
   * GET /api/
   *
   * @return (Result) index page
   */
  public static Result index() {
    return redirect("/api/help");
  }

  /**
   * GET /api/help
   *
   * @return (Result) static help page
   */
  public static Result help() {
    return TODO;
  }

  /**
   * GET /api/geotweets/area
   * GET /api/hashtags/area
   * GET /api/links/area
   * GET /api/users/area
   * GET /api/words/area
   *
   * @return (Result) static help page
   */
  public static Result listAreas() {
    return TODO;
  }

  /**
   * GET /api/geotweets/category
   * GET /api/hashtags/category
   * GET /api/links/category
   * GET /api/users/category
   * GET /api/words/category
   *
   * @return (Result) static help page
   */
  public static Result listCategories() {
    return TODO;
  }

}
