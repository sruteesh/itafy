package controllers.api;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * API definition for static help pages.
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
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
	 * GET /api/geotweets/areas
	 * GET /api/hashtags/areas
	 * GET /api/links/areas
	 * GET /api/users/areas
	 * GET /api/words/areas
	 *
	 * @return (Result) static help page
	 */
	public static Result listAreas() {
		return TODO;
	}

	/**
	 * GET /api/geotweets/categories
	 * GET /api/hashtags/categories
	 * GET /api/links/categories
	 * GET /api/users/categories
	 * GET /api/words/categories
	 *
	 * @return (Result) static help page
	 */
	public static Result listCategories() {
		return TODO;
	}

}
