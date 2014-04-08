package models.data;

import org.jongo.Jongo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import controllers.db.DbNames;
import controllers.db.MongoDBHandler;

/**
 * Definition of Mongo client and Jongo connection.
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public abstract class MongoClientData {
	protected static final MongoClient mongo = MongoDBHandler.getMongoClient();

	protected static final DB dbItafy = mongo.getDB(DbNames.DB_ITAFY);
	protected static final Jongo jongoItafy = new Jongo(dbItafy);

	protected static final DB dbItafyBenchmarks = mongo.getDB(DbNames.DB_ITAFY_BENCHMARKS);
	protected static final Jongo jongoItafyBenchmarks = new Jongo(dbItafyBenchmarks);
}
