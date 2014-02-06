package models;

import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import twitter4j.GeoLocation;

/**
 * Model definition: GeoTweet
 * 
 * | field     | class    |
 * |:-----     |:------   |
 * | latitude  | double   |
 * | longitude | double   |
 * | twitterId | long     |
 * | category  | category |
 * 
 * @author martero@ucm.es & raul.marcos@ucm.es
 */
public class GeoTweet {
  @Id
  @ObjectId private String id;

  private final double latitude;
  private final double longitude;
  private final long twitterId;
  private Category category;

  private final Date createdAt;
  private Date updatedAt;

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

  public double getLatitude() { return latitude; }

  public double getLongitude() { return longitude; }

  public Date getCreatedAt() { return createdAt; }

  public Date getUpdatedAt() { return updatedAt; }

  public long getTwitterId() { return twitterId; }

  public Category getCategory() { return category; }

  public String getId() { return id; }

}
