package models;

import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import twitter4j.GeoLocation;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class GeoTweet
{
	private final double latitude;
	private final double longitude;
	private final Date createdAt;
	private final long twitterId;
	private final Category category;
	@Id
	@ObjectId
	private String id;

	// -------------- //
	// Factory //
	// -------------- //

	private GeoTweet(long twitterId, GeoLocation c)
	{
		this.latitude = c.getLatitude();
		this.longitude = c.getLongitude();
		this.createdAt = new Date();
		this.category = null;
		this.twitterId = twitterId;
	}

	public static GeoTweet createTweetWithGeoLocations(long twitterId, GeoLocation geoLocation)
	{
		return new GeoTweet(twitterId, geoLocation);
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

	public long getTwitterId()
	{
		return twitterId;
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
