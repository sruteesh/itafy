package controllers.api;

import play.mvc.Result;


/**
 * API definition for hashtags.
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * 
 * @see ApiController
 * @see conf/routes
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
	 * GET /api/hashtags/show/:id
	 *
	 * @param id which geoTweet
	 * @return (Result) JSON format
	 */
	public static Result show(String id) {
		return TODO;
	}

}
