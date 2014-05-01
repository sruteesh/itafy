package utils.helpers;

import java.util.ArrayList;
import models.entities.Tweet;

/**
 * Auxiliar class which defines possible useful common methods for manipulating collections
 * <p>
 * Note: all methods would be static; not reason to instanciate or extend this class.
 * 
 * @see Stackoverflow #1844355
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public final class CollectionHelper {

	/**
	 * Suppress default constructor for noninstantiability; "Effective Java" Item 4.
	 * @throws AssertionError
	 */
	private CollectionHelper() {
		throw new AssertionError();
	}


	/**
	 * Converts collection of Mongo's results into ArrayList.<br>
	 * If <code>collection</code> is null, it would return a empty arrayList
	 * <p>
	 * Note: parameter type T is meant to be one of the possible models.
	 * 
	 * @param collection (Iterable) result of a <code>find()</code> query.
	 * @return (ArrayList) intances as generic "Object" objects or empty arrayList.
	 */
	public static <T> ArrayList<Object> asArrayList(Iterable<T> collection) {
		ArrayList<Object> list = new ArrayList<Object>();
		if (collection != null) {
			for (T object : collection) {
				list.add(object);
			}
		}
		return list;
	}


	/**
	 * Returns the same collection but each element casted to <code>Tweet</code> class
	 * 
	 * @param collection
	 * @return each element casted
	 * @see Stackoverflow #933447
	 */
	public static ArrayList<Tweet> castEeachElementToTweet(ArrayList<Object> collection) {
		ArrayList<Tweet> response = new ArrayList<Tweet>();
		if (collection != null) {
			for (Object item : collection) {
				try {
					response.add((Tweet) item);
				} catch (ClassCastException e) {
					e.printStackTrace();
				}
			}
		}
		return response;
	}

}
