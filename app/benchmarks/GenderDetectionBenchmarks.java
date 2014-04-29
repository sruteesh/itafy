package benchmarks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import models.data.MongoClientData;
import models.entities.TwitterName;
import org.jongo.MongoCollection;
import utils.helpers.StringHelper;
import controllers.db.DbNames;

public class GenderDetectionBenchmarks extends MongoClientData {

	protected static final MongoCollection twitterNamesCollection = jongoItafyBenchmarks
			.getCollection(DbNames.TWITTER_NAMES);

	private static int malesA = 0;
	private static int femalesA = 0;
	private static int bothA = 0;

	private static int malesB = 0;
	private static int femalesB = 0;
	private static int bothB = 0;

	private static int malesC = 0;
	private static int femalesC = 0;
	private static int bothC = 0;

	private static Map<String, Integer> nameCandidatesPending = new HashMap<String, Integer>();
	private static Map<String, Integer> descriptionCandidatesPending = new HashMap<String, Integer>();

	public static void main(String[] args) {
		// startBenchmarks();

		startEvaluation();

		// int i = 0;
		// for (String name : sortByValues(nameCandidatesPending).keySet()) {
		// System.out.println(name + "\t" + nameCandidatesPending.get(name));
		// if (i++ > 200) {
		// return;
		// }
		// }

		// int j = 0;
		// for (String name :
		// sortByValues(descriptionCandidatesPending).keySet()) {
		// if (j > 1000 && j <= 3000) {
		// System.out.println(name);
		// } else if (j > 3000) {
		// return;
		// }
		//
		// j++;
		// }
	}

	private static void startBenchmarks() {
		Iterable<TwitterName> twitterNames = twitterNamesCollection
				// .find()
				.find("{location: {$within: {$polygon: #}}}", getSpainPolygon())
				.limit(20000)
				.as(TwitterName.class);

		int count = 0;
		for (TwitterName twitterName : twitterNames) {
			String name = twitterName.getName();
			String description = twitterName.getDescription();
			boolean verbose = false;
			algorithmA(name, verbose);
			algorithmB(name, verbose);
			algorithmC(name, description, verbose);
			count++;
		}

		System.out.println("A MALES: " + malesA + "\t" + (malesA * 100) / count + "%");
		System.out.println("A FEMALES: " + femalesA + "\t" + (femalesA * 100) / count + "%");
		System.out.println("A BOTH: " + bothA + "\t" + (bothA * 100) / count + "%");
		System.out.println("B MALES: " + malesB + "\t" + (malesB * 100) / count + "%");
		System.out.println("B FEMALES: " + femalesB + "\t" + (femalesB * 100) / count + "%");
		System.out.println("B BOTH: " + bothB + "\t" + (bothB * 100) / count + "%");
		System.out.println("C MALES: " + malesC + "\t" + (malesC * 100) / count + "%");
		System.out.println("C FEMALES: " + femalesC + "\t" + (femalesC * 100) / count + "%");
		System.out.println("C BOTH: " + bothC + "\t" + (bothC * 100) / count + "%");
	}

	private static void startEvaluation() {

		// there is a total of 217 Twitter names' manually marked
		int limit = 200;

		Iterable<TwitterName> twitterNames = twitterNamesCollection
				.find("{genre: {$exists: true}, location: {$within: {$polygon: #}}}",
						getSpainPolygon())
				// .find("{genre: {$exists: true}}")
				.limit(limit)
				.as(TwitterName.class);

		int responseA;
		int detected = 0, undetected = 0, falsePositives = 0;
		for (TwitterName twitterName : twitterNames) {
			String name = twitterName.getName();
			String screenName = twitterName.getScreenName();
			String description = twitterName.getDescription();
			String genre = twitterName.getGenre();
			boolean verbose = false;
			responseA = algorithmC(name, description, verbose);
			if ((responseA == 1 && genre.equals("MALE")) || (responseA == 2 &&
					genre.equals("FEMALE"))) {
				detected++;
			} else if (responseA == 0) {
				undetected++;
			} else {
				System.out.println("name: " + name + "\t" + screenName);
				System.out.println("description: " + description);
				System.out.println(genre + " " + responseA);
				falsePositives++;
			}
		}

		System.out.println("DETECTED: " + detected + "\t" + (detected * 100) / limit + "%");
		System.out.println("UNDETECTED: " + undetected + "\t" + (undetected * 100) / limit + "%");
		System.out.println("FALSE POSITIVES: " + falsePositives + "\t" + (falsePositives * 100) / limit + "%");
	}

	private static List<Double[]> getSpainPolygon() {
		// polygon obtained using http://www.birdtheme.org/useful/v3tool.html
		List<Double[]> spainPolygon = new ArrayList<Double[]>();
		spainPolygon.add(new Double[] { -2.241211, 43.644026 });
		spainPolygon.add(new Double[] { -9.580078, 44.182204 });
		spainPolygon.add(new Double[] { -10.371094, 36.985003 });
		spainPolygon.add(new Double[] { -7.382813, 36.315125 });
		spainPolygon.add(new Double[] { -18.588867, 28.729130 });
		spainPolygon.add(new Double[] { -18.720703, 26.706360 });
		spainPolygon.add(new Double[] { -14.589844, 27.176469 });
		spainPolygon.add(new Double[] { -12.788086, 28.613459 });
		spainPolygon.add(new Double[] { -7.075195, 35.924645 });
		spainPolygon.add(new Double[] { -3.603516, 35.817813 });
		spainPolygon.add(new Double[] { 0.263672, 37.544577 });
		spainPolygon.add(new Double[] { 0.747070, 38.134557 });
		spainPolygon.add(new Double[] { 4.350586, 38.959409 });
		spainPolygon.add(new Double[] { 4.746094, 40.245992 });
		spainPolygon.add(new Double[] { 3.647461, 40.713956 });
		spainPolygon.add(new Double[] { 3.779297, 42.714732 });
		spainPolygon.add(new Double[] { -2.241211, 43.644026 });

		return spainPolygon;
	}

	/**
	 * Basic algorithm
	 * 
	 * Sample: 5k. Males: 1056 (21%) Females: 858 (17%)
	 */
	private static int algorithmA(String name, boolean verbose) {
		name = normalize(name);

		boolean male = false;
		boolean female = false;
		ArrayList<String> candidates = getCandidates(name);
		Iterator<String> iterator = candidates.iterator();
		while (iterator.hasNext() && !male && !female) {
			String candidate = iterator.next();
			male |= NamesUtils.topMaleNamesContainsSingleName(candidate);
			female |= NamesUtils.topFemaleNamesContainsSingleName(candidate);

			for (String candidateAux : candidates) {
				male |= NamesUtils.topMaleNamesContainsCompoundName(candidate, candidateAux);
				female |= NamesUtils.topFemaleNamesContainsCompoundName(candidate, candidateAux);
			}
		}

		if (male && female) {
			bothA++;
		} else if (male) {
			malesA++;
		} else if (female) {
			femalesA++;
		}

		if (verbose) {
			if (male && female) {
				System.out.println("[A] **[BOTH]** -\t " + name);
			} else if (!male && !female) {
				System.out.println("[A] -\t " + name);
			}
		}

		if (male && !female) {
			return 1;
		} else if (female && !male) {
			return 2;
		} else {
			return 0;
		}
	}

	/**
	 * This method try if the normalized name contains a top name. It increases
	 * the detection ratio, but it seems to add a lot of false positives, due to
	 * male names shorter than their female version (adrian / adriana).
	 * 
	 * Sample: 5k. Males: 1941 (38%) Females: 916 (18%)
	 */
	private static int algorithmB(String name, boolean verbose) {
		name = normalize(name);

		boolean male = NamesUtils.nameContainsTopMaleName(name);
		boolean female = NamesUtils.nameContainsTopFemaleName(name);

		if (male && female) {
			bothB++;
		} else if (male) {
			malesB++;
		} else if (female) {
			femalesB++;
		}

		if (verbose) {
			if (male && female) {
				System.out.println("[B] **[BOTH]** -\t " + name);
			} else if (!male && !female) {
				System.out.println("[B] -\t " + name);
			}
		}

		if (male && !female) {
			return 1;
		} else if (female && !male) {
			return 2;
		} else {
			return 0;
		}
	}

	/**
	 * Similar to algorithm A, but it doesn't only use the list of top names: it
	 * also use a manual, curated list of names, and a list of genre
	 * identifiers.
	 * 
	 * Sample: 5k. Males: 1138 (22%) Females: 966 (19%)
	 */
	private static int algorithmC(String name, String description, boolean verbose) {
		boolean male = false;
		boolean female = false;

		name = normalize(name);
		ArrayList<String> nameCandidates = getCandidates(name);
		ArrayList<String> descriptionCandidates = null;
		if (description != null) {
			description = normalize(description);
			descriptionCandidates = getCandidates(description);
		}

		Iterator<String> iterator = nameCandidates.iterator();
		while (iterator.hasNext() && !male && !female) {
			String candidate = iterator.next();
			male |= NamesUtils.topMaleNamesContainsSingleName(candidate)
					|| NamesUtils.manualMaleNamesContainsSingleName(candidate)
					|| NamesUtils.maleIdentifiersContainsSingleName(candidate);

			female |= NamesUtils.topFemaleNamesContainsSingleName(candidate)
					|| NamesUtils.manualFemaleNamesContainsSingleName(candidate)
					|| NamesUtils.femaleIdentifiersContainsSingleName(candidate);

			for (String candidateAux : nameCandidates) {
				male |= NamesUtils.topMaleNamesContainsCompoundName(candidate, candidateAux);
				female |= NamesUtils.topFemaleNamesContainsCompoundName(candidate, candidateAux);
			}
		}

		if (!male && !female && description != null) {
			for (String candidate : descriptionCandidates) {
				male |= DescriptionsUtils.manualMaleDescriptionsContains(candidate);
				female |= DescriptionsUtils.manualFemaleDescriptionsContains(candidate);
			}
		}

		if (!male && !female) {
			addNameCandidatesForAnalysis(new HashSet<String>(nameCandidates));
			if (description != null) {
				addDescriptionCandidatesForAnalysis(new HashSet<String>(descriptionCandidates));
			}
		}

		if (male && female) {
			bothC++;
		} else if (male) {
			malesC++;
		} else if (female) {
			femalesC++;
		}

		if (verbose) {
			if (male && female) {
				System.out.println("[C] **[BOTH]** -\t " + name);
			} else if (!male && !female) {
				System.out.println("[C] -\t " + Arrays.toString(nameCandidates.toArray(new String[0])));
			}
		}

		if (male && !female) {
			return 1;
		} else if (female && !male) {
			return 2;
		} else {
			return 0;
		}
	}

	private static void addNameCandidatesForAnalysis(HashSet<String> candidates) {
		for (String candidate : candidates) {
			Integer candidateValue = nameCandidatesPending.get(candidate);
			if (candidateValue == null) {
				nameCandidatesPending.put(candidate, 1);
			} else {
				nameCandidatesPending.put(candidate, candidateValue + 1);
			}
		}
	}

	private static void addDescriptionCandidatesForAnalysis(HashSet<String> candidates) {
		for (String candidate : candidates) {
			Integer candidateValue = descriptionCandidatesPending.get(candidate);
			if (candidateValue == null) {
				descriptionCandidatesPending.put(candidate, 1);
			} else {
				descriptionCandidatesPending.put(candidate, candidateValue + 1);
			}
		}
	}

	private static String normalize(String name) {
		name = name.toLowerCase();
		name = StringHelper.normalizeVowels(name);
		name = StringHelper.normaliceAsciiChars(name);

		// comment this line to measure the improvement of remove non alphabetic
		// chars
		name = StringHelper.removeNonAlphabeticChars(name);

		return name;
	}

	private static ArrayList<String> getCandidates(String stringToAnalyze) {
		ArrayList<String> candidates = new ArrayList<String>(Arrays.asList(stringToAnalyze.split("\\s+")));
		ArrayList<String> result = new ArrayList<String>();

		for (String candidate : candidates) {
			if (candidate.length() > 1) {
				if (!result.contains(candidate)) {
					result.add(candidate);
				}
				String curatedCandidate = removeDuplicatedLetters(candidate);
				if (!result.contains(curatedCandidate)) {
					result.add(curatedCandidate);
				}
			}
		}

		return result;
	}

	private static String removeDuplicatedLetters(String name) {
		StringBuilder sb = new StringBuilder();
		Character last = 0;
		for (Character character : name.toCharArray()) {
			if (character != last) {
				sb.append(character);
			}
			last = character;
		}

		return sb.toString();
	}

	/*
	 * Java method to sort Map in Java by value e.g. HashMap or Hashtable throw
	 * NullPointerException if Map contains null values It also sort values even
	 * if they are duplicates
	 */
	public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {

			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		// LinkedHashMap will keep the keys in the order they are inserted
		// which is currently sorted on natural ordering
		Map<K, V> sortedMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entry : entries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
}
