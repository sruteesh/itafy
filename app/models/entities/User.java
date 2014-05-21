package models.entities;

import java.util.ArrayList;
import java.util.Date;
import models.data.TweetData;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import twitter4j.GeoLocation;
import utils.helpers.CollectionHelper;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model definition: User
 * 
 * <pre>
 * | field          | class    |
 * |:------------   |:-------- |
 * | userName       | String   |
 * | userId         | long     |
 * | verified       | boolean  |
 * | followersCount | int      |
 * | followingCount | int      |
 * | followersRatio | double   |
 * | latitude       | double   |
 * | longitude      | double   |
 * | genre          | String   |
 * </pre>
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class User {
	@Id
	@ObjectId private String id;

	private String userName;
	private long userId;
	private boolean verified;
	private int followersCount;
	private int friendsCount;
	private String genre;
	private double latitude;
	private double longitude;

	private Date createdAt;
	private Date updatedAt;

	@JsonCreator
	public User() {
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	private User(String name, long id) {
		this.userName = name;
		this.userId = id;
		this.verified = false;
		this.genre = null;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	private User(String name, long id, GeoLocation geo) {
		this.userName = name;
		this.userId = id;
		this.verified = false;
		this.latitude = geo.getLatitude();
		this.longitude = geo.getLongitude();
		this.genre = null;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	/**
	 * Factory.
	 * 
	 * @param userId defines this user by twitter
	 * @param name
	 * @return new <code>User</code> instance
	 */
	public static User createUser(long userId, String name) {
		return new User(name, userId);
	}

	/**
	 * Factory.
	 * 
	 * @param userId defines this user by twitter
	 * @param name
	 * @param location
	 * @return new <code>User</code> instance
	 */
	public static User createUserWithGeoLocation(long userId, String name, GeoLocation location) {
		return new User(name, userId, location);
	}


	// FIXME genre as enum instead of String
	public void setGenre(String genre) {
		this.genre = genre;
		this.updatedAt = new Date();
	}

	public void setFollowersCount(int followers) { this.followersCount = followers; }
	public void setFriendsCount(int friends) { this.friendsCount = friends;}


	public ArrayList<Tweet> getTweets() {
		ArrayList<Object> tweets = TweetData.getTweetsFromUser(this.userId);
		return CollectionHelper.castEeachElementToTweet(tweets);
	}

	@JsonProperty("user_name")
	public String getUserName() { return userName; }

	@JsonProperty("user_id")
	public long getUserId() { return userId; }

	@JsonProperty("verified")
	public boolean isVerified() { return verified; }

	@JsonProperty("followers_count")
	public int getFollowersCount() { return followersCount; }

	@JsonProperty("friends_count")
	public int getFriendsCount() { return friendsCount; }

	@JsonProperty("followers_ratio")
	public double getFollowersRatio() {
		double response = 0.0;
		try {
			response = followersCount / friendsCount;
		} catch (ArithmeticException e) {
			// divided by zero; keep 0.0
		}
		return response;
	}

	@JsonProperty("latitude")
	public double getLatitude() { return latitude; }

	@JsonProperty("longitude")
	public double getLongitude() { return longitude; }

	@JsonProperty("genre")
	public String getGenre() { return genre; }

	@JsonProperty("_id")
	public String getId() { return id; }

	@JsonProperty("created_at")
	public Date getCreatedAt() { return createdAt; }

	@JsonProperty("updated_at")
	public Date getUpdatedAt() { return updatedAt; }

	public void updateFollowersCount(twitter4j.User twitterUser) {
		// TODO
	}

}
