package utils.fileWriter;

import models.categories.AvaibleCategories;

/**
 * TODO: class description
 * 
 * @author m.artero@ucm.es
 * @author raul.marcos.l@gmail.com
 */
public class ArffWriter extends FileWriter {
	private static final char STARTING_COMMENT = '%';
	private static final char STARTING_DECLARATION = '@';
	private static final String FILE_HEADER =
			"@relation categories-model\n@attribute Message string\n@attribute Class {POLITICA,CULTURA,DEPORTES}\n";
	private static final String DATA_DECLARATION = "@data";

	/**
	 * <pre>
	 * -------------------------
	 * [at]relation categories-model
	 * [at]attribute Message string
	 * [at]attribute Class {POLITICA,ECONOMIA,CULTURA,DEPORTES}
	 * 
	 * [at]data
	 * [write pointer]
	 * -------------------------
	 * </pre>
	 * 
	 * @param path arff file
	 */
	public ArffWriter(String path) {
		super(path);
		writeText(FILE_HEADER);
		writeText(DATA_DECLARATION);
	}

	/**
	 * <pre>
	 * -------------------------
	 * % metaData
	 * % metaData
	 * % metaData
	 * 
	 * [at]relation categories-model
	 * [at]attribute Message string
	 * [at]attribute Class {POLITICA,ECONOMIA,CULTURA,DEPORTES}
	 * 
	 * [at]data
	 * [write pointer]
	 * -------------------------
	 * </pre>
	 * 
	 * @param path arff file
	 * @param metaData wrote as comments at the header of the file (each String in a new line)
	 */
	public ArffWriter(String path, String... metaData) {
		super(path);
		for (String line : metaData) {
			writeComment(line);
		}
		writeText("");
		writeText(FILE_HEADER);
		writeText(DATA_DECLARATION);
	}

	/**
	 * @return false if IOException raised while execution
	 */
	public void writeComment(String comment) {
		String content = "" + STARTING_COMMENT + ' ' + comment;
		writeText(content);
	}

	/**
	 * @return false if IOException raised while execution
	 */
	public void writeData(String text, String classification) {
		String content = "\"" + text + "\"" + "," + classification.toUpperCase();
		writeText(content);
	}

	/**
	 * @return false if IOException raised while execution
	 */
	public void writeData(String text, AvaibleCategories category) {
		String content = "\"" + text + "\"" + "," + category.name();
		writeText(content);
	}

	/**
	 * @return true is line is a arff comment
	 */
	public static boolean isComment(String line) {
		if (isEmpty(line)) {
			return false;
		}
		return (line.charAt(0) == STARTING_COMMENT);
	}

	/**
	 * 
	 * @return true if line is a arff declaration
	 */
	public static boolean isDeclaration(String line) {
		if (isEmpty(line)) {
			return false;
		}
		return (line.charAt(0) == STARTING_DECLARATION);
	}

}
