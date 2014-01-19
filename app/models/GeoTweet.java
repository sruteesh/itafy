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
  private double    latitude;
  private double    longitude;
  private Date      createdAt;
  private long      twitter_id;
  private Category  category;
  @Id
  @ObjectId
  private String id;


  // -------------- //
  //    Factory     //
  // -------------- //

  private GeoTweet(long twitter_id, GeoLocation c)
  {
    this.latitude =   c.getLatitude();
    this.longitude =  c.getLongitude();
    this.createdAt =  new Date();
    this.category =   null;
    this.twitter_id = twitter_id;
    // id
  }

  public static GeoTweet createTweetWithGeoLocations(long twitter_id, GeoLocation geoLocation)
  {
    return new GeoTweet(twitter_id, geoLocation);
  }


  // -------------- //
  //    Getters     //
  // -------------- //

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

  public long getTwitter_id()
  {
    return twitter_id;
  }

  public Category getCategory()
  {
    return category;
  }

  public String getId()
  {
    return id;
  }

} //GeoTweet
