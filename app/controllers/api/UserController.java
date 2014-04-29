package controllers.api;

import java.util.ArrayList;
import models.data.UserData;
import models.entities.User;
import models.geoLocation.Area;
import models.geoLocation.AvaibleLocations;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import twitter4j.GeoLocation;
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
		Area area = defineAreaFromParams(params);
		String genre = defineGenreFromParams(params);
		String userName = defineUserFromParams(params);
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


	// private

	private static Area defineAreaFromParams(ParamsHelper params) {
		Area area = null;
		if (params.has("area")) {
			AvaibleLocations trustedLocation = AvaibleLocations.asLocation(params.get("area"));
			area = Area.createLocation(trustedLocation);
		} else if ((params.has("latitude")) && (params.has("longitude")) && params.has("radio")) {
			double latitude = Double.valueOf(params.get("latitude")).doubleValue();
			double longitude = Double.valueOf(params.get("longitude")).doubleValue();
			double radio = Double.valueOf(params.get("radio")).doubleValue();
			GeoLocation coordenates = new GeoLocation(latitude, longitude);
			area = Area.createLocation(coordenates, radio);
		}
		return area;
	}

	private static String defineGenreFromParams(ParamsHelper params) {
		String genre = null;
		if (params.has("genre")) {
			genre = params.get("genre");
		}
		return genre;
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

	private static String defineUserFromParams(ParamsHelper params) {
		String userName = null;
		if (params.has("user")) {
			userName = params.get("user");
		}
		return userName;
	}

}
