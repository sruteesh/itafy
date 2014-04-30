package models.categories;

/**
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public enum AvaibleCategories {
	POLITICA, DEPORTES, CULTURA;

	public static String[] names() {
		AvaibleCategories[] categories = AvaibleCategories.values();
		String[] names = new String[categories.length];
		for (int i=0; i < categories.length; i++) {
			names[i] = categories[i].name();
		}
		return names;
	}

}
