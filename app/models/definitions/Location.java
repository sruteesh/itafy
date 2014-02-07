package models.definitions;

import java.util.ArrayList;

/**
 * https://developers.google.com/maps/documentation/geocoding/?hl=es&csw=1
 * http://itouchmap.com/latlong.html
 */
public class Location
{
  public static final String MADRID = "madrid";

  public static ArrayList<String> avaibleLocations() {
    ArrayList<String> avaibleLocations = new ArrayList<String>();
    avaibleLocations.add(MADRID);
    return avaibleLocations;
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
   * @param location
   * @return (Location) new Location isntance or null.
   */
  public static Location createLocation(String location) {
    if (location.equals(MADRID)) {
      return new Location(
          MADRID_MAX_LATITUDE, MADRID_MAX_LONGITUDE,MADRID_MIN_LATITUDE, MADRID_MIN_LONGITUDE);
    } else {
      return null;
    }
  }

  public double getMaxLatitude() { return maxLatitude; }

  public double getMaxLongitude() { return maxLongitude; }

  public double getMinLatitude() { return minLatitude; }

  public double getMinLongitude() { return minLongitude; }

}
