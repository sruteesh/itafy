package models.data;

import org.jongo.MongoCollection;
import controllers.db.NameDBs;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class UserData extends ModelData
{
	private static final MongoCollection user_collection = jongo.getCollection(NameDBs.USERS);

	public UserData() {}

	// TODO

}
