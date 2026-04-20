package ru.rsreu.morozov0212.stringprocessor;

public class StringsProcessingUtils {

	private static final int MIN_NUMBER_OF_WORDS = 1;

	private StringsProcessingUtils() {
	}

	public static String joinSubstringsToStringWithSeparator(String[] substringsArray, String separator) {
		String resultString = substringsArray[0];

		if (StringsProcessingUtils.isSubstringEmpty(resultString)) {
			resultString = "";
			return resultString;
		}

		if (substringsArray.length == StringsProcessingUtils.MIN_NUMBER_OF_WORDS) {
			return resultString;
		}

		int substringIndex = 1;
		while (substringIndex < substringsArray.length
				&& !StringsProcessingUtils.isSubstringEmpty(substringsArray[substringIndex])) {
			resultString += separator + substringsArray[substringIndex];
			substringIndex++;
		}
		return resultString;
	}

	public static int getMinNumberOfWords() {
		return StringsProcessingUtils.MIN_NUMBER_OF_WORDS;
	}

	static boolean isSplitSign(char character, String splitSign) {
		return String.valueOf(character) == splitSign;
	}

	static boolean isSubstringEmpty(String word) {
		return word == null;
	}
}
