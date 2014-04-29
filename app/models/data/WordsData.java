package models.data;

import java.util.ArrayList;
import models.entities.Word;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import twitter4j.GeoLocation;
import utils.helpers.CollectionHelper;
import controllers.db.DbNames;

/**
 * CRUD (create, read, update and destroy) for <code>Word</code> model in the DB.
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 * @see Word
 */
public class WordsData extends MongoClientData {
	private static final MongoCollection wordsCollection = jongoItafy.getCollection(DbNames.WORDS);

	/** No need to instanciate a <code>UserData</code> object */
	private WordsData() {}

	/**
	 * Create: saves the word into the DB
	 * 
	 * @param word
	 * @return Mongo's ObjectId as String
	 */
	public static String saveWord(Word word) {
		wordsCollection.save(word);
		return word.getId();
	}

	/**
	 * Create: creates and saves a new word instance into the DB
	 *
	 * @param text the word itself
	 * @param geoLocation the coordenates
	 * @return Mongo's ObjectId as String
	 */
	public static String saveWord(String text, GeoLocation geoLocation) {
		Word word = Word.createWordWithGeoLocation(text, geoLocation);
		wordsCollection.save(word);
		return word.getId();
	}

	/**
	 * Read: returns all the words in the DB as generic <code>Object</code> instances;
	 * casting expected.
	 *
	 * @return words or empty list otherwise.
	 */
	public static ArrayList<Object> getWords() {
		Iterable<Word> records = wordsCollection.find().as(Word.class);
		return CollectionHelper.asArrayList(records);
	}

	/**
	 * Read: returns word by id
	 *
	 * @param id Mongo's ObjectId as a String.
	 * @return found word or null otherwise.
	 */
	public static Word findWord(String id) {
		Word word = wordsCollection.findOne(new ObjectId(id)).as(Word.class);
		return word;
	}

	/**
	 * Destroy: remove a word from the DB.
	 * 
	 * @param id Mongo's ObjectId as String.
	 */
	public static void destroyWord(String id) {
		wordsCollection.remove(new ObjectId(id));
		return;
	}

}
