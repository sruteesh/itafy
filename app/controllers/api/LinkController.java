package controllers.api;

import java.util.ArrayList;
import models.categories.Category;
import models.data.LinkData;
import models.entities.Link;
import models.geoLocation.Area;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import utils.helpers.JsonHelper;
import utils.helpers.ParamsHelper;


/**
 * API definition for links
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class LinkController extends Controller {

	/**
	 * GET /api/links/
	 * 
	 * Possible queries
	 * <ul>
	 *  <li> area
	 *  <li> category
	 * </ul>
	 * 
	 * @return (Result) index page
	 */
	public static Result index() {
		ParamsHelper params = new ParamsHelper(request().queryString());
		Area area = params.defineArea();
		Category category = params.defineCategory();
		ArrayList<Object> links = executeQueryDependingOn(area, category);
		JsonNode response = JsonHelper.asJson(links);
		return ok(response);
	}


	/**
	 * GET /api/links/:id
	 *
	 * @param id (String) which link
	 * @return (Result) JSON format
	 */
	public static Result show(String id) {
		Link link = LinkData.findLink(id);
		JsonNode response = JsonHelper.asJson(link);
		return ok(response);
	}


	private static ArrayList<Object> executeQueryDependingOn(Area area, Category category) {
		ArrayList<Object> links;
		if ((area != null) && (category != null)) {
			links = LinkData.getLinks(area, category);
		} else if (area != null) {
			links = LinkData.getLinks(area);
		} else if (category != null) {
			links = LinkData.getLinks(category);
		} else {
			links = LinkData.getLinks();
		}
		return links;
	}

}
