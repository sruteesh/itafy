package controllers.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import models.data.GeoTweetData;
import models.data.HashtagData;
import models.data.LinkData;
import models.data.TweetsData;
import models.data.UserData;
import models.entities.GeoTweet;
import models.entities.Hashtag;
import models.entities.Link;
import models.entities.Location;
import models.entities.Tweet;
import models.entities.TwitterName;
import models.entities.User;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import play.Logger;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.URLEntity;
import utils.StreamingWebSocket;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import controllers.db.DbNames;
import controllers.db.MongoDBHandler;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class StreamingListener implements StatusListener {

	private static final MongoClient mongo = MongoDBHandler.getMongoClient();
	private static final DB dbItafyBenchmarks = mongo.getDB(DbNames.DB_ITAFY_BENCHMARKS);
	private static final Jongo jongoEpg = new Jongo(dbItafyBenchmarks);
	private static final MongoCollection twitterNamesCollection = jongoEpg.getCollection(DbNames.TWITTER_NAMES);

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		Logger.warn("[ON DELETION NOTICE] StatusId: " + arg0.getStatusId());
	}

	@Override
	public void onException(Exception arg0) {
		arg0.printStackTrace();
		Logger.warn("[ON EXCEPTION] Message: " + arg0.getMessage());
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		Logger.warn("[ON SCRUB GEO] arg0, arg1: " + arg0 + ", " + arg1);
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		Logger.warn("[ON STALL WARNING] Message: " + arg0.getMessage() + "; full: " + arg0.getPercentFull());
	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		Logger.warn("[ON TRACK LIMITATION NOTICE] arg0: " + arg0);
	}

	@Override
	public void onStatus(twitter4j.Status status) {

		// saveTwitterName(status);

		if (status.getGeoLocation() != null) {
			// sendTweetToWebSocket(status);
			saveStatusToDB(status);
		}
	}

	private void saveTwitterName(twitter4j.Status status) {
		twitter4j.User user = status.getUser();
		String name = user.getName();
		String screenName = user.getScreenName();
		String lang = user.getLang();
		String description = user.getDescription();

		TwitterName twitterName = twitterNamesCollection.findOne("{name: #, description: #}", name, description)
				.as(TwitterName.class);

		if (twitterName == null) {
			twitterName = new TwitterName();

			twitterName.setName(name);
			twitterName.setScreenName(screenName);
			twitterName.setDescription(description);
			twitterName.setProfileImageUrl(user.getProfileBackgroundImageURL());
			twitterName.setLanguage(lang);
			twitterName.setCreatedAt(new Date());

			GeoLocation geoLocation = status.getGeoLocation();
			if (geoLocation != null) {
				double longitude = status.getGeoLocation().getLongitude();
				double latitude = status.getGeoLocation().getLatitude();

				Location location = new Location(longitude, latitude);
				twitterName.setLocation(location);
			}

			twitterNamesCollection.save(twitterName);
			Logger.info(user.getScreenName());
			Logger.info("[TwitterName] " + name + "\t @" + screenName + "\t" + lang);
		}
	}

	private void sendTweetToWebSocket(twitter4j.Status status) {
		HashMap<String, Object> webSocketData = new HashMap<String, Object>();
		webSocketData.put("text", status.getText());
		GeoLocation geoLocation = status.getGeoLocation();
		if (geoLocation != null) {
			webSocketData.put("longitude", geoLocation.getLongitude());
			webSocketData.put("latitude", geoLocation.getLatitude());
			StreamingWebSocket.sendHashMap(webSocketData);
		}
	}

	/**
	 * Called from <code>StreamingListener.onStatus</code>: save the inmediate data to DB.
	 * <ul>
	 *  <li> geoTweet
	 *  <li> hashtags
	 *  <li> links
	 *  <li> user
	 *  <li> words
	 * </ul>
	 */
	private void saveStatusToDB(twitter4j.Status status) {
		GeoLocation location = status.getGeoLocation();
		String geoTweetId = saveGeoTweet(status.getId(), location);
		ArrayList<String> hashtagIds = saveHashtags(status.getHashtagEntities(), location);
		ArrayList<String> linkIds = saveLinks(status.getURLEntities(), location);
		String userId = saveUser(status.getUser(), location);
		ArrayList<String> wordIds = saveWords(status.getText(), location);
		saveTweet(status.getText(), geoTweetId, hashtagIds, linkIds, userId, wordIds);
	}

	/**
	 * Called from <code>StreamingListener.onStatus</code>: create a saves a geoTweet to DB
	 * @param twitterId
	 * @param location
	 * @return Mongo's id as a String
	 */
	private String saveGeoTweet(long twitterId, GeoLocation location) {
		GeoTweet geoTweet = GeoTweet.createTweetWithGeoLocation(twitterId, location);
		String geoTweetId = GeoTweetData.saveGeoTweet(geoTweet);
		Logger.info("Tweet: " + geoTweet.getTwitterId());
		return geoTweetId;
	}

	/**
	 * Called from <code>StreamingListener.onStatus</code>: save each hashtag to DB
	 * @param hashtagEntities
	 * @param location
	 * @return Mongo's ids as Strings
	 */
	private ArrayList<String> saveHashtags(HashtagEntity[] hashtagEntities, GeoLocation location) {
		ArrayList<String> hashtagIds = new ArrayList<String>();

		for (HashtagEntity hashtagEntity : hashtagEntities) {
			String text = hashtagEntity.getText().toLowerCase();
			Hashtag hashtag = Hashtag.createHashtagWithGeoLocations(text, location);
			String hashtagId = HashtagData.saveHashtag(hashtag);
			hashtagIds.add(hashtagId);
			Logger.info("HT: " + hashtag.getName());
		}

		return hashtagIds;
	}

	/**
	 * Called from <code>StreamingListener.onStatus</code>: save each link to DB
	 * @param urlEntities
	 * @param location
	 * @return Mongo's ids as Strings
	 */
	private ArrayList<String> saveLinks(URLEntity[] urlEntities, GeoLocation location) {
		ArrayList<String> linkIds = new ArrayList<String>();

		for (URLEntity urlEntity : urlEntities) {
			String url = urlEntity.getExpandedURL();
			Link link = Link.createLinkWithGeoLocations(url, location);
			String linkId = LinkData.saveLink(link);
			linkIds.add(linkId);
			Logger.info("Link: " + link.getUrl());
		}

		return linkIds;
	}

	/**
	 * Called from <code>StreamingListener.onStatus</code>: create and saves the user to DB.
	 * 
	 * @param twitterUser
	 * @param location
	 * @return Mongo's id as a String
	 */
	private String saveUser(twitter4j.User twitterUser, GeoLocation location) {
		long userId = twitterUser.getId();
		String userName = twitterUser.getName();
		User user = User.createUserWithGeoLocation(userId, userName, location);
		user.setFollowersCount(twitterUser.getFollowersCount());
		user.setFriendsCount(twitterUser.getFriendsCount());
		UserData.saveUser(user);
		Logger.info("User: " + user.getUserName());
		return user.getId();
	}

	private ArrayList<String> saveWords(String tweetText, GeoLocation location) {
		ArrayList<String> wordIds = new ArrayList<String>();
		// TODO
		return wordIds;
	}

	/**
	 * Called from <code>StreamingListener.onStatus</code>: create and saves a new
	 * <code>models.entity.tweet</code> which relates every model
	 * 
	 * @param geoTweetId
	 * @param hashtagIds
	 * @param linkIds
	 * @param userId
	 * @param wordIds
	 * @return Mongo's id as a String
	 */
	private String saveTweet(String text, String geoTweetId, ArrayList<String> hashtagIds,
			ArrayList<String> linkIds, String userId, ArrayList<String> wordIds) {

		Tweet tweet = Tweet.createTweetWithGeoTweetAndUser(text, geoTweetId, userId);

		if (!hashtagIds.isEmpty()) {
			tweet.setHashtagIds(hashtagIds);
		}
		if (!linkIds.isEmpty()) {
			tweet.setLinkIds(linkIds);
		}
		if (!wordIds.isEmpty()) {
			tweet.setWordIds(wordIds);
		}

		TweetsData.savetweet(tweet);
		Logger.info("Tweet: " + tweet.getText());
		return tweet.getId();
	}

}
