package models.data;

import java.util.ArrayList;
import models.entities.User;
import models.geoLocation.Area;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import utils.helpers.CollectionHelper;
import controllers.db.DbNames;

/**
 * CRUD (create, read, update and destroy) for <code>User</code> model in the DB.
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see User
 */
public class UserData extends MongoClientData {
	private static final MongoCollection userCollection = jongoItafy.getCollection(DbNames.USERS);

	/** No need to instanciate a <code>UserData</code> object */
	private UserData() {}

	/**
	 * Create: saves the user instance into the DB.
	 *
	 * @param user instance going to be saved.
	 * @return (String) Mongo's ObjectId as String.
	 */
	public static String saveUser(User user) {
		userCollection.save(user);
		return user.getId();
	}

	/**
	 * Read: returns all the users in the DB as generic <code>Object</code> instances;
	 * casting expected.
	 *
	 * @return (ArrayList) all useres or empty list otherwise.
	 */
	public static ArrayList<Object> getUsers() {
		Iterable<User> records = userCollection.find().as(User.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: returns users in the desired location as generic <code>Object</code> instances;
	 * casting expected.
	 * <p>
	 * Note: avoid auto boxing (Effective Java Item 49)
	 * 
	 * @param area
	 * @return users in location or empty list otherwise.
	 */
	public static ArrayList<Object> getUsers(Area area) {
		String query = "{latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Double maxLatitude = Double.valueOf(area.getMaxLat());
		Double minLatitude = Double.valueOf(area.getMinLat());
		Double maxLongitude = Double.valueOf(area.getMaxLong());
		Double minLongitude = Double.valueOf(area.getMinLong());
		Iterable<User> records = userCollection
				.find(query, maxLatitude, minLatitude, maxLongitude, minLongitude)
				.as(User.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: returns users with the selected genre as generic <code>Object</code> instances;
	 * casting expected.
	 * 
	 * FIXME Change the sex to be a Enum instead of a String
	 * 
	 * @param genre
	 * @return users with the selected genre or empry list otherwise.
	 */
	public static ArrayList<Object> getUsers(String genre) {
		String query = "{genre: #}";
		Iterable<User> records = userCollection
				.find(query, genre)
				.as(User.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: returns the user with the selected Twitter's userId
	 * 
	 * @param userId defined by Twitter
	 * @return user with the selected Twitter's id.
	 */
	public static User findUser(long userId) {
		String query = "{user_id: #}";
		Long id = Long.valueOf(userId);
		User user = userCollection.findOne(query, id).as(User.class);
		return user;
	}

	/**
	 * Read: returns users with the selected genre and location as <code>Object</code> instances;
	 * casting expected.
	 * <p>
	 * Note: avoid auto boxing (Effective Java Item 49)
	 * 
	 * FIXME Chage the sex to be a Enum instead a String
	 * 
	 * @param area
	 * @param genre
	 * @return users in location and selected genre or empty list otherwise.
	 */
	public static ArrayList<Object> getUsers(Area area, String genre) {
		String query = "{genre: #, latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Double maxLatitude = Double.valueOf(area.getMaxLat());
		Double minLatitude = Double.valueOf(area.getMinLat());
		Double maxLongitude = Double.valueOf(area.getMaxLong());
		Double minLongitude = Double.valueOf(area.getMinLong());
		Iterable<User> records = userCollection
				.find(query, genre, maxLatitude, minLatitude, maxLongitude, minLongitude)
				.as(User.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Query: returns how many users in the desired location.
	 * 
	 * @param area
	 * @return count of users in the area
	 */
	public static long countUsers(Area area) {
		String query = "{latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Double maxLatitude = Double.valueOf(area.getMaxLat());
		Double minLatitude = Double.valueOf(area.getMinLat());
		Double maxLongitude = Double.valueOf(area.getMaxLong());
		Double minLongitude = Double.valueOf(area.getMinLong());
		long count = userCollection.count(query, maxLatitude, minLatitude, maxLongitude, minLongitude);
		return count;
	}

	/**
	 * Query: returns how many users with the selected genre
	 * 
	 * FIXME Chage the sex to be a Enum instead a String
	 * 
	 * @param genre genre of the users
	 * @return count of users with the selected genre
	 */
	public static long countUsers(String genre) {
		String query = "{genre: #}";
		long count = userCollection.count(query);
		return count;
	}

	/**
	 * Read: returns user by id
	 *
	 * @param id Mongo's ObjectId as a String.
	 * @return found user or null otherwise.
	 */
	public static User findUser(String id) {
		User user = userCollection.findOne(new ObjectId(id)).as(User.class);
		return user;
	}

	/**
	 * Read: returns user by twitter id
	 * 
	 * @param id twitterId
	 * @return found user or null otherwise
	 */
	public static User findByTwitterId(long id) {
		String query = "{user_id: #}";
		Long userId = Long.valueOf(id);
		User user = userCollection.findOne(query, userId).as(User.class);
		return user;
	}


	/**
	 * Update: change the genre to an existing user.</br>
	 * If the user doesn't exist in the DB this function will not create any user and will return false.
	 *
	 * FIXME Chage the sex to be a Enum instead a String
	 *
	 * @param id Mongo's ObjectId as a String
	 * @param genre new genre
	 * @return true if the user has been updated; false otherwise.
	 */
	public static boolean updateCategoryToGeoTweet(String id, String genre) {
		User user = userCollection
				.findOne(new ObjectId(id))
				.as(User.class);

		if (user == null) {
			return false;
		}

		user.setGenre(genre);
		userCollection.save(user);
		return true;
	}

	/**
	 * Destroy: remove a User from the DB.
	 * 
	 * @param id Mongo's ObjectId as String.
	 */
	public static void destroyGeoTweet(String id) {
		userCollection.remove(new ObjectId(id));
		return;
	}

}
