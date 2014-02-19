package models.entities;

import java.util.Date;
import models.categories.Category;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import twitter4j.GeoLocation;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class Hashtag
{
	private final String name;
	private final double latitude;
	private final double longitude;
	private final Date createdAt;
	private final Category category;
	@Id
	@ObjectId private String id;

	private Hashtag(String name, GeoLocation c)
	{
		this.latitude = c.getLatitude();
		this.longitude = c.getLongitude();
		this.createdAt = new Date();
		this.category = null;
		this.name = name;
	}

	public static Hashtag createHashtagWithGeoLocations(String hashtag, GeoLocation geoLocation)
	{
		return new Hashtag(hashtag, geoLocation);
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

	public String getName()
	{
		return name;
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
