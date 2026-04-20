package ru.rsreu.morozov0212.stringprocessor;

public class FirstLetterDuplicateFilter {

	private FirstLetterDuplicateFilter() {
	}

	public static String filterWordsWithDuplicateFirstLetter(String inputString, String splitSign) {
		String[] separatedWords = inputString.split(splitSign);
		String[] wordsWithoutDuplicateFirstLetter = FirstLetterDuplicateFilter
				.removeWordsWithDublicateFirstLetter(separatedWords);
		return StringsProcessingUtils.joinSubstringsToStringWithSeparator(wordsWithoutDuplicateFirstLetter, splitSign);
	}

	private static String[] removeWordsWithDublicateFirstLetter(String[] separatedWords) {
		String[] resultWords = new String[separatedWords.length];

		int resultWordsIndex = 0;
		for (int i = 0; i < separatedWords.length; i++) {
			if (!StringsProcessingUtils.isSubstringEmpty(separatedWords[i])
					&& !FirstLetterDuplicateFilter.isFirstLetterRepeated(separatedWords[i])) {
				resultWords[resultWordsIndex++] = separatedWords[i];
			}
		}

		return resultWords;
	}

	private static boolean isFirstLetterRepeated(String word) {
		if (word.length() <= 1) {
			return false;
		}

		char firstLetter = word.charAt(0);
		for (int i = 1; i < word.length(); i++) {
			if (word.charAt(i) == firstLetter) {
				return true;
			}
		}
		return false;
	}

}
