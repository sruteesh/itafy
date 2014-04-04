package models.geoLocation;

import java.util.HashMap;
import models.data.GeoTweetData;

/**
 * recount of geoTweets by location;
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public class GeoTweetsCounter {

	public GeoTweetsCounter() {}

	/**
	 * Returns recount of geoTweets by location;
	 * 
	 * <pre>
	 * {
	 *  "madrid" => 56,
	 *  "barcelona" => 43
	 * }
	 * </pre>
	 * 
	 * @return (HashMap) recount of geoTweets by location.
	 */
	public HashMap<String, Long> recountGeoTweets() {
		HashMap<String, Long> response = new HashMap<String, Long>();
		addMadridReountToMap(response);
		addBarcelonaToMap(response);
		return response;
	}

	protected void addMadridReountToMap(HashMap<String, Long> map) {
		String key = AvaibleLocations.asString(AvaibleLocations.MADRID);
		long value = GeoTweetData.countGeoTweets(AvaibleLocations.MADRID);
		map.put(key, value);
	}

	protected void addBarcelonaToMap(HashMap<String, Long> map) {
		String key = AvaibleLocations.asString(AvaibleLocations.BARCELONA);
		long value = GeoTweetData.countGeoTweets(AvaibleLocations.BARCELONA);
		map.put(key, value);
	}

}
