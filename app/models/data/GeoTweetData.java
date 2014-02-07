package models.data;

import java.util.ArrayList;

import models.Category;
import models.GeoTweet;
import models.definitions.Location;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import twitter4j.GeoLocation;
import utils.Helper;
import controllers.db.NameDBs;

/**
 * CRUD (create, read, update and destroy) for GeoTweet model in the DB.
 * 
 * @author martero@ucm.es & raul.marcos@ucm.es
 */
public class GeoTweetData extends ModelData
{
  private static final MongoCollection geoTweetCollection = jongo.getCollection(NameDBs.GEO_TWEETS);

  public GeoTweetData() { }


  /**
   * Create: saves the geoTweet instance into the DB.
   * 
   * @param geoTweet instance going to be saved.
   * @return (String) Mongo's ObjectId as String.
   */
  public static String saveGeoTweet(GeoTweet geoTweet) {
    geoTweetCollection.save(geoTweet);
    return geoTweet.getId();
  }

  /**
   * Create: creates and saves a new GeoTweet instance in the DB.
   * 
   * @param twitterId id defines this tweet by Twitter.
   * @param geoLocation coordenates for this tweet (latitude & longitude).
   * @return (String) Mongo's ObjectId as String.
   */
  public static String saveGeoTweet(long twitterId, GeoLocation geoLocation) {
    GeoTweet geoTweet = GeoTweet.createTweetWithGeoLocation(twitterId, geoLocation);
    geoTweetCollection.save(geoTweet);
    return geoTweet.getId();
  }

  /**
   * Read: returns all the geoTweets in the DB as generic "Object" instances; casting expected.
   * 
   * @return (ArrayList) all geoTweets or empty list otherwise.
   */
  public static ArrayList<Object> getAllGeoTweets() {
    Iterable<GeoTweet> records = geoTweetCollection.find().as(GeoTweet.class);

    if (records == null) {
      return new ArrayList<Object>();
    }
    return Helper.asArrayList(records);
  }

  /**
   * Read: returns geoTweets in the desired location as generic "Object" instances.
   * 
   * @param area known location.
   * @return (ArrayList) all geoTweets in location or empty list otherwise.
   */
  public static ArrayList<Object> getAllGeoTweets(String area) {
    Location location = Location.createLocation(area);

    if (location == null) {
      return new ArrayList<Object>();
    }

    String query = "{latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
    Iterable<GeoTweet> records = geoTweetCollection
        .find(query, location.getMaxLatitude(), location.getMinLatitude(), location.getMaxLongitude(), location.getMinLongitude())
        .as(GeoTweet.class);

    if (records == null) {
      return new ArrayList<Object>();
    }

    return Helper.asArrayList(records);
  }

  /**
   * Read: returns the found geoTweet by id.
   * 
   * @param id Mongo's ObjectId as String.
   * @return (GeoTweet) found geoTweet or null otherwise.
   */
  public static GeoTweet getGeoTweetById(String id) {
    GeoTweet geoTweet = geoTweetCollection.findOne(new ObjectId(id)).as(GeoTweet.class);
    return geoTweet;
  }

  /**
   * Read: returns geoTweets with this category as generic "Object" instances; casting expected.
   * 
   * @param category
   * @return (ArrayList) found geoTweets or empty list otherwise.
   */
  public static ArrayList<Object> getGeoTweetsByCategory(Category category) {
    Iterable<GeoTweet> records = geoTweetCollection
        .find("{category: #}", category.name())
        .as(GeoTweet.class);

    if (records == null) {
      return new ArrayList<Object>();
    }
    return Helper.asArrayList(records);
  }

  /**
   * Update: change the category to an existing geoTweet. If the geoTweet does not exist in the DB
   * this function will not create any geoTweet and will return false.
   * 
   * @param id GeoTweet id.
   * @param category new category.
   * @return (boolean) true if the geoTweet has been actualized; false otherwise.
   */
  public static boolean updateCategoryToGeoTweet(String id, Category category) {
    GeoTweet foundTweet = geoTweetCollection
        .findOne(new ObjectId(id))
        .as(GeoTweet.class);

    if (foundTweet == null) {
      return false;
    }

    foundTweet.setCategory(category);
    geoTweetCollection.save(foundTweet);
    return true;
  }

  /**
   * Destroy: remove a GeoTweet from the DB.
   * @param id Mongo's ObjectId as String.
   */
  public static void destroyGeoTweet(String id) {
    geoTweetCollection.remove(new ObjectId(id));
    return;
  }

}