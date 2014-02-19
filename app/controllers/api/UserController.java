package controllers.api;

import play.mvc.Controller;
import play.mvc.Result;


/**
 * API definition for users
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class UserController extends Controller {

	/**
	 * GET /api/users/
	 *
	 * @return (Result) index page
	 */
	public static Result index() {
		return redirect("/api/users/help");
	}

	/**
	 * GET /api/users/:id
	 *
	 * @param id (String) which user
	 * @return (Result) JSON format
	 */
	public static Result show(String id) {
		return TODO;
	}

}
