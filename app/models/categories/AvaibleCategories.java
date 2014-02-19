package models.categories;

import java.util.ArrayList;


public enum AvaibleCategories {
	POLITICA, ECONOMIA, DEPORTES, CULTURA;

	/**
	 * Converts the input String into a known category defined on Category.AvaibleCategories.
	 * Will return null if unkown String given.
	 * 
	 * @param category String we want to convert
	 * @return (Category.AvaibleCategories) known category or null if unkown String given.
	 */
	public static AvaibleCategories asCategory(String category) {
		if (category != null) {
			if (category.equals("politica")) {
				return POLITICA;
			} else if (category.equals("economia")) {
				return ECONOMIA;
			} else if (category.equals("deportes")) {
				return DEPORTES;
			} else if (category.equals("cultura")) {
				return CULTURA;
			}
		}
		return null;
	}

	public static ArrayList<String> avaibleCategories() {
		ArrayList<String> avaibleCategories = new ArrayList<String>();
		avaibleCategories.add("politica");
		avaibleCategories.add("economia");
		avaibleCategories.add("deportes");
		avaibleCategories.add("cultura");
		return avaibleCategories;
	}

}
