package utils.fileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class FileWriter {
	protected final String path;

	public FileWriter(String path) {
		this.path = path;
		try {
			Files.deleteIfExists(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean writeText(String text) {
		final String content = text + "\n";
		try {
			Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean writeEnter() {
		return writeText("");
	}

	public static boolean isEmpty(String line) {
		if ((line == null) || line.isEmpty() || line.equals("\n")) {
			return true;
		}
		return false;
	}
}
