package models.data;

import models.Coordenate;
import models.GeoTweet;

import org.jongo.MongoCollection;

import controllers.db.NameDBs;

public class GeoTweetData extends EntityData
{
  private static final MongoCollection tweet_collection = jongo.getCollection(NameDBs.GEO_TWEETS);

  public GeoTweetData() {}
  
  
  // --------------------- //
  //    Classs methods     //
  // --------------------- //
  
  public static GeoTweet saveGeoTweet(long twitter_id, Coordenate coordenate)
  {
    GeoTweet tweet = GeoTweet.createTweetWithCoordenates(twitter_id, coordenate);
    tweet_collection.save(tweet);
    return tweet;
  }
  
} // GeoTweetData
