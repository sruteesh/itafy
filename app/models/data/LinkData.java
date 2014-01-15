package models.data;

import models.Coordenate;
import models.Link;

import org.jongo.MongoCollection;

import controllers.db.NameDBs;

public class LinkData extends EntityData
{
  private static final MongoCollection tweet_collection = jongo.getCollection(NameDBs.GEO_TWEETS);

  public LinkData() {}
  
  
  // --------------------- //
  //    Classs methods     //
  // --------------------- //
  
  public static Link saveLink(String url, Coordenate coordenate)
  {
    Link link = Link.createLinkWithCoordenates(url, coordenate);
    tweet_collection.save(link);
    return link;
  }
  
} // LinkData
