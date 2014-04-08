package models.entities;

import java.util.ArrayList;
import java.util.Date;
import models.categories.Category;
import models.data.TweetsData;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import twitter4j.GeoLocation;
import utils.Helper;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * @author manutero, raulmarcosl
 */
public class Word {
	@Id
	@ObjectId private String id;

	private String text;
	private double latitude;
	private double longitude;
	private Category category;

	private Date createdAt;
	private Date updatedAt;

	@JsonCreator
	public Word() {
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	private Word(String text, GeoLocation c) {
		this.text = text;
		this.latitude = c.getLatitude();
		this.longitude = c.getLongitude();
		this.category = null;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	/**
	 * Factory.
	 * 
	 * @param name defines the word itself
	 * @param geoLocation coordenates
	 * @return new <code>Word</code> instance
	 */
	public static Word createWordWithGeoLocation(String name, GeoLocation geoLocation) {
		return new Word(name, geoLocation);
	}

	public void setCategory(Category category) {
		this.category = category;
		this.updatedAt = new Date();
	}

	public ArrayList<Tweet> getTweets() {
		ArrayList<Object> tweets = TweetsData.getTweetsWithWord(this.id);
		return Helper.castEeachElementToTweet(tweets);
	}

	@JsonProperty("text")
	public String getText() { return text; }

	@JsonProperty("latitude")
	public double getLatitude() { return latitude; }

	@JsonProperty("longitude")
	public double getLongitude() { return longitude; }

	@JsonProperty("category")
	public Category getCategory() { return category; }

	@JsonProperty("created_at")
	public Date getCreatedAt() { return createdAt; }

	@JsonProperty("updated_at")
	public Date getUpdatedAt() { return updatedAt; }

	@JsonProperty("_id")
	public String getId() { return id; }

}
