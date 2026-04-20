package ru.rsreu.morozov0212.stringprocessor;

public class LengthBasedStringSplitter {

	private LengthBasedStringSplitter() {
	}

	public static String splitStringWithLengthLimit(String inputString, String splitSign, String endSign,
			int substringLength) {
		String[] separatedWords = inputString.split(String.valueOf(splitSign));
		String[] separatedLines = LengthBasedStringSplitter.collectIntoSubstringsWithLengthLimit(separatedWords,
				splitSign, substringLength);
		return StringsProcessingUtils.joinSubstringsToStringWithSeparator(separatedLines, endSign);
	}

	private static String[] collectIntoSubstringsWithLengthLimit(String[] separatedWords, String splitSign,
			int substringLength) {
		if (separatedWords.length == StringsProcessingUtils.getMinNumberOfWords()) {
			return separatedWords;
		}

		String[] separatedLines = new String[separatedWords.length];

		int lineIndex = 0;
		int currentLength = 0;

		for (int i = 0; i < separatedWords.length; i++) {
			if (LengthBasedStringSplitter.isCurrentLengthZero(currentLength)) {
				separatedLines[lineIndex] = separatedWords[i];
				currentLength = separatedWords[i].length();
			} else if (LengthBasedStringSplitter.isEnoughSpaceForNewSubstring(currentLength, splitSign.length(),
					separatedWords[i].length(), substringLength)) {

				separatedLines[lineIndex] = separatedLines[lineIndex] + splitSign + separatedWords[i];
				currentLength += splitSign.length() + separatedWords[i].length();

			} else {
				lineIndex++;
				separatedLines[lineIndex] = separatedWords[i];
				currentLength = separatedWords[i].length();
			}
		}
		return separatedLines;
	}

	private static boolean isCurrentLengthZero(int currentLength) {
		return currentLength == 0;
	}

	private static boolean isEnoughSpaceForNewSubstring(int currentLength, int splitSignLength, int wordLength,
			int substringLength) {
		return currentLength + splitSignLength + wordLength <= substringLength;
	}
}
