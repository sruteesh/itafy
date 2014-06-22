package controllers.api;

import java.util.HashMap;

import models.data.TweetData;
import play.cache.Cache;
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
		HashMap<String, Object> cachedTweets = (HashMap<String, Object>) Cache.get("tweets.per-minute");
		if (cachedTweets == null) {
			cachedTweets = TweetData.getPerMinute();
			Cache.set("tweets.per-minute", cachedTweets);
		}

		return generateResultFromHashMapResponse(cachedTweets);
	}

	public static Result getCategoriesPerPercentage() {
		return TODO;
	}

	public static Result getGendersPerPercentage() {
		return TODO;
	}

}
