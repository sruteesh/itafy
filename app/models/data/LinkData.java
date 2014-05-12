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
import utils.helpers.CollectionHelper;
import controllers.db.DbNames;

/**
 * CRUD (create, read, update, and destroy) for <code>Link</code> model in the DB.
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * @see Link
 */
public class LinkData extends MongoClientData {
	protected static final MongoCollection linkCollection = jongoItafy.getCollection(DbNames.LINKS);

	/** No need to instanciate a <code>UserData</code> object */
	private LinkData() {}

	/**
	 * Create: creates and saves a new link instance in the DB.
	 * 
	 * @param url the link itself.
	 * @param geoLocation coordenates of the link (latitude & longitude)
	 * @return Mongo's ObjectId as String.
	 */
	public static String saveLink(String url, GeoLocation geoLocation) {
		Link link = Link.createLinkWithGeoLocations(url, geoLocation);
		linkCollection.save(link);
		return link.getId();
	}

	/**
	 * Create: saves the link instance into the DB.
	 * 
	 * @param link instance going to be saved.
	 * @return Mongo's ObjectId as String.
	 */
	public static String saveLink(Link link) {
		linkCollection.save(link);
		return link.getId();
	}

	/**
	 * Read: returns all the links in the DB as generic <code>Object</code> instances;
	 * casting expected
	 * 
	 * @return all links or empty list otherwise.
	 */
	public static ArrayList<Object> getAllLinks() {
		Iterable<Link> records = linkCollection.find().as(Link.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: returns links in the desired location as generic <code>Object</code>
	 * instances.
	 *
	 * @param location known location.
	 * @return all links in location or empty list otherwise.
	 */
	public static ArrayList<Object> getAllLinks(AvaibleLocations location) {
		Area area = Area.createLocation(location);
		if (area == null) {
			return new ArrayList<Object>();
		}

		String query = "{latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Double maxLatitude = Double.valueOf(area.getMaxLat());
		Double minLatitude = Double.valueOf(area.getMinLat());
		Double maxLongitude = Double.valueOf(area.getMaxLong());
		Double minLongitude = Double.valueOf(area.getMinLong());

		Iterable<Link> records = linkCollection
				.find(query, maxLatitude, minLatitude, maxLongitude, minLongitude)
				.as(Link.class);

		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: returns lniks with the desired category as generic <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param cat known category.
	 * @return all hashtags with category or empty list otherwise.
	 */
	public static ArrayList<Object> getAllLinks(AvaibleCategories cat) {
		Category category = Category.createCategory(cat);
		if (category == null) {
			return new ArrayList<Object>();
		}

		Iterable<Link> records = linkCollection
				.find("{category: #}", category.getName())
				.as(Link.class);

		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: returns links with the desired category and location as <code>Object</code> instances;
	 * casting expected.
	 * 
	 * @param location known location.
	 * @param category known category.
	 * @return all links in location and categorized or empty list otherwise.
	 */
	public static ArrayList<Object> getAllLinks(AvaibleLocations location, AvaibleCategories category) {
		Area area = Area.createLocation(location);
		Category cat = Category.createCategory(category);
		if ((area == null) || (cat == null)) {
			return new ArrayList<Object>();
		}

		String query = "{category: #, latitude: {$lt:#, $gt:#}, longitude:{$lt:#, $gt:#}}";
		Double maxLatitude = Double.valueOf(area.getMaxLat());
		Double minLatitude = Double.valueOf(area.getMinLat());
		Double maxLongitude = Double.valueOf(area.getMaxLong());
		Double minLongitude = Double.valueOf(area.getMinLong());

		Iterable<Link> records = linkCollection
				.find(query, cat.getName(), maxLatitude, minLatitude, maxLongitude, minLongitude)
				.as(Link.class);

		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: returns the found link by id.
	 * 
	 * @param id Mongo's ObjectId as String.
	 * @return found link or null otherwise.
	 */
	public static Link findLink(String id) {
		Link link = linkCollection.findOne(new ObjectId(id)).as(Link.class);
		return link;
	}

	/**
	 * Update: change the category to an existing link. If the link does not exist in the DB
	 * this function will not create any link and will return false.
	 * 
	 * @param id link id.
	 * @param category new category.
	 * @return true if the link has been actualized; false otherwise.
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
	 * @param linkId Mongo's ObjectId as String.
	 */
	public static void destroyLink(String linkId) {
		linkCollection.remove(new ObjectId(linkId));
		return;
	}

}
