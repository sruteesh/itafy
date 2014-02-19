package models.categories;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Category model
 * 
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 * @see AvaibleCategories
 */
public class Category {

	private String name;

	@JsonCreator
	private Category(String name) {
		this.name = name;
	}

	/**
	 * Factory
	 * 
	 * @param name must be checked if is trusted category
	 * @return (Category) new Category instance or null.
	 */
	public static Category createCategory(String name) {
		AvaibleCategories trustedName = AvaibleCategories.asCategory(name);
		if (trustedName != null) {
			return createCategory(trustedName);
		} else  {
			return null;
		}
	}

	/**
	 * Factory.
	 * 
	 * @param trustedName
	 * @return (Category) new Category instance or null.
	 */
	public static Category createCategory(AvaibleCategories trustedName) {
		if (trustedName == AvaibleCategories.POLITICA) {
			return new Category("politica");
		} else if (trustedName == AvaibleCategories.ECONOMIA) {
			return new Category("economia");
		} else if (trustedName == AvaibleCategories.DEPORTES) {
			return new Category("deportes");
		} else if (trustedName == AvaibleCategories.CULTURA) {
			return new Category("cultura");
		} else {
			return null;
		}
	}

	@JsonProperty("name")
	public String getName() { return name; }

}

