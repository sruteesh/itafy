package models.data;

import models.Word;
import org.jongo.MongoCollection;
import twitter4j.GeoLocation;
import controllers.db.NameDBs;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class WordsData extends ModelData {

	private static final MongoCollection words_collection = jongo.getCollection(NameDBs.WORDS);

	public WordsData() {}

	public static Word saveWord(String text, GeoLocation GeoLocation)
	{
		Word word = Word.createWordWithGeoLocation(text, GeoLocation);
		words_collection.save(word);
		return word;
	}

}
