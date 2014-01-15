package models;

import java.io.IOException;
import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import utils.TitleExtractor;

public class Link
{
  private String    url;
  private String    title;
  private long      latitude;
  private long      longitude;
  private Date      createdAt;
  private Category  category;
  @Id
  @ObjectId
  private String id;
  
  
  // -------------- //
  //    Factory     //
  // -------------- //
  
  private Link(String url, Coordenate c)
  { 
    this.latitude = c.getLatitude();
    this.longitude = c.getLongitude();
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
  
  public static Link createLinkWithCoordenates(String url, Coordenate c)
  {
    return new Link(url, c);
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
