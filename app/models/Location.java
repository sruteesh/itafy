package models;





/**
 * Location model & enum definition: AvaibleLocations
 *
 * @see
 *  - https://developers.google.com/maps/documentation/geocoding/?hl=es&csw=1
 *  - http://itouchmap.com/latlong.html
 *  - Stackoverflow #1750435
 *
 *  @author martero@ucm.es & raul.marcos@ucm.es
 */
public class Location
{

	public enum AvaibleLocations {
		MADRID;

		/**
		 * Converts the input String into a known location defined on
		 * Location.AvaibleLocations. Will return null if unkown String given.
		 *
		 * @param location String we want to convert
		 * @return (Location.AvaibleLocations) known location or null if unkown
		 * String given.
		 */
		public static AvaibleLocations asLocation(String location) {
			if (location != null) {
				if (location.equals("madrid")) {
					return MADRID;
				}
			}
			return null;
		}

	}

	/*   MADRID
	 *
	 *      40.6
	 *    |-------|
	 *    |       |
	 * -4 |       | -3.3
	 *    |       |
	 *    |-------|
	 *      40.2
	 */

	public static final double MADRID_MAX_LATITUDE = 40.6;
	public static final double MADRID_MIN_LONGITUDE = -4;
	public static final double MADRID_MAX_LONGITUDE = -3.3;
	public static final double MADRID_MIN_LATITUDE = 40.2;

	private double maxLatitude;
	private double maxLongitude;
	private double minLatitude;
	private double minLongitude;

	private Location(double maxLat, double maxLon, double minLat, double minLon) {
		this.maxLatitude = maxLat;
		this.maxLongitude = maxLon;
		this.minLatitude = minLat;
		this.minLongitude = minLon;
	}

	/**
	 * Factory.
	 *
	 * @param location must be checked if is trusted location
	 * @return (Location) new Location isntance or null.
	 */
	public static Location createLocation(String location) {
		AvaibleLocations trustedLocation = AvaibleLocations.asLocation(location);
		return createLocation(trustedLocation);
	}

	/**
	 * Factory.
	 *
	 * @param trustedLocation
	 * @return (Location) new Location instance or null.
	 */
	public static Location createLocation(AvaibleLocations trustedLocation) {
		if (trustedLocation == AvaibleLocations.MADRID) {
			return new Location(
					MADRID_MAX_LATITUDE, MADRID_MAX_LONGITUDE,MADRID_MIN_LATITUDE, MADRID_MIN_LONGITUDE);
		} else {
			return null;
		}
	}

	public double getMaxLat() { return maxLatitude; }

	public double getMaxLong() { return maxLongitude; }

	public double getMinLat() { return minLatitude; }

	public double getMinLong() { return minLongitude; }

}
