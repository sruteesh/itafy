package benchmarks;

import java.text.Normalizer;

import models.data.MongoClientData;
import models.entities.TwitterName;

import org.jongo.MongoCollection;

import controllers.db.NameDBs;

public class GenderDetectionBenchmarks extends MongoClientData {

	protected static final MongoCollection twitterNamesCollection = jongoItafyBenchmarks
			.getCollection(NameDBs.TWITTER_NAMES);

	private static int malesA = 0;
	private static int femalesA = 0;

	private static int malesB = 0;
	private static int femalesB = 0;

	private static int malesC = 0;
	private static int femalesC = 0;

	public static void main(String[] args) {
		startBenchmarks();
	}

	private static void startBenchmarks() {
		Iterable<TwitterName> twitterNames = twitterNamesCollection
				.find()
				.limit(5000)
				.as(TwitterName.class);

		int count = 0;
		for (TwitterName twitterName : twitterNames) {
			String name = twitterName.getName();

			algorithmA(name);
			algorithmB(name);
			algorithmC(name);
			count++;
		}

		System.out.println();
		System.out.println("A MALES: " + malesA + "\t" + (malesA * 100) / count + "%");
		System.out.println("A FEMALES: " + femalesA + "\t" + (femalesA * 100) / count + "%");
		System.out.println("B MALES: " + malesB + "\t" + (malesB * 100) / count + "%");
		System.out.println("B FEMALES: " + femalesB + "\t" + (femalesB * 100) / count + "%");
		System.out.println("C MALES: " + malesC + "\t" + (malesC * 100) / count + "%");
		System.out.println("C FEMALES: " + femalesC + "\t" + (femalesC * 100) / count + "%");
	}

	/**
	 * Basic algorithm
	 * 
	 * Sample: 5k. Males: 1056 (21%) Females: 858 (17%)
	 */

	private static void algorithmA(String name) {

		name = normalize(name);

		String[] candidates = getCandidates(name);

		// comment this line to measure the improvement of remove non alphabetic
		// chars
		name = removeNonAlphabeticChars(name);

		boolean male = false;
		boolean female = false;
		for (String candidate : candidates) {
			male |= SpainNames.topMaleNamesContainsSingleName(candidate);
			female |= SpainNames.topFemaleNamesContainsSingleName(candidate);

			for (String candidateAux : candidates) {
				male |= SpainNames.topMaleNamesContainsCompoundName(candidate, candidateAux);
				female |= SpainNames.topFemaleNamesContainsCompoundName(candidate, candidateAux);
			}
		}

		if (male) {
			malesA++;
			System.out.println("[A] MALE -\t " + name);
		} else if (female) {
			femalesA++;
			System.out.println("[A] FEMALE -\t " + name);

		} else if (male && female) {
			System.out.println("[A] **[BOTH]** -\t " + name);
		} else {
			System.out.println("[A] -\t " + name);
		}
	}

	/**
	 * This method try if the normalized name contains a top name. It increases
	 * the detection ratio, but it seems to add a lot of false positives, due to
	 * male names shorter than their female version (adrian / adriana).
	 * 
	 * Sample: 5k. Males: 1941 (38%) Females: 916 (18%)
	 */
	private static void algorithmB(String name) {

		name = normalize(name);

		boolean male = SpainNames.nameContainsTopMaleName(name);
		boolean female = SpainNames.nameContainsTopFemaleName(name);

		if (male) {
			malesB++;
			System.out.println("[B] MALE -\t " + name);
		} else if (female) {
			femalesB++;
			System.out.println("[B] FEMALE -\t " + name);

		} else if (male && female) {
			System.out.println("[B] **[BOTH]** -\t " + name);
		} else {
			System.out.println("[B] -\t " + name);
		}
	}

	/**
	 * Similar to algorithm A, but it doesn't only use the list of top names: it
	 * also use a manual, curated list of names, and a list of genre
	 * identifiers.
	 * 
	 * Sample: 5k. Males: 1138 (22%) Females: 966 (19%)
	 */
	private static void algorithmC(String name) {

		name = normalize(name);

		String[] candidates = getCandidates(name);

		boolean male = false;
		boolean female = false;
		for (String candidate : candidates) {

			male |= SpainNames.topMaleNamesContainsSingleName(candidate)
					|| SpainNames.manualMaleNamesContainsSingleName(candidate)
					|| SpainNames.maleIdentifiersContainsSingleName(candidate);

			female |= SpainNames.topFemaleNamesContainsSingleName(candidate)
					|| SpainNames.manualFemaleNamesContainsSingleName(candidate)
					|| SpainNames.femaleIdentifiersContainsSingleName(candidate);

			for (String candidateAux : candidates) {
				male |= SpainNames.topMaleNamesContainsCompoundName(candidate, candidateAux);
				female |= SpainNames.topFemaleNamesContainsCompoundName(candidate, candidateAux);
			}
		}

		if (male) {
			malesC++;
			System.out.println("[C] MALE -\t " + name);
		} else if (female) {
			femalesC++;
			System.out.println("[C] FEMALE -\t " + name);

		} else if (male && female) {
			System.out.println("[C] **[BOTH]** -\t " + name);
		} else {
			System.out.println("[C] -\t " + name);
		}
	}

	private static String normalize(String name) {
		name = name.toLowerCase();
		name = normalizeVowels(name);
		name = normaliceAsciiChars(name);

		// comment this line to measure the improvement of remove non alphabetic
		// chars
		name = removeNonAlphabeticChars(name);

		return name;
	}

	private static String normalizeVowels(String name) {
		return name
				.replace("á", "a")
				.replace("é", "e")
				.replace("í", "i")
				.replace("ó", "o")
				.replace("ú", "u")
				.replace("ә", "e")
				.replace("ε", "e")
				.replace("α", "a");
	}

	private static String normaliceAsciiChars(String name) {
		return Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	private static String removeNonAlphabeticChars(String name) {
		return name.replaceAll("[^a-zA-Z]", " ");
	}

	private static String[] getCandidates(String name) {
		return name.split("\\s+");
	}
}
