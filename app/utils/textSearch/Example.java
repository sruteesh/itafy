package utils.textSearch;

import java.util.HashMap;
import models.entities.Tweet;


public class Example {

	public static void main(String[] a) {
		Tweet t1 = Tweet.createTweetWithGeoTweetAndUser("Hola Caracola", "00", "00");
		Tweet t2 = Tweet.createTweetWithGeoTweetAndUser("Eres un xixi; dijo el mayor xixi de todos", "01", "01");
		Tweet t3 = Tweet.createTweetWithGeoTweetAndUser("xixi para todos", "02", "02");

		Index index = new Index();
		index.addTweet(t1);
		index.addTweet(t2);
		index.addTweet(t3);
		index.closeWriter();

		String query = "xixi";
		HashMap<String, Float> results = new HashMap<String, Float>();
		Searcher searcher = new Searcher();
		results = searcher.search(query);

		System.out.println(results.toString());
	}
}
