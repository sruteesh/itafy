package models.data;

import models.Hashtag;
import org.jongo.MongoCollection;
import twitter4j.GeoLocation;
import controllers.db.NameDBs;

/**
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@ucm.es
 */
public class HashtagData extends ModelData
{
	private static final MongoCollection hashtag_collection = jongo.getCollection(NameDBs.HASHTAGS);

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
		hashtag_collection.save(hashtag);
		return hashtag.getId();
	}

	/**
	 * Create: saves the hashtag instance into the DB.
	 *
	 * @param hashtag (Hashtag) instance going to be saved.
	 * @return (String) Mongo's ObjectId as String.
	 */
	public static String saveHashtag(Hashtag hashtag) {
		hashtag_collection.save(hashtag);
		return hashtag.getId();
	}

}
