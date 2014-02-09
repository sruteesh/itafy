package controllers.api;

import java.util.ArrayList;

import models.Category.AvaibleCategories;
import models.Location.AvaibleLocations;
import play.mvc.Controller;

/**
 * Common logic for controllers
 * 
 * @author martero@ucm.es & raul.marcos@ucm.es
 */
public abstract class ApiController extends Controller {

  protected static boolean isKnownLocation(AvaibleLocations location) {
    return location != null;
  }

  protected static ArrayList<String> avaibleLocations() {
    ArrayList<String> avaibleLocations = new ArrayList<String>();
    avaibleLocations.add("madrid");
    return avaibleLocations;
  }

  protected static boolean isKnownCategory(AvaibleCategories category) {
    return category != null;
  }

  protected static ArrayList<String> avaibleCategories() {
    ArrayList<String> avaibleCategories = new ArrayList<String>();
    avaibleCategories.add("politica");
    avaibleCategories.add("economia");
    avaibleCategories.add("deportes");
    avaibleCategories.add("cultura");
    return avaibleCategories;
  }

}
