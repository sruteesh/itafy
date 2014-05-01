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
		Category response = null;
		AvaibleCategories trustedName = AvaibleCategories.valueOf(name.toUpperCase());
		if (trustedName != null) {
			response = new Category(name);
		}
		return response;
	}

	public static Category createCategory(AvaibleCategories category) {
		return new Category(category.name().toLowerCase());
	}

	@JsonProperty("name")
	public String getName() { return name; }

}

