package utils.textSearch;

import java.util.HashMap;
import models.entities.Tweet;


public class Example {

	public static void main(String[] a) {
		Tweet t1 = new Tweet("Hola Caracola");
		Tweet t2 = new Tweet("Eres un xixi; dijo el mayor xixi de todos");
		Tweet t3 = new Tweet("xixi para todos");

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
