package models.entities;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Location {

	public static final String LONGITUDE = "longitude";
	public static final String LATITUDE = "latitude";

	private double longitude;
	private double latitude;

	public Location() {

	}

	@JsonCreator
	public Location(@JsonProperty(LONGITUDE) double longitude, @JsonProperty(LATITUDE) double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	@JsonProperty(LONGITUDE)
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@JsonProperty(LATITUDE)
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
