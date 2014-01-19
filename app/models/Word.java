package models;

import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import twitter4j.GeoLocation;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class Word
{
  private String    text;
  private double      latitude;
  private double      longitude;
  private Date      created_at;
  private Category  category;
  @Id
  @ObjectId
  private String id;


  // -------------- //
  //    Factory     //
  // -------------- //

  private Word(String text, GeoLocation c)
  {
    this.text =       text;
    this.latitude =   c.getLatitude();
    this.longitude =  c.getLongitude();
    this.created_at = new Date();
    this.category =   null;
    // id
  }

  public static Word createWordWithGeoLocation(String name, GeoLocation geoLocation)
  {
    return new Word(name, geoLocation);
  }

  //-------------- //
  //    Getters    //
  // ------------- //

  public String getText()
  {
    return text;
  }

  public double getLatitude()
  {
    return latitude;
  }

  public double getLongitude()
  {
    return longitude;
  }

  public Date getCreated_at()
  {
    return created_at;
  }

  public Category getCategory()
  {
    return category;
  }

  public String getId()
  {
    return id;
  }

} // Word
