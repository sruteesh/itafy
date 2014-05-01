package utils.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Auxiliar class which defines possible useful common methods for manpulating date
 * <p>
 * Note: all methods would be static; not reason to instanciate or extend this class
 * 
 * @see Stackoverflow #1844355
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public final class DateHelper {

	/**
	 * Suppress default constructor for noninstantiability; "Effective Java" Item 4.
	 * 
	 * @throws AssertionError
	 */
	private DateHelper() {
		throw new AssertionError();
	}


	/**
	 * With format dd_MM_yyy; example <code>2014_04_29</code>
	 * @return today's date as String
	 */
	public static String today() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		return sdf.format(date);
	}

}
