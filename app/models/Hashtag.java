package models;

import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class Hashtag
{
  private String    name;
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
  
  private Hashtag(String name, Coordenate c)
  { 
    this.latitude =   c.getLatitude();
    this.longitude =  c.getLongitude();
    this.createdAt =  new Date();
    this.category =   null;
    this.name =       name;
    // id
  }
  
  public static Hashtag createHashtagWithCoordenates(String hashtag, Coordenate c)
  {
    return new Hashtag(hashtag, c);
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
