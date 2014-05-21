package utils.fileUpdater;

import java.io.File;

public class FileUpdaterExample {
	/* FIXME: relative path */
	static final String FOLDER_PATH = "/Users/manutero/workspace/itafy/weka-data/";

	public static void main(String[] args) {
		final File folder = new File(FOLDER_PATH);
		FileUpdater updater = new FileUpdater();
		updater.updateFiles(folder);
		System.out.println("FIN");
	}

}
