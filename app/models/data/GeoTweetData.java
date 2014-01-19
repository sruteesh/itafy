package models.data;

import models.GeoTweet;

import org.jongo.MongoCollection;

import twitter4j.GeoLocation;
import controllers.db.NameDBs;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class GeoTweetData extends ModelData
{
  private static final MongoCollection tweet_collection = jongo.getCollection(NameDBs.GEO_TWEETS);

  public GeoTweetData() { }


  // --------------------- //
  //    Classs methods     //
  // --------------------- //

  public static GeoTweet saveGeoTweet(long twitter_id, GeoLocation GeoLocation)
  {
    GeoTweet tweet = GeoTweet.createTweetWithGeoLocations(twitter_id, GeoLocation);
    tweet_collection.save(tweet);
    return tweet;
  }

} // GeoTweetData
