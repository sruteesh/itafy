package models;

import java.util.ArrayList;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Model definition: Tweet
 *
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 * 
 * @see GeoTweet
 * @see Hashtag
 * @see Link
 * @see User
 * @see Word
 */
public class Tweet {

	@Id
	@ObjectId private String id;

	private String text;
	@ObjectId private String geoTweetId;
	private ArrayList<String> hashtagIds;
	private ArrayList<String> linkIds;
	@ObjectId private String userId;
	private ArrayList<String> wordIds;

	@JsonCreator
	public Tweet() {
		// TODO
	}

	// Example main class, do delete
	public Tweet(String text) {
		this.text = text;
		this.id = String.valueOf(Math.random());
	}

	@JsonProperty("_id")
	public String getId() { return id; }

	@JsonProperty("text")
	public String getText() {return text;}

	@JsonProperty("geotweet_id")
	public String getGeoTweetId() { return geoTweetId; }

	@JsonProperty("hashtag_ids")
	public ArrayList<String> getHashtagIds() { return hashtagIds; }

	@JsonProperty("link_ids")
	public ArrayList<String> getLinkIds() { return linkIds; }

	@JsonProperty("user_id")
	public String getUserId() { return userId; }

	@JsonProperty("word_ids")
	public ArrayList<String> getWordIds() { return wordIds; }

}
