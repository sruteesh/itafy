package utils.fileWriter;

import models.categories.AvaibleCategories;


public class FileWriterExample {

	public static void main(String[] args) {
		// FIXME: absolute path
		final String path = "/Users/manutero/workspace/itafy/app/utils/fileWriter/ArffExample.arff";

		final String metaData1 = "El País (28 abril 2014)";
		final String metaData2 = "https://...";
		final String data1 = "Este es el artículo 1";
		final String data2 = "Este es el artículo 2";

		ArffWriter writer = new ArffWriter(path, metaData1, metaData2);
		writer.writeData(data1, AvaibleCategories.ACTUALIDAD);
		writer.writeData(data2, AvaibleCategories.DEPORTES);
		writer.close();
	}

}
