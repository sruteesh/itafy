package utils.textSearch;

import java.util.HashMap;
import models.data.TweetData;
import models.entities.Tweet;


public class Example {

	public static void main(String[] a) {
		String[] temporalIds = createTweets();
		createIndex(temporalIds);
		HashMap<String, Float> results = query("dinosaurio");
		System.out.println(results.toString());
		destroyTweets(temporalIds);
	}


	// private

	private static String[] createTweets() {
		String[] response = new String[3];
		for (int i = 0; i < 3; i++) {
			Tweet t = Tweet.createTweetWithGeoTweetAndUser(fakeTexts[i], fakeGeoTweetIds[0], fakeUserId);
			response[i] = TweetData.savetweet(t);
			System.out.println(t.getId() + ": " + fakeTexts[i]);
		}
		return response;
	}

	private static void createIndex(String[] ids) {
		Index index = new Index();
		for (String id : ids) {
			index.addTweet(id);
		}
		index.closeWriter();
	}

	private static HashMap<String, Float> query(String query) {
		HashMap<String, Float> results = new HashMap<String, Float>();
		Searcher searcher = new Searcher();
		results = searcher.search(query);
		return results;
	}

	private static void destroyTweets(String[] ids) {
		for (String id : ids) {
			TweetData.destroyTweet(id);
		}
	}


	// lets imagine that the user "e720" wrote these tweets

	private static final String[] fakeGeoTweetIds = {
		"5370be443004241a99c0e720", "5370be443004241a99c0e721", "5370be443004241a99c0e722"
	};

	private static final String[] fakeTexts = {
		"Hola hola caracola", "Eres un dinosaurio; dijo el mayor dinosaurio de todos", "dinosaurio en el zoo"
	};

	private static final String fakeUserId = "5370be443004241a99c0e720";
}
