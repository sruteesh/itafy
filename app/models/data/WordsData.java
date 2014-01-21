package models.data;

import models.Word;

import org.jongo.MongoCollection;

import twitter4j.GeoLocation;
import controllers.db.NameDBs;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class WordsData extends ModelData
{
	private static final MongoCollection tweet_collection = jongo.getCollection(NameDBs.WORDS);

	public WordsData() {
	}

	// --------------------- //
	// Classs methods //
	// --------------------- //

	public static Word saveWord(String text, GeoLocation GeoLocation)
	{
		Word word = Word.createWordWithGeoLocation(text, GeoLocation);
		tweet_collection.save(word);
		return word;
	}

} // WordsData
