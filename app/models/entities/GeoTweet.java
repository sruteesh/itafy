package models.entities;

import java.util.Date;
import models.categories.Category;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import twitter4j.GeoLocation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model definition: GeoTweet
 * 
 * <pre>
 * | field     | class    |
 * |:-----     |:------   |
 * | latitude  | double   |
 * | longitude | double   |
 * | twitterId | long     |
 * | category  | category |
 * </pre>
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class GeoTweet {
	@Id
	@ObjectId private String id;

	private double latitude;
	private double longitude;
	private long twitterId;
	private Category category;

	private Date createdAt;
	private Date updatedAt;

	@JsonCreator
	public GeoTweet() {
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	private GeoTweet(long twitterId, GeoLocation c) {
		this.twitterId = twitterId;
		this.latitude = c.getLatitude();
		this.longitude = c.getLongitude();
		this.category = null;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	/**
	 * Factory.
	 * 
	 * @param twitterId defines this tweet by Twitter.
	 * @param geoLocation coordenates.
	 * @return (GeoTweet) new instance.
	 */
	public static GeoTweet createTweetWithGeoLocation(long twitterId, GeoLocation geoLocation) {
		return new GeoTweet(twitterId, geoLocation);
	}

	public void setCategory(Category category) {
		this.category = category;
		this.updatedAt = new Date();
	}

	@JsonProperty("latitude")
	public double getLatitude() { return latitude; }

	@JsonProperty("longitude")
	public double getLongitude() { return longitude; }

	@JsonProperty("created_at")
	public Date getCreatedAt() { return createdAt; }

	@JsonProperty("updated_at")
	public Date getUpdatedAt() { return updatedAt; }

	@JsonProperty("twitter_id")
	public long getTwitterId() { return twitterId; }

	@JsonProperty("category")
	public Category getCategory() { return category; }

	@JsonProperty("_id")
	public String getId() { return id; }

}
