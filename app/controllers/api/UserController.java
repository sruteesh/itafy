package controllers.api;

import java.util.ArrayList;
import models.data.UserData;
import models.entities.User;
import models.geoLocation.Area;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import utils.helpers.JsonHelper;
import utils.helpers.ParamsHelper;


/**
 * API definition for users
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class UserController extends Controller {

	/**
	 * <pre>
	 * GET /api/users/
	 * GET /api/users/? area=a
	 * GET /api/users/? genre=g
	 * GET /api/users/? latitude=lat & longitude=lon & radio=r
	 * GET /api/users/? user=u
	 * 
	 * GET /api/users/? area=a & genre=g
	 * GET /api/users/? latitude=lat & longitude=lon & radio = r & genre=g
	 * GET /api/users/? area=a & user=u
	 * GET /api/users/? latitude=lat & longitude=lon & radio=r & user=u
	 * </pre>
	 * 
	 * TODO genre as a enum instead of a String
	 * FIXME possible multiple parameters: remove the last "get(0)"
	 */
	public static Result index() {
		ParamsHelper params = new ParamsHelper(request().queryString());
		Area area = params.defineArea();
		String genre = params.defineGenre();
		String userName = params.defineUser();
		ArrayList<Object> users = executeQueryDependingOn(area, genre, userName);
		JsonNode response = JsonHelper.asJson(users);
		return ok(response);
	}

	/**
	 * GET /api/users/:id
	 */
	public static Result show(String id) {
		User user = UserData.findUser(id);
		if (user == null) {
			try {
				user = UserData.findUser(Long.valueOf(id).longValue());
			} catch (NumberFormatException e) {
				// no mongo's id, no twitter id;
				// nothing to show
			}
		}
		JsonNode response = JsonHelper.asJson(user);
		return ok(response);
	}


	// TODO : use userName
	private static ArrayList<Object> executeQueryDependingOn(Area area, String genre, String userName) {
		ArrayList<Object> users = new ArrayList<Object>();
		if ((area != null) && (genre != null)) {
			users = UserData.getUsers(area, genre);
		} else if (area != null) {
			users = UserData.getUsers(area);
		} else if (genre != null) {
			users = UserData.getUsers(genre);
		} else {
			users = UserData.getUsers();
		}
		return users;
	}

}
