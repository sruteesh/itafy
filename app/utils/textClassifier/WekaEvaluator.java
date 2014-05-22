package utils.textClassifier;

import java.util.HashMap;
import models.categories.AvaibleCategories;


public class WekaEvaluator {
	private double NEWS_LIMIT;
	private double SPORT_LIMIT;
	private HashMap<String, Double> evaluableData;

	public WekaEvaluator() {
		evaluableData = new HashMap<String,Double>();
		NEWS_LIMIT = 0.9710401891252954;
		SPORT_LIMIT = 0.25;
	}

	public void setSportLimit(double v) { SPORT_LIMIT = v; }
	public void setNewsLimit(double v) {NEWS_LIMIT = v; }

	public HashMap<String,Double> getEvaluableData() {
		return evaluableData;
	}

	public String evaluate(HashMap<String, Double> distribution) {
		evaluableData = distribution;
		System.out.println(distribution);
		double news = getNewsValue(distribution);
		double sports = getSportsValue(distribution);
		if (sports >= SPORT_LIMIT) {
			return AvaibleCategories.DEPORTES.name();
		} else if( news >= NEWS_LIMIT) {
			return AvaibleCategories.ACTUALIDAD.name();
		} else {
			return "NINGUNO";
		}
	}

	private double getNewsValue(HashMap<String, Double> d) {
		Double value = new Double(0.0);
		value = d.get(AvaibleCategories.ACTUALIDAD.name());
		return value.doubleValue();
	}

	private double getSportsValue(HashMap<String,Double> d) {
		Double value = new Double(0.0);
		value = d.get(AvaibleCategories.DEPORTES.name());
		return value.doubleValue();
	}
}
