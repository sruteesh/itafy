package models.data;

import java.util.ArrayList;
import models.Category;
import models.Category.AvaibleCategories;
import models.GeoTweet;
import models.Hashtag;
import models.Location;
import models.Location.AvaibleLocations;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import twitter4j.GeoLocation;
import utils.Helper;
import controllers.db.NameDBs;

/**
 * CRUD (create, read, update, and destroy) for <code>Hashtag</code> model in the DB.
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * @see Hashtag
 */
public class HashtagData extends MongoClientData {
	protected static final MongoCollection hashtagCollection = jongo.getCollection(NameDBs.HASHTAGS);

	public HashtagData() {}

	/**
	 * Create: creates and saves a new hashtag instance in the DB.
	 *
	 * @param text (String) defines this hashtag.
	 * @param geoLocation (GeoLocation) coordenates for this hashtag (latitude & longitude).
	 * @return (String) Mongo's ObjectId as String.
	 */
	public static String saveHashtag(String text, GeoLocation geoLocation) {
		Hashtag hashtag = Hashtag.createHashtagWithGeoLocations(text, geoLocation);
		hashtagCollection.save(hashtag);
		return hashtag.getId();
	}

	/**
	 * Create: saves the hashtag instance into the DB.
	 *
	 * @param hashtag (Hashtag) instance going to be saved.
	 * @return (String) Mongo's ObjectId as String.
	 */
	public static String saveHashtag(Hashtag hashtag) {
		hashtagCollection.save(hashtag);
		return hashtag.getId();
	}

	/**
	 * Read: returns all the hashtags in the DB as generic <code>Object</code> instances;
	 * casting expected.
	 *
	 * @return (ArrayList) all geoTweets or empty list otherwise.
	 */
	public static ArrayList<Object> getAllHashtags() {
		Iterable<Hashtag> records = hashtagCollection.find().as(Hashtag.class);
		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns geoTweets in the desired location as generic <code>Object</code>
	 * instances.
	 *
	 * @param location (Enum) known location.
	 * @return (ArrayList) all hashtags in location or empty list otherwise.
	 */
	public static ArrayList<Object> getAllHashtags(AvaibleLocations location) {
		Location area = Location.createLocation(location);
		if (area == null) {
			return new ArrayList<Object>();
		}

		String query = "{latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Iterable<Hashtag> records = hashtagCollection
				.find(query, area.getMaxLat(), area.getMinLat(), area.getMaxLong(), area.getMinLong())
				.as(Hashtag.class);

		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns hashtags with the desired category as generic <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param cat (Enum) known category.
	 * @return (ArrayList) all hashtags with category or empty list otherwise.
	 */
	public static ArrayList<Object> getAllHashtags(AvaibleCategories cat) {
		Category category = Category.createCategory(cat);
		if (category == null) {
			return new ArrayList<Object>();
		}

		Iterable<Hashtag> records = hashtagCollection
				.find("{category: #}", category.getName())
				.as(Hashtag.class);

		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns hashtags with the desired category and location as <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param location (Enum) known location.
	 * @param category (Enum) known category.
	 * @return (ArrayList) all hashtags in location and categorized or empty list otherwise.
	 */
	public static ArrayList<Object> getAllHashtags(AvaibleLocations location, AvaibleCategories category) {
		Location loc = Location.createLocation(location);
		Category cat = Category.createCategory(category);
		if ((loc == null) || (cat == null)) {
			return new ArrayList<Object>();
		}

		String query = "{category: #, latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Iterable<GeoTweet> records = hashtagCollection
				.find(query, cat.getName(), loc.getMaxLat(), loc.getMinLat(), loc.getMaxLong(), loc.getMinLong())
				.as(GeoTweet.class);

		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns the found hashtag by id.
	 *
	 * @param id (String) Mongo's ObjectId as String.
	 * @return (GeoTweet) found hashtag or null otherwise.
	 */
	public static Hashtag getHashtagById(String id) {
		Hashtag hashtag = hashtagCollection.findOne(new ObjectId(id)).as(Hashtag.class);
		return hashtag;
	}

}
