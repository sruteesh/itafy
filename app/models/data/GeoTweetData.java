package models.data;

import java.util.ArrayList;
import models.categories.AvaibleCategories;
import models.categories.Category;
import models.entities.GeoTweet;
import models.geoLocation.Area;
import models.geoLocation.AvaibleLocations;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import twitter4j.GeoLocation;
import utils.Helper;
import controllers.db.DbNames;

/**
 * CRUD (create, read, update and destroy) for <code>GeoTweet</code> model in the DB.
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * @see GeoTweet
 */
public class GeoTweetData extends MongoClientData {

	protected static final MongoCollection geoTweetCollection = jongoItafy.getCollection(DbNames.GEO_TWEETS);

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
	 * Create: creates and saves a new geoTweet instance in the DB.
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
	 * Read: returns all the geoTweets in the DB as generic <code>Object</code> instances;
	 * casting expected.
	 *
	 * @return (ArrayList) all geoTweets or empty list otherwise.
	 */
	public static ArrayList<Object> getAllGeoTweets() {
		Iterable<GeoTweet> records = geoTweetCollection.find().as(GeoTweet.class);
		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns geoTweets in the desired location as generic <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param location (Enum) known location.
	 * @return (ArrayList) all geoTweets in location or empty list otherwise.
	 */
	public static ArrayList<Object> getAllGeoTweets(AvaibleLocations location) {
		Area area = Area.createLocation(location);
		if (area == null) {
			return new ArrayList<Object>();
		}

		String query = "{latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Iterable<GeoTweet> records = geoTweetCollection
				.find(query, area.getMaxLat(), area.getMinLat(), area.getMaxLong(), area.getMinLong())
				.as(GeoTweet.class);

		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns geoTweets with the desired category as generic <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param cat (Enum) known category.
	 * @return (ArrayList) all geoTweets with category or empty list otherwise.
	 */
	public static ArrayList<Object> getAllGeoTweets(AvaibleCategories cat) {
		Category category = Category.createCategory(cat);
		if (category == null) {
			return new ArrayList<Object>();
		}

		Iterable<GeoTweet> records = geoTweetCollection
				.find("{category: #}", category.getName())
				.as(GeoTweet.class);

		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns geoTweets with the desired category and location as <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param location (Enum) known location.
	 * @param category (Enum) known category.
	 * @return (ArrayList) all geotweets in location and categorized or empty list otherwise.
	 */
	public static ArrayList<Object> getAllGeoTweets(AvaibleLocations location, AvaibleCategories category) {
		Area loc = Area.createLocation(location);
		Category cat = Category.createCategory(category);
		if ((loc == null) || (cat == null)) {
			return new ArrayList<Object>();
		}

		String query = "{category: #, latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Iterable<GeoTweet> records = geoTweetCollection
				.find(query, cat.getName(), loc.getMaxLat(), loc.getMinLat(), loc.getMaxLong(), loc.getMinLong())
				.as(GeoTweet.class);

		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns the found geoTweet by id.
	 *
	 * @param id (String) Mongo's ObjectId as String.
	 * @return (GeoTweet) found geoTweet or null otherwise.
	 */
	public static GeoTweet getGeoTweetById(String id) {
		GeoTweet geoTweet = geoTweetCollection.findOne(new ObjectId(id)).as(GeoTweet.class);
		return geoTweet;
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