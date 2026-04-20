package ru.rsreu.morozov0212;

import java.util.Scanner;
import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.morozov0212.stringprocessor.LengthBasedStringSplitter;
import ru.rsreu.morozov0212.stringprocessor.FirstLetterDuplicateFilter;
import ru.rsreu.morozov0212.stringprocessor.RegularExpressionMatchValidator;

public class ClientRunner {

	private static final String SPLIT_SIGN = " ";
	private static final String END_SIGN = "\n";
	private static final String REGULAR_EXPRESSION = "^[HCO0-9]+$";
	private static Resourcer resourcer = ProjectResourcer.getInstance();

	private ClientRunner() {
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.print(resourcer.getString("message.input.string.count"));
		int stringCount = Integer.parseInt(in.next());
		in.nextLine();

		String[] strings = new String[stringCount];
		for (int i = 0; i < strings.length; i++) {
			System.out.printf(resourcer.getString("message.input.string.number"), i + 1);
			strings[i] = in.nextLine();
		}

		System.out.print(resourcer.getString("message.input.substring.length"));
		int substringLength = Integer.parseUnsignedInt(in.next());

		in.close();

		String resultOutputString = "";

		resultOutputString += resourcer.getString("message.output.split.results");
		for (int i = 0; i < stringCount; i++) {
			String splitedString = LengthBasedStringSplitter.splitStringWithLengthLimit(strings[i],
					ClientRunner.SPLIT_SIGN, ClientRunner.END_SIGN, substringLength);

			resultOutputString += ClientRunner.buildResultOutputString(i, splitedString);
		}

		resultOutputString += ClientRunner.END_SIGN;
		resultOutputString += resourcer.getString("message.output.filter.results");
		for (int i = 0; i < stringCount; i++) {
			String stringWithoutWordsHasDublicatedFirstLetter = FirstLetterDuplicateFilter
					.filterWordsWithDuplicateFirstLetter(strings[i], ClientRunner.SPLIT_SIGN);
			resultOutputString += ClientRunner.buildResultOutputString(i, stringWithoutWordsHasDublicatedFirstLetter);
		}

		resultOutputString += ClientRunner.END_SIGN;
		resultOutputString += resourcer.getString("message.output.regex.results");
		for (int i = 0; i < stringCount; i++) {
			String matchingResult;

			if (RegularExpressionMatchValidator.isMatch(strings[i], ClientRunner.REGULAR_EXPRESSION)) {
				matchingResult = resourcer.getString("message.match.true");
			} else {
				matchingResult = resourcer.getString("message.match.false");
			}

			resultOutputString += ClientRunner.buildResultOutputString(i, matchingResult);
		}

		System.out.println(resultOutputString);
	}

	private static String buildResultOutputString(int stringIndex, String resultString) {
		return String.valueOf(stringIndex + 1) + resourcer.getString("message.output.string") + ClientRunner.END_SIGN
				+ resultString + ClientRunner.END_SIGN;
	}

}
