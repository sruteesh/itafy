package models.data;

import java.util.ArrayList;

import models.Category;
import models.Link;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import twitter4j.GeoLocation;
import utils.Helper;
import controllers.db.NameDBs;

/**
 * CRUD (create, read, update, and destroy) for Link model in the DB.
 * 
 * @author martero@ucm.es & raul.marcos@ucm.es
 */
public class LinkData extends ModelData
{
  private static final MongoCollection linkCollection = jongo.getCollection(NameDBs.GEO_TWEETS);

  public LinkData() { }


  /**
   * Create: saves the link instance into the DB.
   * 
   * @param link instance going to be saved.
   * @return (String) Mongo's ObjectId as String.
   */
  public static String saveLink(Link link) {
    linkCollection.save(link);
    return link.getId();
  }

  /**
   * Create: creates and saves a new Link instance in the DB.
   * 
   * @param url the link itself.
   * @param GeoLocation coordenates of the link (latitude & longitude)
   * @return (String) Mongo's ObjectId as String.
   */
  public static String saveLink(String url, GeoLocation GeoLocation)
  {
    Link link = Link.createLinkWithGeoLocations(url, GeoLocation);
    linkCollection.save(link);
    return link.getId();
  }

  /**
   * Read: returns all the links in the DB as generic "Object" instances; casting expected
   * 
   * @return (ArrayList) all links or empty list otherwise.
   */
  public static ArrayList<Object> getAllLinks() {
    Iterable<Link> records = linkCollection.find().as(Link.class);

    if (records == null) {
      return new ArrayList<Object>();
    }
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
   * Read: returns links with this category as generic "Object" instances; casting expected.
   * 
   * @param category
   * @return (ArrayList) found links or empty list otherwise.
   */
  public static ArrayList<Object> getLinksByCategory(Category category) {
    Iterable<Link> records = linkCollection
        .find("{category: #}", category.getName())
        .as(Link.class);

    if (records == null) {
      return new ArrayList<Object>();
    }
    return Helper.asArrayList(records);
  }

  /**
   * Update: change the category to an existing link. If the link does not exist in the DB
   * this function will not create any link and will return false.
   * 
   * @param id link id.
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
   * @param id Mongo's ObjectId as String.
   */
  public static void destroyLink(String id) {
    linkCollection.remove(new ObjectId(id));
    return;
  }

}
