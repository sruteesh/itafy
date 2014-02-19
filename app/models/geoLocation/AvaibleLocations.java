package models.geoLocation;

public enum AvaibleLocations {
	MADRID, BARCELONA;

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
			if (location.equals("madrid") || location.equals("Madrid") || location.equals("MADRID")) {
				return MADRID;
			}
			if (location.equals("barcelona") || location.equals("Barcelona") || location.equals("BARCELONA")) {
				return BARCELONA;
			}
		}
		return null;
	}

}