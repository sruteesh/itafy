package models.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import models.entities.Tweet;
import models.entities.TwitterUser;

import org.jongo.MongoCollection;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import controllers.db.DbNames;

public class TweetData extends MongoClientData {
	private static final MongoCollection tweetsCollection = jongoItafy.getCollection(DbNames.TWEETS);
	private static final MongoCollection usersCollection = jongoItafy.getCollection(DbNames.USERS);

	public static Tweet saveTweet(Status status, String userGender) {

		Tweet tweet = new Tweet();

		tweet.setCreatedAt(status.getCreatedAt());
		tweet.setMessageId(status.getId());

		tweet.setText(extractText(status));
		tweet.setUserMentionEntities(extractUserMentionEntities(status));
		tweet.setUrlEntities(extractUrlEntities(status));
		tweet.setHashtagEntities(extractHashtagEntities(status));

		tweet.setLatitude(extractLatitude(status));
		tweet.setLongitude(extractLongitude(status));
		tweet.setPlace(extractPlace(status));

		tweet.setSource(extractSource(status));
		tweet.setInReplyToStatusId(status.getInReplyToStatusId());
		tweet.setInReplyToUserId(status.getInReplyToUserId());
		tweet.setInReplyToScreenName(status.getInReplyToScreenName());

		tweet.setRetweetCount(status.getRetweetCount());
		tweet.setPossiblySensitive(status.isPossiblySensitive());
		tweet.setUser(createUser(status.getUser()));

		tweet.setGender(userGender);

		tweetsCollection.save(tweet);

		return tweet;
	}

	private static TwitterUser createUser(User user) {
		TwitterUser twitterUser = new TwitterUser();
		twitterUser.setUserId(user.getId());
		twitterUser.setName(user.getName());
		twitterUser.setScreenName(user.getScreenName());
		twitterUser.setLocation(user.getLocation());
		twitterUser.setDescription(user.getDescription());
		twitterUser.setContributorsEnabled(user.isContributorsEnabled());
		twitterUser.setProfileImageURL(user.getProfileImageURL());
		twitterUser.setProfileImageURLHttps(user.getBiggerProfileImageURLHttps());
		twitterUser.setUrl(user.getURL());
		twitterUser.setProtected(user.isProtected());
		twitterUser.setFollowersCount(user.getFollowersCount());
		twitterUser.setProfileBackgroundColor(user.getProfileBackgroundColor());
		twitterUser.setProfileTextColor(user.getProfileTextColor());
		twitterUser.setProfileLinkColor(user.getProfileLinkColor());
		twitterUser.setProfileSidebarFillColor(user.getProfileSidebarFillColor());
		twitterUser.setProfileSidebarBorderColor(user.getProfileSidebarBorderColor());
		twitterUser.setProfileUseBackgroundImage(user.isProfileUseBackgroundImage());
		twitterUser.setShowAllInlineMedia(user.isShowAllInlineMedia());
		twitterUser.setFriendsCount(user.getFriendsCount());
		twitterUser.setCreatedAt(user.getCreatedAt());
		twitterUser.setFavouritesCount(user.getFavouritesCount());
		twitterUser.setUtcOffset(user.getUtcOffset());
		twitterUser.setTimeZone(String.valueOf(user.getTimeZone()));
		twitterUser.setProfileBackgroundImageUrl(user.getProfileBackgroundImageURL());
		twitterUser.setProfileBackgroundImageUrlHttps(user.getProfileBackgroundImageUrlHttps());
		twitterUser.setProfileBackgroundTiled(user.isProfileBackgroundTiled());
		twitterUser.setLang(user.getLang());
		twitterUser.setStatusesCount(user.getStatusesCount());
		twitterUser.setGeoEnabled(user.isGeoEnabled());
		twitterUser.setVerified(user.isVerified());
		twitterUser.setTranslator(user.isTranslator());
		twitterUser.setListedCount(user.getListedCount());
		twitterUser.setFollowRequestSent(user.isFollowRequestSent());

		usersCollection.save(twitterUser);

		return twitterUser;
	}

	public static String extractText(Status status) {
		if (status.getRetweetedStatus() == null) {
			return status.getText();
		} else {
			return status.getRetweetedStatus().getText();
		}
	}

	private static ArrayList<String> extractUrlEntities(Status status) {
		URLEntity[] urlEntities;
		if (status.getRetweetedStatus() == null) {
			urlEntities = status.getURLEntities();
		} else {
			urlEntities = status.getRetweetedStatus().getURLEntities();
		}

		ArrayList<String> arrayUrlEntities = new ArrayList<String>();
		for (URLEntity urlEntity : urlEntities) {
			arrayUrlEntities.add(urlEntity.getExpandedURL());
		}

		return arrayUrlEntities;
	}

	private static ArrayList<String> extractUserMentionEntities(Status status) {
		UserMentionEntity[] userMentionEntities;
		if (status.getRetweetedStatus() == null) {
			userMentionEntities = status.getUserMentionEntities();
		} else {
			userMentionEntities = status.getRetweetedStatus().getUserMentionEntities();
		}
		ArrayList<String> arrayUserMentionEntities = new ArrayList<String>();
		for (UserMentionEntity userMentionEntity : userMentionEntities) {
			arrayUserMentionEntities.add(userMentionEntity.getScreenName());
		}

		return arrayUserMentionEntities;
	}

	private static ArrayList<String> extractHashtagEntities(Status status) {
		HashtagEntity[] hashtagEntities;
		if (status.getRetweetedStatus() == null) {
			hashtagEntities = status.getHashtagEntities();
		} else {
			hashtagEntities = status.getRetweetedStatus().getHashtagEntities();
		}

		ArrayList<String> arrayhashtags = new ArrayList<String>();
		for (HashtagEntity h : hashtagEntities) {
			arrayhashtags.add('#' + h.getText());
		}

		return arrayhashtags;
	}

	private static double extractLatitude(Status status) {
		double latitude = 0;
		if (status.getGeoLocation() != null) {
			latitude = status.getGeoLocation().getLatitude();
		}

		return latitude;
	}

	private static double extractLongitude(Status status) {
		double longitude = 0;
		if (status.getGeoLocation() != null) {
			longitude = status.getGeoLocation().getLongitude();
		}

		return longitude;
	}

	private static String extractPlace(Status status) {
		String place = null;
		if (status.getPlace() != null) {
			place = String.valueOf(status.getPlace());
		}

		return place;
	}

	private static String extractSource(Status status) {
		return status.getSource().replaceAll("</?a(|\\s+[^>]+)>", "");
	}

	public static Iterable<Tweet> getTweetsWithoutCategory(int limit) {
		if (limit < 0) {
			limit = 200;
		}

		return tweetsCollection
				.find("{category: null}")
				.sort("{_id: -1}")
				.limit(limit)
				.as(Tweet.class);
	}

	public static void setCategory(Tweet tweet, String category) {
		tweet.setCategory(category);
		tweetsCollection.save(tweet);
	}

	public static HashMap<String, Object> getPerMinute() {
		HashMap<String, Object> response = new HashMap<String, Object>();
		ArrayList<Long> countList = new ArrayList<Long>();
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -24);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = new Date(calendar.getTimeInMillis());
		response.put("start_date", startDate);
		Date endDate = null;

		while (startDate.before(now)) {
			calendar.add(Calendar.MINUTE, 1);
			endDate = new Date(calendar.getTimeInMillis());

			long tweetCount = tweetsCollection
					.count("{createdAt: {$gt: #, $lte: #}}", startDate, endDate);
			countList.add(tweetCount);

			startDate = endDate;
		}
		response.put("end_date", endDate);
		response.put("tweets", countList);

		return response;
	}

	public static HashMap<String, Object> getCategoriesPerPercentage() {
		HashMap<String, Object> response = new HashMap<String, Object>();

		long totalTweets = tweetsCollection.count();
		long sportsTweets = tweetsCollection.count("{category: #}", "DEPORTES");

		response.put("tweets", totalTweets);
		response.put("sports_tweets", sportsTweets);
		response.put("sports_percentage", sportsTweets * 100 / totalTweets);

		return response;
	}

	public static HashMap<String, Object> getGendersPerPercentage() {
		HashMap<String, Object> response = new HashMap<String, Object>();

		long totalTweets = tweetsCollection.count();
		long femaleTweets = tweetsCollection.count("{gender: #}", "female");
		long maleTweets = tweetsCollection.count("{gender: #}", "male");

		response.put("tweets", totalTweets);
		response.put("female_tweets", femaleTweets);
		response.put("male_tweets", maleTweets);

		return response;
	}
}
