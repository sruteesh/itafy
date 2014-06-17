package utils.gender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import utils.helpers.NormalizeHelper;
import benchmarks.DescriptionsUtils;
import benchmarks.NamesUtils;

public class GenderDetector {

	public static String detectUser(String name, String description) {
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

		if (!male && !female && (description != null)) {
			if (descriptionCandidates != null) {
				for (String candidate : descriptionCandidates) {
					male |= DescriptionsUtils.manualMaleDescriptionsContains(candidate);
					female |= DescriptionsUtils.manualFemaleDescriptionsContains(candidate);
				}
			}
		}

		if (male && !female) {
			return "male";
		} else if (female && !male) {
			return "female";
		} else {
			return "unknown";
		}
	}

	private static String normalize(String name) {
		name = name.toLowerCase();
		name = NormalizeHelper.normalizeVowels(name);
		name = NormalizeHelper.normalizeAsciiChars(name);

		// comment this line to measure the improvement of remove non alphabetic
		// chars
		name = NormalizeHelper.removeNonAlphabeticChars(name);

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
}
