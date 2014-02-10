package api;

import twitter4j.GeoLocation;

/**
 * Fixture definition for <code>ApiTest</code>
 * 
 * @author martero@ucm.es
 * @author raul.marcos@ucm.es
 * @see ApiTest
 */
public class ApiFixtures {

  // ids for tests
  public static String TWEET_1;
  public static String TWEET_2;
  public static String TWEET_3;
  public static String TWEET_4;

  // Twitter ids
  public static final long TWEET_1_ID = 11265371;
  public static final long TWEET_2_ID = 11265372;
  public static final long TWEET_3_ID = 11265373;
  public static final long TWEET_4_ID = 11265374;

  // coordenates in Madrid
  public static final double LAT_1 = 40.4287;
  public static final double LON_1 = -3.6984;
  public static final double LAT_2 = 40.4138;
  public static final double LON_2 = -3.6986;

  public static final GeoLocation LOCATION_1 = new GeoLocation(LAT_1, LON_1);
  public static final GeoLocation LOCATION_2 = new GeoLocation(LAT_2, LON_2);

  // coordinates out of Madrid
  public static final double LAT_3 = 42.5821;
  public static final double LON_3 = -5.5604;

  public static final GeoLocation LOCATION_3 = new GeoLocation(LAT_3, LON_3);

}
