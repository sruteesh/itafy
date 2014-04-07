package controllers;

import java.util.HashMap;
import models.geoLocation.GeoTweetsCounter;
import play.Routes;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * @see http://franzgranlund.wordpress.com/2012/03/29/play-framework-2-0-javascriptrouter-in-java/
 * @see http://www.objectify.be/wordpress/?p=734
 * @author m.artero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class Application extends Controller {

	public static Result index() {
		return ok(index.render("0"));
	}

	public static Result javascriptRoutes() {
		response().setContentType("text/javascript");
		return ok(Routes.javascriptRouter("jsRoutes",
				// Routes
				controllers.routes.javascript.Application.peopleCount()
				));
	}

	// TODO: String location
	public static Result peopleCount() {
		GeoTweetsCounter counter = new GeoTweetsCounter();
		HashMap<String, Long> count = counter.recountGeoTweets();
		String tweetsInMadrid = String.valueOf(count.get("madrid"));
		return ok(index.render(tweetsInMadrid));
	}

}
