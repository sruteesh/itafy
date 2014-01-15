package models.data;

import org.jongo.MongoCollection;

import controllers.db.NameDBs;

public class UserData extends EntityData
{
  private static final MongoCollection tweet_collection = jongo.getCollection(NameDBs.USERS);

  public UserData() {}
  
  //TODO
  
} // UerData
