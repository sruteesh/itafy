package models.data;

import org.jongo.MongoCollection;

import controllers.db.NameDBs;

public class HashtagData extends EntityData
{
  private static final MongoCollection tweet_collection = jongo.getCollection(NameDBs.HASHTAGS);

  public HashtagData() {}
  
  //TODO
  
} // HashtagData
