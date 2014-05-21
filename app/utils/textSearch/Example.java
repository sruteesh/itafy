package utils.textSearch;

import java.util.ArrayList;
import java.util.HashMap;
import models.categories.AvaibleCategories;


public class Example {

	public static void main(String[] a) {
		createIndex();
		Object results = query("dinosaurio");
		System.out.println(results.toString());

	}


	// private

	private static void createIndex() {
		Index index = new Index();
		for (String key : fakeTexts.keySet()) {
			index.addText(key, fakeTexts.get(key));
		}
		index.closeWriter();
	}

	private static HashMap<String, ArrayList<Float>> query(String query) {
		HashMap<String, ArrayList<Float>> results = new HashMap<String, ArrayList<Float>>();
		Searcher searcher = new Searcher();
		results = searcher.search(query);
		return results;
	}


	private static final HashMap<String, AvaibleCategories> fakeTexts = new HashMap<String, AvaibleCategories>();
	static {
		fakeTexts.put("Hola hola caracola", AvaibleCategories.ACTUALIDAD);
		fakeTexts.put("Eres un dinosaurio; dijo el mayor dinosaurio de todos", AvaibleCategories.DEPORTES);
		fakeTexts.put("dinosaurio en el zoo", AvaibleCategories.DEPORTES);
		fakeTexts.put("esto nos son mas que pruebas", AvaibleCategories.DEPORTES);
		fakeTexts.put("de forma, un tanto aleatorias", AvaibleCategories.DEPORTES);
		fakeTexts.put("Alendi no debe llegar al pozo de la ascension", AvaibleCategories.DEPORTES);
		fakeTexts.put("pues no debe liberar lo que se oculta alli", AvaibleCategories.DEPORTES);
		fakeTexts.put("aqui meto otro dinosaurio", AvaibleCategories.DEPORTES);
		fakeTexts.put("y este dinosaurio es para despistar", AvaibleCategories.ACTUALIDAD);
		fakeTexts.put("hablemos de pokemon", AvaibleCategories.ACTUALIDAD);
		fakeTexts.put("en realidad esta escrito en katakana", AvaibleCategories.ACTUALIDAD);
		fakeTexts.put("y es una contraccion de pocket monsters", AvaibleCategories.ACTUALIDAD);
		fakeTexts.put("tambien hay pokemon de tipo dinosaurio", AvaibleCategories.DEPORTES);

	}



}
