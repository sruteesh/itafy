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
public class Link
{
  private String    url;
  private String    title;
  private double      latitude;
  private double      longitude;
  private Date      createdAt;
  private Category  category;
  @Id
  @ObjectId
  private String id;


  // -------------- //
  //    Factory     //
  // -------------- //

  private Link(String url, GeoLocation location)
  {
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
    // id
  }

  public static Link createLinkWithGeoLocations(String url, GeoLocation geoLocation)
  {
    return new Link(url, geoLocation);
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

} //GeoTweet
