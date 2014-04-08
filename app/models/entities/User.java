package models.entities;

import java.util.ArrayList;
import java.util.Date;
import models.data.TweetsData;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import utils.Helper;
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
	private int followingCount;
	private String genre;

	private Date createdAt;
	private Date updatedAt;

	@JsonCreator
	public User() {
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	private User(String userName, long userId, boolean verified, int followersCount, int followingCount) {
		this.userName = userName;
		this.userId = userId;
		this.verified = verified;
		this.followersCount = followersCount;
		this.followingCount = followingCount;
		this.genre = null;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	/**
	 * Factory.
	 * 
	 * @param userId defines this user by twitter
	 * @param userName
	 * @param followers number of followers this user has
	 * @param following number of people this user is following
	 * @return new <code>User</code> instance
	 */
	public static User createUserWithNameAndFollowersRatio(long userId, String userName, int followers, int following) {
		return new User(userName, userId, false, followers, following);
	}

	/**
	 * Factory.
	 * 
	 * @param userId defines this user by twitter
	 * @param userName
	 * @param followers number of followers this user has
	 * @param following number of people this user is following
	 * @return new <code>User</code> instance with the flag <code>verified</code> set to true
	 */
	public static User createVerifiedUserWithNameAndFollowersRatio(long userId, String userName, int followers, int following) {
		return new User(userName, userId, true, followers, following);
	}

	// TODO genre as enum instead of String
	public void setGenre(String genre) {
		this.genre = genre;
		this.updatedAt = new Date();
	}

	public ArrayList<Tweet> getTweets() {
		ArrayList<Object> tweets = TweetsData.getTweetsWithUser(this.id);
		return Helper.castEeachElementToTweet(tweets);
	}

	@JsonProperty("user_name")
	public String getUserName() { return userName; }

	@JsonProperty("user_id")
	public long getUserId() { return userId; }

	@JsonProperty("verified")
	public boolean isVerified() { return verified; }

	@JsonProperty("followers_count")
	public int getFollowersCount() { return followersCount; }

	@JsonProperty("following_count")
	public int getFollowingCount() { return followingCount; }

	@JsonProperty("followers_ratio")
	public double getFollowersRatio() {
		return followersCount / followingCount;
	}

	@JsonProperty("genre")
	public String getGenre() { return genre; }

	@JsonProperty("_id")
	public String getId() { return id; }

	@JsonProperty("created_at")
	public Date getCreatedAt() { return createdAt; }

	@JsonProperty("updated_at")
	public Date getUpdatedAt() { return updatedAt; }

}
