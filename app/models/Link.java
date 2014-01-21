package models;

import java.io.IOException;
import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import twitter4j.GeoLocation;
import utils.TitleExtractor;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class Link {
	private final String url;
	private String title;
	private final double latitude;
	private final double longitude;
	private final Date createdAt;
	private final Category category;
	@Id
	@ObjectId
	private String id;

	private Link(String url, GeoLocation location) {
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
		this.createdAt = new Date();
		this.category = null;
		this.url = url;
		try {
			this.title = TitleExtractor.getPageTitle(url);
		} catch (IOException e) {
			this.title = null;
			e.printStackTrace();
		}
	}

	public static Link createLinkWithGeoLocations(String url, GeoLocation geoLocation)
	{
		return new Link(url, geoLocation);
	}

	public double getLatitude()
	{
		return latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public String getUrl()
	{
		return url;
	}

	public String getTitle()
	{
		return title;
	}

	public Category getCategory()
	{
		return category;
	}

	public String getId()
	{
		return id;
	}

}
