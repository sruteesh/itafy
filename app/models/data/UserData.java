package models.data;

import org.jongo.MongoCollection;
import controllers.db.NameDBs;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class UserData extends MongoClientData
{
	private static final MongoCollection user_collection = jongoItafy.getCollection(NameDBs.USERS);

	public UserData() {}

	// TODO

}
