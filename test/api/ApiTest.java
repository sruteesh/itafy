package api;

import java.util.ArrayList;
import models.data.GeoTweetData;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import controllers.api.GeoTweetController;


/**
 * Environment for test <code>controllers.api</code>
 * <p>
 * <code>
 * | tweet # | location # | <br>
 * |:------- |:---------- | <br>
 * | tweet 1 | location 1 | <br>
 * | tweet 2 | location 1 | <br>
 * | tweet 3 | location 2 | <br>
 * | tweet 4 | location 3 | <br>
 * </code>
 * <p>
 * Locations in Madrid
 * <ul>
 *  <li> location 1
 *  <li> location 2
 * </ul>
 * <p>
 * Locations out of Madrid
 * <ul>
 *  <li> location 3
 * </ul>
 * 
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 * @see ApiFixtures
 * @see ApiController
 * @see GeoTweetController
 */
public class ApiTest {

	/* tear down: must remove fake tweets */
	private static ArrayList<String> fakeTweets = new ArrayList<String>();

	/**
	 * Prepare test environment.
	 * <ol>
	 *  <li> create fake tweets
	 * </ol>
	 * Note: executed once at begining
	 */
	@BeforeClass
	public static void tearUp() {
		fakeTweets.add(createFakeTweet(1));
		fakeTweets.add(createFakeTweet(2));
		fakeTweets.add(createFakeTweet(3));
		fakeTweets.add(createFakeTweet(4));
	}

	/**
	 * Clean up test environment.
	 * <ol>
	 *  <li> Remove fake tweets
	 * </ol>
	 * Note: executed once at the end
	 */
	@AfterClass
	public static void tearDown() {
		for (String mustDelete : fakeTweets) {
			GeoTweetData.destroyGeoTweet(mustDelete);
		}
	}

	@Test
	public void getGeoTweetsForMadrid() {
		//    Result result = Helpers.callAction(controllers.api.routes.ref.GeoTweetController.area("madrid"));
		//    String response = contentAsString(result);
		//    assertThat(response).isEqualTo("");
	}


	private static String createFakeTweet(int tweetNumber) {
		String tweetId;

		switch (tweetNumber) {
			case 1:
				tweetId = GeoTweetData.saveGeoTweet(ApiFixtures.TWEET_1_ID, ApiFixtures.LOCATION_1);
				ApiFixtures.TWEET_1 = tweetId;
				return tweetId;

			case 2:
				tweetId = GeoTweetData.saveGeoTweet(ApiFixtures.TWEET_2_ID, ApiFixtures.LOCATION_1);
				ApiFixtures.TWEET_2 = tweetId;
				return tweetId;

			case 3:
				tweetId = GeoTweetData.saveGeoTweet(ApiFixtures.TWEET_3_ID, ApiFixtures.LOCATION_2);
				fakeTweets.add(tweetId);
				ApiFixtures.TWEET_3 = tweetId;
				return tweetId;

			case 4:
				tweetId = GeoTweetData.saveGeoTweet(ApiFixtures.TWEET_4_ID, ApiFixtures.LOCATION_3);
				ApiFixtures.TWEET_4 = tweetId;
				return tweetId;

			default:
				System.out.println("Error (TEST) createFakeTweet: " + tweetNumber);
				return null;
		}
	}

}
