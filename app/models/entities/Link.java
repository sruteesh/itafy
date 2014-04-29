package models.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import models.categories.Category;
import models.data.TweetsData;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import twitter4j.GeoLocation;
import utils.helpers.CollectionHelper;
import utils.helpers.WebHelper;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model definition: Link
 *
 * <pre>
 * | field     | class    |
 * |:--------- |:-------- |
 * | latitude  | double   |
 * | longitude | double   |
 * | title     | String   |
 * | url       | String   |
 * | category  | Category |
 * </pre>
 *
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 */
public class Link {
	@Id
	@ObjectId private String id;

	private double latitude;
	private double longitude;
	private String title;
	private String url;
	private Category category;

	private Date createdAt;
	private Date updatedAt;

	@JsonCreator
	public Link() {
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	private Link(String url, GeoLocation location) {
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
		this.title = null;
		this.url = url;
		this.category = null;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	/**
	 * Factory.
	 *
	 * @param url the link itself.
	 * @param geoLocation coordenates.
	 * @return (Link) new instance.
	 */
	public static Link createLinkWithGeoLocations(String url, GeoLocation geoLocation) {
		return new Link(url, geoLocation);
	}

	public void completeTitle() throws IOException {
		this.title = WebHelper.getPageTitle(this.url);
		this.updatedAt = new Date();
	}

	public void setCategory(Category category) {
		this.category = category;
		this.updatedAt = new Date();
	}

	public ArrayList<Tweet> getTweets() {
		ArrayList<Object> tweets = TweetsData.getTweetsWithLink(this.id);
		return CollectionHelper.castEeachElementToTweet(tweets);
	}

	@JsonProperty("latitude")
	public double getLatitude() { return latitude; }

	@JsonProperty("longitude")
	public double getLongitude() { return longitude; }

	@JsonProperty("url")
	public String getUrl() { return url; }

	@JsonProperty("title")
	public String getTitle() { return title; }

	@JsonProperty("category")
	public Category getCategory() { return category; }

	@JsonProperty("created_at")
	public Date getCreatedAt() { return createdAt; }

	@JsonProperty("updated_at")
	public Date getUpdatedAt() { return updatedAt; }

	@JsonProperty("_id")
	public String getId() { return id; }

}
