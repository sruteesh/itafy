package models;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

/**
 * 
 * @author manutero, raulmarcosl
 */
public class User {
	private final String userName;
	private final long userId;
	private final boolean verified;
	private final String locationText;
	private final int followersCount;
	private final int followingCount;
	@Id
	@ObjectId
	private String id;

	public User(String userName, long userId, boolean verified, String location, int followersCount, int followingCount) {
		this.userName = userName;
		this.userId = userId;
		this.verified = verified;
		this.locationText = location;
		this.followersCount = followersCount;
		this.followingCount = followingCount;
	}

	public String getUserName() {
		return userName;
	}

	public long getUserId() {
		return userId;
	}

	public boolean isVerified() {
		return verified;
	}

	public String getLocationText() {
		return locationText;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public String getId() {
		return id;
	}

	public double getFollowersRatio()
	{
		return followersCount / followingCount;
	}

}
