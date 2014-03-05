package models.data;

import org.jongo.MongoCollection;
import controllers.db.DbNames;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class UserData extends MongoClientData
{
	private static final MongoCollection user_collection = jongoItafy.getCollection(DbNames.USERS);

	public UserData() {}

	// TODO

}
