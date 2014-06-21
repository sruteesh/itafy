package controllers.api;

import java.util.HashMap;

import models.data.TweetData;
import play.mvc.Result;
import controllers.BaseController;

public class TweetController extends BaseController {

	public static Result index() {
		return TODO;
	}

	public static Result show(String id) {
		return TODO;
	}

	public static Result perMinute() {
		HashMap<String, Object> response = TweetData.getPerMinute();

		return generateResultFromHashMapResponse(response);
	}

	public static Result getCategoriesPerPercentage() {
		return TODO;
	}

	public static Result getGendersPerPercentage() {
		return TODO;
	}

}
