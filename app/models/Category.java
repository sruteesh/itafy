package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * enum definition: Category
 * 
 * TODO
 * 
 * @author martero@ucm.es & raul.marcos@ucm.es
 */
public class Category {

  public static final String POLITICA = "politica";
  public static final String ECONOMIA = "economia";
  public static final String DEPORTES = "deportes";
  public static final String CULTURA = "cultura";

  private String name;

  @JsonCreator
  public Category(String name) {
    this.name = name;
  }

  @JsonProperty("name")
  public String name() { return this.name; }

}
