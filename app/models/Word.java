package models;

import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class Word
{
  private String    text;
  private long      latitude;
  private long      longitude;
  private Date      created_at;
  private Category  category;
  @Id
  @ObjectId
  private String id;
  
  
  // -------------- //
  //    Factory     //
  // -------------- //
  
  private Word(String text, Coordenate c) 
  {
    this.text =       text;
    this.latitude =   c.getLatitude();
    this.longitude =  c.getLongitude();
    this.created_at = new Date();
    this.category =   null;
    // id
  }
  
  public static Word createWordWithCoordenate(String name, Coordenate c)
  {
    return new Word(name, c);
  }
  
  //-------------- //
  //    Getters    //
  // ------------- //
  
  public String getText()
  {
    return text;
  }
  
  public long getLatitude()
  {
    return latitude;
  }

  public long getLongitude()
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
