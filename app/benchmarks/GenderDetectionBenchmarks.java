package benchmarks;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	private static Map<String, Integer> nameCandidatesPending = new HashMap<String, Integer>();
	private static Map<String, Integer> descriptionCandidatesPending = new HashMap<String, Integer>();

	public static void main(String[] args) {
		startBenchmarks();

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
				.find()
				.limit(50000)
				.as(TwitterName.class);

		int count = 0;
		for (TwitterName twitterName : twitterNames) {
			String name = twitterName.getName();
			String description = twitterName.getDescription();
			boolean verbose = false;
			algorithmA(name, verbose);
			// algorithmB(name, verbose);
			algorithmC(name, description, verbose);
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

	private static void algorithmA(String name, boolean verbose) {
		name = normalize(name);

		boolean male = false;
		boolean female = false;
		HashSet<String> candidates = getCandidates(name);
		for (String candidate : candidates) {
			male |= NamesUtils.topMaleNamesContainsSingleName(candidate);
			female |= NamesUtils.topFemaleNamesContainsSingleName(candidate);

			for (String candidateAux : candidates) {
				male |= NamesUtils.topMaleNamesContainsCompoundName(candidate, candidateAux);
				female |= NamesUtils.topFemaleNamesContainsCompoundName(candidate, candidateAux);
			}
		}

		if (male) {
			malesA++;
		} else if (female) {
			femalesA++;
		}

		if (verbose) {
			if (male && female) {
				System.out.println("[A] **[BOTH]** -\t " + name);
			} else if (!male || !female) {
				System.out.println("[A] -\t " + name);
			}
		}
	}

	/**
	 * This method try if the normalized name contains a top name. It increases
	 * the detection ratio, but it seems to add a lot of false positives, due to
	 * male names shorter than their female version (adrian / adriana).
	 * 
	 * Sample: 5k. Males: 1941 (38%) Females: 916 (18%)
	 */
	private static void algorithmB(String name, boolean verbose) {
		name = normalize(name);

		boolean male = NamesUtils.nameContainsTopMaleName(name);
		boolean female = NamesUtils.nameContainsTopFemaleName(name);

		if (male) {
			malesB++;
		} else if (female) {
			femalesB++;
		}

		if (verbose) {
			if (male && female) {
				System.out.println("[B] **[BOTH]** -\t " + name);
			} else if (!male || !female) {
				System.out.println("[B] -\t " + name);
			}
		}
	}

	/**
	 * Similar to algorithm A, but it doesn't only use the list of top names: it
	 * also use a manual, curated list of names, and a list of genre
	 * identifiers.
	 * 
	 * Sample: 5k. Males: 1138 (22%) Females: 966 (19%)
	 */
	private static void algorithmC(String name, String description, boolean verbose) {

		name = normalize(name);
		HashSet<String> nameCandidates = getCandidates(name);
		HashSet<String> descriptionCandidates = null;
		if (description != null) {
			description = normalize(description);
			descriptionCandidates = getCandidates(description);
		}

		boolean male = false;
		boolean female = false;
		for (String candidate : nameCandidates) {

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
			addNameCandidatesForAnalysis(nameCandidates);
			if (description != null) {
				addDescriptionCandidatesForAnalysis(descriptionCandidates);
			}
		}

		if (male) {
			malesC++;
		} else if (female) {
			femalesC++;
		}

		if (verbose) {
			if (male && female) {
				System.out.println("[C] **[BOTH]** -\t " + name);
			} else if (!male || !female) {
				System.out.println("[C] -\t " + name);
			}
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

	private static HashSet<String> getCandidates(String stringToAnalyze) {
		HashSet<String> candidates = new HashSet<String>(Arrays.asList(stringToAnalyze.split("\\s+")));
		HashSet<String> result = new HashSet<String>();

		for (String candidate : candidates) {
			if (candidate.length() > 1) {
				result.add(candidate);
			}
		}

		return result;
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
