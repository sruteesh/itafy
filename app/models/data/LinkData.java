package models.data;

import java.util.ArrayList;
import models.categories.AvaibleCategories;
import models.categories.Category;
import models.entities.Link;
import models.geoLocation.Area;
import models.geoLocation.AvaibleLocations;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import twitter4j.GeoLocation;
import utils.Helper;
import controllers.db.NameDBs;

/**
 * CRUD (create, read, update, and destroy) for <code>Link</code> model in the DB.
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * @see Link
 */
public class LinkData extends MongoClientData {
	protected static final MongoCollection linkCollection = jongo.getCollection(NameDBs.LINKS);

	public LinkData() {}

	/**
	 * Create: creates and saves a new link instance in the DB.
	 * 
	 * @param url (String) the link itself.
	 * @param geoLocation (GeoLocation) coordenates of the link (latitude & longitude)
	 * @return (String) Mongo's ObjectId as String.
	 */
	public static String saveLink(String url, GeoLocation geoLocation) {
		Link link = Link.createLinkWithGeoLocations(url, geoLocation);
		linkCollection.save(link);
		return link.getId();
	}

	/**
	 * Create: saves the link instance into the DB.
	 * 
	 * @param link (Link) instance going to be saved.
	 * @return (String) Mongo's ObjectId as String.
	 */
	public static String saveLink(Link link) {
		linkCollection.save(link);
		return link.getId();
	}

	/**
	 * Read: returns all the links in the DB as generic <code>Object</code> instances;
	 * casting expected
	 * 
	 * @return (ArrayList) all links or empty list otherwise.
	 */
	public static ArrayList<Object> getAllLinks() {
		Iterable<Link> records = linkCollection.find().as(Link.class);
		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns links in the desired location as generic <code>Object</code>
	 * instances.
	 *
	 * @param location (Enum) known location.
	 * @return (ArrayList) all links in location or empty list otherwise.
	 */
	public static ArrayList<Object> getAllLinks(AvaibleLocations location) {
		Area area = Area.createLocation(location);
		if (area == null) {
			return new ArrayList<Object>();
		}

		String query = "{latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Iterable<Link> records = linkCollection
				.find(query, area.getMaxLat(), area.getMinLat(), area.getMaxLong(), area.getMinLong())
				.as(Link.class);

		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns lniks with the desired category as generic <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param cat (Enum) known category.
	 * @return (ArrayList) all hashtags with category or empty list otherwise.
	 */
	public static ArrayList<Object> getAllLinks(AvaibleCategories cat) {
		Category category = Category.createCategory(cat);
		if (category == null) {
			return new ArrayList<Object>();
		}

		Iterable<Link> records = linkCollection
				.find("{category: #}", category.getName())
				.as(Link.class);

		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns links with the desired category and location as <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param location (Enum) known location.
	 * @param category (Enum) known category.
	 * @return (ArrayList) all links in location and categorized or empty list otherwise.
	 */
	public static ArrayList<Object> getAllLinks(AvaibleLocations location, AvaibleCategories category) {
		Area loc = Area.createLocation(location);
		Category cat = Category.createCategory(category);
		if ((loc == null) || (cat == null)) {
			return new ArrayList<Object>();
		}

		String query = "{category: #, latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Iterable<Link> records = linkCollection
				.find(query, cat.getName(), loc.getMaxLat(), loc.getMinLat(), loc.getMaxLong(), loc.getMinLong())
				.as(Link.class);

		return Helper.asArrayList(records);
	}

	/**
	 * Read: returns the found link by id.
	 * 
	 * @param id Mongo's ObjectId as String.
	 * @return (GeoTweet) found link or null otherwise.
	 */
	public static Link getGeoTweetById(String id) {
		Link link = linkCollection.findOne(new ObjectId(id)).as(Link.class);
		return link;
	}

	/**
	 * Update: change the category to an existing link. If the link does not exist in the DB
	 * this function will not create any link and will return false.
	 * 
	 * @param id (String) link id.
	 * @param category new category.
	 * @return (boolean) true if the link has been actualized; false otherwise.
	 */
	public static boolean updateCategoryToLink(String id, Category category) {
		Link foundLink = linkCollection
				.findOne(new ObjectId(id))
				.as(Link.class);

		if (foundLink == null) {
			return false;
		}

		foundLink.setCategory(category);
		linkCollection.save(foundLink);
		return true;
	}

	/**
	 * Destroy: remove a link from the DB.
	 * 
	 * @param linkId (String) Mongo's ObjectId as String.
	 */
	public static void destroyLink(String linkId) {
		linkCollection.remove(new ObjectId(linkId));
		return;
	}

}
