package utils;

import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Auxiliar class which defines possible useful common methods.
 * 
 * Note: all methods would be static; not reason to instanciate or extend this class.
 * 
 * @see Stackoverflow #1844355
 * 
 * @author martero@ucm.es & raul.marcos@ucm.es
 *
 */
public final class Helper
{

  /**
   * Suppress default constructor for noninstantiability; "Effective Java" Item 4.
   * 
   * @throws AssertionError
   */
  private Helper() {
    throw new AssertionError();
  }

  /**
   * Converts collection of Mongo's results into ArrayList.
   * 
   * Note: parameter type T is meant to be one of the possible models.
   * 
   * @param collection result of a "find()" query into the DB.
   * @return (ArrayList) intances as generic "Object" objects
   */
  public static <T> ArrayList<Object> asArrayList(Iterable<T> collection) {
    ArrayList<Object> list = new ArrayList<Object>();
    for (T object : collection) {
      list.add(object);
    }
    return list;
  }

  /** useful for JSON manipulation */
  private static final ObjectMapper mapper = new ObjectMapper();

  /**
   * Converts the parameter object to JSON.
   * 
   * @param object expected instance of any model
   * @return (JsonNode)
   */
  public static JsonNode asJson(Object object) {
    return mapper.valueToTree(object);
  }

  /**
   * Returns an empty JSON "{}"
   * 
   * @return (JsonNode) empty JSON
   */
  public static JsonNode emptyJson() {
    ArrayList<Object> emptyArray = new ArrayList<Object>();
    return mapper.valueToTree(emptyArray);
  }

}