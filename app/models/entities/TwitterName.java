package models.entities;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class TwitterName {

	public static final String NAME = "name";
	public static final String SCREEN_NAME = "screen_name";
	public static final String LANGUAGE = "language";
	public static final String LOCATION = "location";
	public static final String SPAIN = "spain";
	public static final String CREATED_AT = "created_at";

	@Id
	@ObjectId
	private String id;
	private String name;
	private String screenName;
	private String language;
	private Location location;
	private boolean spain;
	private Date createdAt;

	public TwitterName() {

	}

	@JsonProperty(NAME)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty(SCREEN_NAME)
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@JsonProperty(LOCATION)
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@JsonProperty(SPAIN)
	public boolean isSpain() {
		return spain;
	}

	public void setSpain(boolean spain) {
		this.spain = spain;
	}

	public String getId() {
		return id;
	}

	@JsonProperty(LANGUAGE)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@JsonProperty(CREATED_AT)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
