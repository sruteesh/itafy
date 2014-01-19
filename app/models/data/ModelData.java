package models.data;

import org.jongo.Jongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import controllers.db.MongoDBHandler;
import controllers.db.NameDBs;

/**
 * 
 * @author manutero, raulmarcosl
 */
public abstract class ModelData
{
  protected static final MongoClient mongo = MongoDBHandler.getMongoClient();;
  protected static final DB db = mongo.getDB(NameDBs.DB_ITAFY);;
  protected static final Jongo jongo = new Jongo(db);
}
