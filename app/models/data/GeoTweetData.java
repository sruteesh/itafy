package models.data;

import java.util.ArrayList;
import java.util.Date;
import models.categories.Category;
import models.entities.GeoTweet;
import models.geoLocation.Area;
import models.geoLocation.AvaibleLocations;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import twitter4j.GeoLocation;
import utils.helpers.CollectionHelper;
import controllers.db.DbNames;

/**
 * CRUD (create, read, update and destroy) for <code>GeoTweet</code> model in the DB.
 *
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * @see GeoTweet
 */
public class GeoTweetData extends MongoClientData {
	protected static final MongoCollection geoTweetCollection =
			jongoItafy.getCollection(DbNames.GEO_TWEETS);

	/** No need to instanciate a <code>GeoTweetData</code> object */
	private GeoTweetData() {}


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
	public static ArrayList<Object> getGeoTweets() {
		Iterable<GeoTweet> records = geoTweetCollection.find().as(GeoTweet.class);
		return CollectionHelper.asArrayList(records);
	}


	/**
	 * Read: returns geoTweets in the desired location as generic <code>Object</code> instances;
	 * casting expected.
	 * <p>
	 * Note: avoid auto boxing (Effective Java Item 49)
	 * 
	 * @param location (Enum) known location.
	 * @return (ArrayList) all geoTweets in location or empty list otherwise.
	 */
	public static ArrayList<Object> getGeoTweets(Area area) {
		if (area == null) {
			return new ArrayList<Object>();
		}
		String query = "{latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Double maxLatitude = Double.valueOf(area.getMaxLat());
		Double minLatitude = Double.valueOf(area.getMinLat());
		Double maxLongitude = Double.valueOf(area.getMaxLong());
		Double minLongitude = Double.valueOf(area.getMinLong());
		Iterable<GeoTweet> records = geoTweetCollection
				.find(query, maxLatitude, minLatitude, maxLongitude, minLongitude)
				.as(GeoTweet.class);
		return CollectionHelper.asArrayList(records);
	}


	/**
	 * Read: returns geoTweets with the desired category as generic <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param cat (Enum) known category.
	 * @return (ArrayList) all geoTweets with category or empty list otherwise.
	 */
	public static ArrayList<Object> getGeoTweets(Category category) {
		if (category == null) {
			return new ArrayList<Object>();
		}
		Iterable<GeoTweet> records = geoTweetCollection
				.find("{category: #}", category.getName())
				.as(GeoTweet.class);
		return CollectionHelper.asArrayList(records);
	}


	/**
	 * Read: returns geoTweets from the selected date as generic <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param date
	 * @return geoTweets from the selected date
	 */
	public static ArrayList<Object> getGeoTweets(Date date) {
		if (date == null) {
			return new ArrayList<Object>();
		}
		String query = "{updated_at : {$gt: #}}";
		Iterable<GeoTweet> records = geoTweetCollection
				.find(query, date)
				.as(GeoTweet.class);
		return CollectionHelper.asArrayList(records);
	}


	/**
	 * Read: returns geoTweets with the desired category and location as <code>Object</code>
	 * instances; casting expected
	 * <p>
	 * Note: avoid auto boxing (Effective Java Item 49)
	 * 
	 * @param location (Enum) known location.
	 * @param category (Enum) known category.
	 * @return (ArrayList) all geotweets in location and categorized or empty list otherwise.
	 */
	public static ArrayList<Object> getGeoTweets(Area area, Category category) {
		if ((area == null) || (category == null)) {
			return new ArrayList<Object>();
		}
		String query = "{category: #, latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Double maxLatitude = Double.valueOf(area.getMaxLat());
		Double minLatitude = Double.valueOf(area.getMinLat());
		Double maxLongitude = Double.valueOf(area.getMaxLong());
		Double minLongitude = Double.valueOf(area.getMinLong());
		Iterable<GeoTweet> records = geoTweetCollection
				.find(query, category.getName(), maxLatitude, minLatitude, maxLongitude, minLongitude)
				.as(GeoTweet.class);
		return CollectionHelper.asArrayList(records);
	}


	/**
	 * Read: returns geoTweets with the selected location, from the selected date as generic
	 * <code>Object</code> instances; casting expected.
	 * <p>
	 * Note: avoid auto boxing (Effective Java Item 49)
	 * 
	 * @param location knwon location
	 * @param date
	 * @return geoTweets from the selected date and in the selected location
	 */
	public static ArrayList<Object> getGeoTweets(AvaibleLocations location, Date date) {
		Area area = Area.createLocation(location);
		if ((area == null) || (date == null)) {
			return new ArrayList<Object>();
		}
		String query = "{updated_at : {$gt: #}, latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Double maxLatitude = Double.valueOf(area.getMaxLat());
		Double minLatitude = Double.valueOf(area.getMinLat());
		Double maxLongitude = Double.valueOf(area.getMaxLong());
		Double minLongitude = Double.valueOf(area.getMinLong());
		Iterable<GeoTweet> records = geoTweetCollection
				.find(query, date, maxLatitude, minLatitude, maxLongitude, minLongitude)
				.as(GeoTweet.class);
		return CollectionHelper.asArrayList(records);
	}


	/**
	 * Query: returns how many geoTweets in the desired location.
	 * <p>
	 * Note: avoid auto boxing (Effective Java Item 49)
	 * 
	 * @param location known location.
	 * @return count of geoTweets in the location; -1 if unknown location
	 */
	public static long countGeoTweets(AvaibleLocations location) {
		Area area = Area.createLocation(location);
		if (area == null) {
			return -1;
		}
		String query = "{latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Double maxLatitude = Double.valueOf(area.getMaxLat());
		Double minLatitude = Double.valueOf(area.getMinLat());
		Double maxLongitude = Double.valueOf(area.getMaxLong());
		Double minLongitude = Double.valueOf(area.getMinLong());
		long count = geoTweetCollection
				.count(query, maxLatitude, minLatitude, maxLongitude, minLongitude);
		return count;
	}


	/**
	 * Read: returns the found geoTweet by id.
	 *
	 * @param id Mongo's ObjectId as String.
	 * @return found geoTweet or null otherwise.
	 */
	public static GeoTweet findGeoTweet(String id) {
		GeoTweet geoTweet = geoTweetCollection.findOne(new ObjectId(id)).as(GeoTweet.class);
		return geoTweet;
	}


	/**
	 * Update: change the category to an existing geoTweet. If the geoTweet does not exist in the DB
	 * this function will not create any geoTweet and will return false.
	 *
	 * @param geoTweet GeoTweet id.
	 * @param category new category.
	 * @return true if the geoTweet has been actualized; false otherwise.
	 */
	public static boolean updateCategoryToGeoTweet(String geoTweet, Category category) {
		GeoTweet foundTweet = geoTweetCollection
				.findOne(new ObjectId(geoTweet))
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