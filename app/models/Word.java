package models;

import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import twitter4j.GeoLocation;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class Word
{
	private final String text;
	private final double latitude;
	private final double longitude;
	private final Date createdAt;
	private final Category category;
	@Id
	@ObjectId
	private String id;

	private Word(String text, GeoLocation c) {
		this.text = text;
		this.latitude = c.getLatitude();
		this.longitude = c.getLongitude();
		this.createdAt = new Date();
		this.category = null;
	}

	public static Word createWordWithGeoLocation(String name, GeoLocation geoLocation) {
		return new Word(name, geoLocation);
	}

	public String getText() {
		return text;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Category getCategory() {
		return category;
	}

	public String getId() {
		return id;
	}

}
