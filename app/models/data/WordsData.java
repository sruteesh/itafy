package models.data;

import models.Coordenate;
import models.Word;

import org.jongo.MongoCollection;

import controllers.db.NameDBs;

public class WordsData extends EntityData
{
  private static final MongoCollection tweet_collection = jongo.getCollection(NameDBs.WORDS);

  public WordsData() {}
  
  
  // --------------------- //
  //    Classs methods     //
  // --------------------- //
  
  public static Word saveWord(String text, Coordenate coordenate)
  {
    Word word = Word.createWordWithCoordenate(text, coordenate);
    tweet_collection.save(word);
    return word;
  }
  
} // WordsData
