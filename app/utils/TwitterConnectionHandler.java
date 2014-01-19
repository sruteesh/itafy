package utils;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class TwitterConnectionHandler {

	private static Twitter apiRest = null;
	private static TwitterStream streaming = null;

	public TwitterConnectionHandler() {

	}

	public static Twitter getApiRest() {
		if (apiRest == null) {
			TwitterFactory twitterFactory = new TwitterFactory();
			apiRest = twitterFactory.getInstance();
		}

		return apiRest;
	}

	public static TwitterStream getStreaming() {
		if (streaming == null) {
			streaming = new TwitterStreamFactory().getInstance();
		}

		return streaming;
	}
}
