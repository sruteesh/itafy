package models;

import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import twitter4j.GeoLocation;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class Hashtag
{
  private String    name;
  private double    latitude;
  private double    longitude;
  private Date      createdAt;
  private Category  category;
  @Id
  @ObjectId
  private String id;


  // -------------- //
  //    Factory     //
  // -------------- //

  private Hashtag(String name, GeoLocation c)
  {
    this.latitude =   c.getLatitude();
    this.longitude =  c.getLongitude();
    this.createdAt =  new Date();
    this.category =   null;
    this.name =       name;
    // id
  }

  public static Hashtag createHashtagWithGeoLocations(String hashtag, GeoLocation geoLocation)
  {
    return new Hashtag(hashtag, geoLocation);
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

} // Hashtag
