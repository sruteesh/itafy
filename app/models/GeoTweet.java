package models;

import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;


public class GeoTweet
{
  private long      latitude;
  private long      longitude;
  private Date      createdAt;
  private long      twitter_id;
  private Category  category;
  @Id
  @ObjectId
  private String id;
  
  
  // -------------- //
  //    Factory     //
  // -------------- //
  
  private GeoTweet(long twitter_id, Coordenate c)
  { 
    this.latitude =   c.getLatitude();
    this.longitude =  c.getLongitude();
    this.createdAt =  new Date();
    this.category =   null;
    this.twitter_id = twitter_id;
    // id
  }
  
  public static GeoTweet createTweetWithCoordenates(long twitter_id, Coordenate c)
  {
    return new GeoTweet(twitter_id, c);
  }
  
  
  // -------------- //
  //    Getters     //
  // -------------- //
  
  public long getLatitude()
  {
    return latitude;
  }

  public long getLongitude()
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
