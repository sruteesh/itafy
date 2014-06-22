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
			Cache.set("tweets.per-minute", cachedTweets, 120);
		}

		return generateResultFromHashMapResponse(cachedTweets);
	}

	public static Result getCategoriesPerPercentage() {
		HashMap<String, Object> cachedCategories = (HashMap<String, Object>) Cache.get("categories.per-percentage");

		if (cachedCategories == null) {
			cachedCategories = TweetData.getCategoriesPerPercentage();
			Cache.set("categories.per-percentage", cachedCategories, 120);
		}

		return generateResultFromHashMapResponse(cachedCategories);
	}

	public static Result getGendersPerPercentage() {
		HashMap<String, Object> cachedGenders = (HashMap<String, Object>) Cache.get("genders.per-percentage");

		if (cachedGenders == null) {
			cachedGenders = TweetData.getGendersPerPercentage();
			Cache.set("genders.per-percentage", cachedGenders, 120);
		}

		return generateResultFromHashMapResponse(cachedGenders);
	}

}
