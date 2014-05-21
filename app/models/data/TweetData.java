package models.data;

import java.util.ArrayList;
import models.entities.Tweet;
import models.entities.User;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import utils.helpers.CollectionHelper;
import controllers.db.DbNames;

public class TweetData extends MongoClientData {
	private static final MongoCollection tweetsCollection = jongoItafy.getCollection(DbNames.TWEETS);

	/** No need to instanciate a <code>TweetData</code> object */
	private TweetData() {}

	/**
	 * Create: saves the tweet into the DB
	 * 
	 * @param tweet
	 * @return Mongo's ObjectId as String
	 */
	public static String savetweet(Tweet tweet) {
		tweetsCollection.save(tweet);
		return tweet.getId();
	}

	/**
	 * Create: creates and saves a new tweet instance into the DB
	 * 
	 * @param text the tweet itself
	 * @param geoTweet Mongo's id as a String
	 * @param user Mongo's id as a String
	 * @return Mongo's ObjectId as String
	 */
	public static String savetweet(String text, String geoTweet, String user) {
		Tweet tweet = Tweet.createTweetWithGeoTweetAndUser(text, geoTweet, user);
		tweetsCollection.save(tweet);
		return tweet.getId();
	}

	/**
	 * Read: returns all the tweets in the DB as generic <code>Object</code>
	 * instances; casting expected.
	 * 
	 * @return tweets or empty list otherwise.
	 */
	public static ArrayList<Object> getTweets() {
		Iterable<Tweet> records = tweetsCollection.find().as(Tweet.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: tweets with the selected link
	 * 
	 * @param id Mongo's id as a String
	 * @return array of tweets with the selected link or empty list otherwise.
	 */
	public static ArrayList<Object> getTweetsWithLink(String id) {
		String query = "{link_ids: #}}";
		Iterable<Tweet> records = tweetsCollection
				.find(query, new ObjectId(id))
				.as(Tweet.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: tweets with the selected word
	 * 
	 * @param id Mongo's id as a String
	 * @return array of tweets with the selected link or empty list otherwise.
	 */
	public static ArrayList<Object> getTweetsWithWord(String id) {
		String query = "{word_ids: #}}";
		Iterable<Tweet> records = tweetsCollection
				.find(query, new ObjectId(id))
				.as(Tweet.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: tweets with the selected hashtag
	 * 
	 * @param id Mongo's id as a String
	 * @return array of tweets with the selected link or empty list otherwise.
	 */
	public static ArrayList<Object> getTweetsWithHashtag(String id) {
		String query = "{hashtag_ids: #}}";
		Iterable<Tweet> records = tweetsCollection
				.find(query, new ObjectId(id))
				.as(Tweet.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: tweets from the selected user
	 * 
	 * @param twitterUserId
	 * @return array of tweets with the selected user or empty list otherwise.
	 */
	public static ArrayList<Object> getTweetsFromUser(long twitterUserId) {
		User user = UserData.findUser(twitterUserId);
		System.out.println("User: " + user.getId());
		String query = "{user_id: #}";
		Iterable<Tweet> tweets = tweetsCollection
				.find(query, new ObjectId("53453a50300402c4735397a4"))
				.as(Tweet.class);
		return CollectionHelper.asArrayList(tweets);

	}

	/**
	 * Read: returns tweet by id
	 * 
	 * @param id Mongo's ObjectId as a String.
	 * @return found tweet or null otherwise.
	 */
	public static Tweet findTweet(String id) {
		Tweet tweet = tweetsCollection.findOne(new ObjectId(id)).as(Tweet.class);
		return tweet;
	}

	/**
	 * Destroy: remove a tweet from the DB.
	 * 
	 * @param id Mongo's ObjectId as String.
	 */
	public static void destroyTweet(String id) {
		tweetsCollection.remove(new ObjectId(id));
		return;
	}



}
