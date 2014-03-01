package models.entities;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Location {
	private double longitude;
	private double latitude;

	private static final Location SPAIN_TOP_LEFT_CORNER = new Location(44.65302, -10.31620);
	private static final Location SPAIN_BOTTOM_RIGHT_CORNER = new Location(34.95799, 3.13107);

	private static final double SPAIN_TOP_LEFT_CORNER_LONGITUDE = 44.65302;
	private static final double SPAIN_TOP_LEFT_CORNER_LATITUDE = -10.31620;
	private static final double SPAIN_BOTTOM_RIGHT_CORNER_LONGITUDE = 34.95799;
	private static final double SPAIN_BOTTOM_RIGHT_CORNER_LATITUDE = 3.13107;

	public Location() {

	}

	@JsonCreator
	public Location(@JsonProperty("longitude") double longitude, @JsonProperty("latitude") double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	@JsonProperty("longitude")
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@JsonProperty("latitude")
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public static boolean isInSpain(double longitude, double latitude) {
		return (longitude <= SPAIN_BOTTOM_RIGHT_CORNER_LONGITUDE && longitude >= SPAIN_TOP_LEFT_CORNER_LONGITUDE
				&& latitude <= SPAIN_BOTTOM_RIGHT_CORNER_LATITUDE && latitude >= SPAIN_TOP_LEFT_CORNER_LATITUDE);
	}
}
