package controllers.api;

import play.mvc.Controller;
import play.mvc.Result;


/**
 * API definition for words
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class WordController extends Controller {

	/**
	 * GET /api/words/
	 *
	 * @return (Result) index page
	 */
	public static Result index() {
		return redirect("/api/words/help");
	}

	/**
	 * GET /api/words/:id
	 *
	 * @param id (String) which geoTweet
	 * @return (Result) JSON format
	 */
	public static Result show(String id) {
		return TODO;
	}

}
