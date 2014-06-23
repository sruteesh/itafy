package controllers.api;

import java.util.HashMap;

import models.data.TweetData;
import play.cache.Cache;
import play.mvc.Result;
import controllers.BaseController;

public class TweetController extends BaseController {

	private static final int TEN_MINUTES_IN_SECONDS = 60 * 10;

	public static Result index() {
		return TODO;
	}

	public static Result show(String id) {
		return TODO;
	}

	public static Result perMinute() {
		HashMap<String, Object> cachedTweets = (HashMap<String, Object>) Cache.get("tweets.per-minute");
		if (cachedTweets == null) {
			cachedTweets = warmTweetsCache();
		}

		return generateResultFromHashMapResponse(cachedTweets);
	}

	public static HashMap<String, Object> warmTweetsCache() {
		HashMap<String, Object> cachedTweets = TweetData.getPerMinute();
		Cache.set("tweets.per-minute", cachedTweets, TEN_MINUTES_IN_SECONDS);

		return cachedTweets;
	}

	public static Result getCategoriesPerPercentage() {
		HashMap<String, Object> cachedCategories = (HashMap<String, Object>) Cache.get("categories.per-percentage");

		if (cachedCategories == null) {
			cachedCategories = warmCategoriesCache();
		}

		return generateResultFromHashMapResponse(cachedCategories);
	}

	public static HashMap<String, Object> warmCategoriesCache() {
		HashMap<String, Object> cachedCategories = TweetData.getCategoriesPerPercentage();
		Cache.set("categories.per-percentage", cachedCategories, TEN_MINUTES_IN_SECONDS);

		return cachedCategories;
	}

	public static Result getGendersPerPercentage() {
		HashMap<String, Object> cachedGenders = (HashMap<String, Object>) Cache.get("genders.per-percentage");

		if (cachedGenders == null) {
			cachedGenders = warmGendersCache();
		}

		return generateResultFromHashMapResponse(cachedGenders);
	}

	public static HashMap<String, Object> warmGendersCache() {
		HashMap<String, Object> cachedGenders = TweetData.getGendersPerPercentage();
		Cache.set("genders.per-percentage", cachedGenders, TEN_MINUTES_IN_SECONDS);

		return cachedGenders;
	}

}
