package models.entities;

import java.util.Date;
import models.categories.Category;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import twitter4j.GeoLocation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class Hashtag {
	@Id
	@ObjectId private String id;

	private String name;
	private double latitude;
	private double longitude;
	private Category category;

	private Date createdAt;
	private Date updatedAt;

	@JsonCreator
	private Hashtag(String name, GeoLocation c) {
		this.latitude = c.getLatitude();
		this.longitude = c.getLongitude();
		this.createdAt = new Date();
		this.category = null;
		this.name = name;
	}

	/**
	 * Factory.
	 * 
	 * @param hashtag
	 * @param geoLocation
	 * @return
	 */
	public static Hashtag createHashtagWithGeoLocations(String hashtag, GeoLocation geoLocation) {
		return new Hashtag(hashtag, geoLocation);
	}

	@JsonProperty("latitude")
	public double getLatitude() { return latitude; }

	@JsonProperty("longitude")
	public double getLongitude() { return longitude; }

	@JsonProperty("created_at")
	public Date getCreatedAt() { return createdAt; }

	@JsonProperty("name")
	public String getName() { return name; }

	@JsonProperty("category")
	public Category getCategory() { return category; }

	@JsonProperty("_id")
	public String getId() { return id; }

}
