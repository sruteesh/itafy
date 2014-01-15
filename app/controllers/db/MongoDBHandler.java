package controllers.db;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class MongoDBHandler
{
	private static MongoClient mongoClient;

	static {
		try {
			MongoDBHandler.mongoClient = new MongoClient("127.0.0.1", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static MongoClient getMongoClient()
	{
		return MongoDBHandler.mongoClient;
	}
}
