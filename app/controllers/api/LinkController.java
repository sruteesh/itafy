package controllers.api;

import java.util.ArrayList;
import models.categories.AvaibleCategories;
import models.data.LinkData;
import models.entities.Link;
import models.geoLocation.AvaibleLocations;
import org.codehaus.jackson.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import utils.helpers.JsonHelper;


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
		AvaibleLocations trustedArea = AvaibleLocations.asLocation(request().getQueryString("area"));
		AvaibleCategories trustedCategory = AvaibleCategories.valueOf(request().getQueryString("category"));

		ArrayList<Object> links;
		if ((trustedArea != null) && (trustedCategory != null)) {
			links = LinkData.getAllLinks(trustedArea, trustedCategory);
		} else if (trustedArea != null) {
			links = LinkData.getAllLinks(trustedArea);
		} else if (trustedCategory != null) {
			links = LinkData.getAllLinks(trustedCategory);
		} else {
			links = LinkData.getAllLinks();
		}

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

}
