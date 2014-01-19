package models.data;

import models.Hashtag;

import org.jongo.MongoCollection;

import twitter4j.GeoLocation;
import controllers.db.NameDBs;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class HashtagData extends ModelData
{
  private static final MongoCollection tweet_collection = jongo.getCollection(NameDBs.HASHTAGS);

  public HashtagData() {}
  
  
  // --------------------- //
  //    Classs methods     //
  // --------------------- //

  public static Hashtag saveHashtag(String text, GeoLocation geoLocation)
  {
    Hashtag hashtag = Hashtag.createHashtagWithGeoLocations(text, geoLocation);
    tweet_collection.save(hashtag);
    return hashtag;
  }
  
} // HashtagData
