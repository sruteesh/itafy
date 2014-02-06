package utils;

import java.util.ArrayList;

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
   * 
   * @param collection
   * @return
   */
  public static <T> ArrayList<Object> asArrayList(Iterable<T> collection) {
    ArrayList<Object> tagList = new ArrayList<Object>();
    for (T tag : collection) {
      tagList.add(tag);
    }
    return tagList;
  }
}
