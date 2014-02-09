package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Category model & enum definition: AvaibleCategories
 * 
 * @author martero@ucm.es & raul.marcos@ucm.es
 */
public class Category {

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
      if (category.equals("politica")) {
        return POLITICA;
      } else if (category.equals("economia")) {
        return ECONOMIA;
      } else if (category.equals("deportes")) {
        return DEPORTES;
      } else if (category.equals("cultura")) {
        return CULTURA;
      } else  {
        return null;
      }
    }
  }

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
