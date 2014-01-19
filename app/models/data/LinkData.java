package models.data;

import models.Link;

import org.jongo.MongoCollection;

import twitter4j.GeoLocation;
import controllers.db.NameDBs;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class LinkData extends ModelData
{
  private static final MongoCollection tweet_collection = jongo.getCollection(NameDBs.GEO_TWEETS);

  public LinkData() {}


  // --------------------- //
  //    Classs methods     //
  // --------------------- //

  public static Link saveLink(String url, GeoLocation GeoLocation)
  {
    Link link = Link.createLinkWithGeoLocations(url, GeoLocation);
    tweet_collection.save(link);
    return link;
  }

} // LinkData
