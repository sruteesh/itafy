package models.entities;

import java.util.ArrayList;
import java.util.Date;
import models.data.GeoTweetData;
import models.data.HashtagData;
import models.data.LinkData;
import models.data.UserData;
import models.data.WordsData;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Model definition: Tweet
 * <p>
 * Table which relates every model.
 * 
 * <ul>
 *  <li> a tweet (always) has one geoTweet
 *  <li> a tweet (always) has one user
 *  <li> a tweet (could) has many hashtags
 *  <li> a tweet (could) has many links
 *  <li> a tweet (could) has many words
 * </ul>
 *
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
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

	private Date createdAt;
	private Date updatedAt;

	@JsonCreator
	public Tweet() {
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	private Tweet(String text, String geoTweetId, String userId) {
		this.text = text;
		this.geoTweetId = geoTweetId;
		this.hashtagIds = null;
		this.linkIds = null;
		this.userId = userId;
		this.wordIds = null;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	/**
	 * Factory
	 * 
	 * @param text the tweet itself
	 * @param geoTweetId Mongo's id as a String
	 * @param userId Mongo's id as a String
	 * @return new <code>Tweet</code> instance
	 */
	public static Tweet createTweetWithGeoTweetAndUser(String text, String geoTweetId, String userId) {
		return new Tweet(text, geoTweetId, userId);
	}


	// public methods

	public void addHashtag(String hashtagId) {
		if (hashtagIds == null) {
			hashtagIds = new ArrayList<String>();
		}
		hashtagIds.add(hashtagId);
		updatedAt = new Date();
	}

	public void addLink(String linkId) {
		if (linkIds == null) {
			linkIds = new ArrayList<String>();
		}
		linkIds.add(linkId);
		updatedAt = new Date();
	}

	public void addWord(String wordId) {
		if (wordIds == null) {
			wordIds = new ArrayList<String>();
		}
		wordIds.add(wordId);
		updatedAt = new Date();
	}


	// getters

	public GeoTweet getGeoTweet() {
		return GeoTweetData.findGeoTweet(this.geoTweetId);
	}

	public ArrayList<Hashtag> getHashtags() {
		ArrayList<Hashtag> response = new ArrayList<Hashtag>();
		if (hashtagIds != null) {
			for (String hashtagId : hashtagIds) {
				Hashtag hashtag = HashtagData.findHashtagById(hashtagId);
				response.add(hashtag);
			}
		}
		return response;
	}

	public ArrayList<Link> getLinks() {
		ArrayList<Link> response = new ArrayList<Link>();
		if (linkIds != null) {
			for (String linkId : linkIds) {
				Link link = LinkData.findLink(linkId);
				response.add(link);
			}
		}
		return response;
	}

	public User getUser() {
		return UserData.findUser(this.userId);
	}

	public ArrayList<Word> getWords() {
		ArrayList<Word> response = new ArrayList<Word>();
		if (wordIds != null) {
			for (String wordId : wordIds) {
				Word word = WordsData.findWord(wordId);
				response.add(word);
			}
		}
		return response;
	}

	@JsonProperty("_id")
	public String getId() { return id; }

	@JsonProperty("text")
	public String getText() { return text; }

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

	@JsonProperty("created_at")
	public Date getCreatedAt() { return createdAt; }

	@JsonProperty("updated_at")
	public Date getUpdatedAt() { return updatedAt; }


	// setters

	public void setHashtagIds(ArrayList<String> hashtagIds) { this.hashtagIds = hashtagIds; }
	public void setLinkIds(ArrayList<String> linkIds) { this.linkIds = linkIds; }
	public void setWordIds(ArrayList<String> wordIds) { this.wordIds = wordIds; }

}
