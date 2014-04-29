package utils.helpers;

import java.io.FileReader;
import java.io.IOException;

/**
 * Auxiliar class which defines possible useful common methods for manpulating files
 * <p>
 * Note: all methods would be static; not reason to instanciate or extend this class
 * 
 * @see Stackoverflow #1844355
 * @author martero@ucm.es
 * @author raulmarcosl@gmail.com
 */
public final class FileHelper {

	/**
	 * Suppress default constructor for noninstantiability; "Effective Java" Item 4.
	 * @throws AssertionError
	 */
	private FileHelper() {
		throw new AssertionError();
	}


	/**
	 * Read a .txt file and return it's content as a String
	 * 
	 * @param filePath path to a non binary file
	 * @return text of the <code>.txt</code> file
	 */
	public static String readTextFile(String filePath) {
		try {
			FileReader m = new FileReader(filePath);
			StringBuffer message = new StringBuffer();
			int l;
			while ((l = m.read()) != -1) {
				message.append((char) l);
			}
			m.close();
			return message.toString();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Utils.readTextFile("+filePath+")");
			return null;
		}
	}

}
