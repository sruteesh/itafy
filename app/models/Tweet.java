package models;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Model definition: Tweet
 *
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 * @see GeoTweet
 * @see Hashtag
 * @see Link
 * @see User
 * @see Word
 */
public class Tweet {

	@Id
	@ObjectId private String id;

	private String status;
	@ObjectId private String geoTweetId;
	@ObjectId private String hashtagId;
	@ObjectId private String linkId;
	@ObjectId private String userId;
	@ObjectId private String wordId;

	@JsonCreator
	public Tweet() {
		// TODO
	}

	@JsonProperty("_id")
	public String getId() { return id; }

	@JsonProperty("status")
	public String getStatus() {return status;}

	@JsonProperty("geo_tweet_id")
	public String getGeoTweetId() { return geoTweetId; }

	@JsonProperty("hashtag_id")
	public String getHashtagId() { return hashtagId; }

	@JsonProperty("link_id")
	public String getLinkId() { return linkId; }

	@JsonProperty("user_id")
	public String getUserId() { return userId; }

	@JsonProperty("word_id")
	public String getWordId() { return wordId; }

}
