package models.entities;

import java.util.ArrayList;
import java.util.Date;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class Tweet {

	private String rawTweet;
	private Date createdAt;
	private ArrayList<String> hashtagEntities;
	@Id
	@ObjectId
	private String id;
	private String inReplyToScreenName;
	private long inReplyToStatusId;
	private long inReplyToUserId;

	private boolean isPossiblySensitive;

	// status.getgeolocation.getlatitude
	private double latitude;

	private double longitude;

	private long messageId;
	// toString of Place object
	private String place;

	private long retweetCount;
	private String source;
	private String text;

	private ArrayList<String> urlEntities;
	private TwitterUser user;
	private ArrayList<String> userMentionEntities;

	private String category;
	private String gender;

	public Tweet() {

	}

	public String getRawTweet() {
		return rawTweet;
	}

	public void setRawTweet(String rawTweet) {
		this.rawTweet = rawTweet;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public ArrayList<String> getHashtagEntities() {
		return hashtagEntities;
	}

	public void setHashtagEntities(ArrayList<String> hashtagEntities) {
		this.hashtagEntities = hashtagEntities;
	}

	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}

	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}

	public long getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	public void setInReplyToStatusId(long inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	public long getInReplyToUserId() {
		return inReplyToUserId;
	}

	public void setInReplyToUserId(long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	public boolean isPossiblySensitive() {
		return isPossiblySensitive;
	}

	public void setPossiblySensitive(boolean isPossiblySensitive) {
		this.isPossiblySensitive = isPossiblySensitive;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public long getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(long retweetCount) {
		this.retweetCount = retweetCount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> getUrlEntities() {
		return urlEntities;
	}

	public void setUrlEntities(ArrayList<String> urlEntities) {
		this.urlEntities = urlEntities;
	}

	public TwitterUser getUser() {
		return user;
	}

	public void setUser(TwitterUser user) {
		this.user = user;
	}

	public ArrayList<String> getUserMentionEntities() {
		return userMentionEntities;
	}

	public void setUserMentionEntities(ArrayList<String> userMentionEntities) {
		this.userMentionEntities = userMentionEntities;
	}

	public String getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}