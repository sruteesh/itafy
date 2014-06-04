package models.entities;

import java.util.Date;

import models.categories.Category;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import twitter4j.GeoLocation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model definition: Hashtag
 * 
 * <pre>
 * | field     | class    |
 * |:--------- |:-------- |
 * | name      | String   |
 * | latitude  | double   |
 * | longitude | double   |
 * | category  | Category |
 * | tweetId   | String	  |
 * </pre>
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class Hashtag {
	@Id
	@ObjectId
	private String id;

	private String name;
	private double latitude;
	private double longitude;
	private Category category;
	@ObjectId
	private String tweetId;

	private Date createdAt;
	private Date updatedAt;

	@JsonCreator
	public Hashtag() {
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	private Hashtag(String name, GeoLocation g, String tweetId) {
		this.latitude = g.getLatitude();
		this.longitude = g.getLongitude();
		this.createdAt = new Date();
		this.category = null;
		this.tweetId = tweetId;
		this.name = name;
	}

	/**
	 * Factory.
	 * 
	 * @param hashtag
	 * @param geoLocation
	 * @param tweetId
	 * @return
	 */
	public static Hashtag createHashtagWithGeoLocations(String hashtag, GeoLocation geoLocation, String tweetId) {
		return new Hashtag(hashtag, geoLocation, tweetId);
	}

	// public ArrayList<Tweet> getTweets() {
	// ArrayList<Object> tweets = TweetData.getTweetsWithHashtag(this.id);
	// return CollectionHelper.castEeachElementToTweet(tweets);
	// }

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("latitude")
	public double getLatitude() {
		return latitude;
	}

	@JsonProperty("longitude")
	public double getLongitude() {
		return longitude;
	}

	@JsonProperty("category")
	public Category getCategory() {
		return category;
	}

	@JsonProperty("tweet_id")
	public String getTweetId() {
		return tweetId;
	}

	@JsonProperty("created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	@JsonProperty("_id")
	public String getId() {
		return id;
	}

}
