package utils.fileWriter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
	private File file;
	private PrintWriter writer;

	public FileWriter(String path) {
		try {
			file = new File(path);
			writer = new PrintWriter(file, "UTF-8");
		} catch (IOException e) {
			// it's ok
		}
	}

	public void writeText(String text) {
		writer.println(text);
	}

	public void writeEnter() {
		writeText("");
	}

	public void close() {
		writer.close();
	}

	public static boolean isEmpty(String line) {
		if ((line == null) || line.isEmpty() || line.equals("\n")) {
			return true;
		}
		return false;
	}

}