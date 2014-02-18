package models;

import java.util.Date;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model definition: User
 * 
 * <pre>
 * | field          | class    |
 * |:------------   |:-------- |
 * | userName       | String   |
 * | userId         | long     |
 * | verified       | boolean  |
 * | locationText   | String   |
 * | followersCount | int      |
 * | followingCount | int      |
 * | followersRatio | double   |
 * </pre>
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class User {

	@Id
	@ObjectId private String id;

	private String userName;
	private long userId;
	private boolean verified;
	private String locationText;
	private int followersCount;
	private int followingCount;

	private Date createdAt;
	private Date updatedAt;

	@JsonCreator
	public User() {
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	public User(String userName, long userId, boolean verified, String location, int followersCount, int followingCount) {
		this.userName = userName;
		this.userId = userId;
		this.verified = verified;
		this.locationText = location;
		this.followersCount = followersCount;
		this.followingCount = followingCount;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	@JsonProperty("user_name")
	public String getUserName() { return userName; }

	@JsonProperty("user_id")
	public long getUserId() { return userId; }

	@JsonProperty("verified")
	public boolean isVerified() { return verified; }

	@JsonProperty("location_text")
	public String getLocationText() { return locationText; }

	@JsonProperty("followers_count")
	public int getFollowersCount() { return followersCount; }

	@JsonProperty("following_count")
	public int getFollowingCount() { return followingCount; }

	@JsonProperty("followers_ratio")
	public double getFollowersRatio() {
		return followersCount / followingCount;
	}

	@JsonProperty("_id")
	public String getId() { return id; }

	@JsonProperty("created_at")
	public Date getCreatedAt() { return createdAt; }

	@JsonProperty("updated_at")
	public Date getUpdatedAt() { return updatedAt; }

}
