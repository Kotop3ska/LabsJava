package ru.rsreu.morozov0212.stringprocessor;

public class RegularExpressionMatchValidator {

	private RegularExpressionMatchValidator() {
	}

	public static boolean isMatch(String inputString, String regularExpression) {
		return inputString.matches(regularExpression);
	}
}
