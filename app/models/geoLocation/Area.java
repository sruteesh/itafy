package models.geoLocation;


/**
 * Area definition
 *
 * @see https://developers.google.com/maps/documentation/geocoding/?hl=es&csw=1
 * @see http://itouchmap.com/latlong.html
 * @see Stackoverflow #1750435
 * @author m.artero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class Area {

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
	public static final double MADRID_MIN_LONGITUDE = -4.0;
	public static final double MADRID_MAX_LONGITUDE = -3.3;
	public static final double MADRID_MIN_LATITUDE = 40.2;

	/*     BARCELONA
	 *
	 *        41.5
	 *     |-------|
	 *     |       |
	 * 2.0 |       | 2.4
	 *     |       |
	 *     |-------|
	 *        41.2
	 */

	public static final double BARCELONA_MAX_LATITUDE = 41.5;
	public static final double BARCELONA_MIN_LONGITUDE = 2.0;
	public static final double BARCELONA_MAX_LONGITUDE = 2.4;
	public static final double BARCELONA_MIN_LATITUDE = 41.2;


	// attributes
	private double maxLatitude;
	private double maxLongitude;
	private double minLatitude;
	private double minLongitude;

	private Area(double maxLat, double maxLon, double minLat, double minLon) {
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
	public static Area createLocation(String location) {
		AvaibleLocations trustedLocation = AvaibleLocations.asLocation(location);
		return createLocation(trustedLocation);
	}

	/**
	 * Factory.
	 *
	 * @param trustedLocation
	 * @return (Location) new Location instance or null.
	 */
	public static Area createLocation(AvaibleLocations trustedLocation) {
		Area area = null;
		if (trustedLocation != null) {
			if (trustedLocation == AvaibleLocations.MADRID) {
				area = new Area(MADRID_MAX_LATITUDE, MADRID_MAX_LONGITUDE, MADRID_MIN_LATITUDE, MADRID_MIN_LONGITUDE);
			} else if (trustedLocation == AvaibleLocations.BARCELONA) {
				area = new Area(BARCELONA_MAX_LATITUDE, BARCELONA_MAX_LONGITUDE, BARCELONA_MIN_LATITUDE, BARCELONA_MIN_LONGITUDE);
			}
		}
		return area;
	}


	// getters
	public double getMaxLat() { return maxLatitude; }
	public double getMaxLong() { return maxLongitude; }
	public double getMinLat() { return minLatitude; }
	public double getMinLong() { return minLongitude; }

}
