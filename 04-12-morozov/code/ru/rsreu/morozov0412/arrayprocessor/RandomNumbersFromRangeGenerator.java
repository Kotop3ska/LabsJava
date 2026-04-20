package ru.rsreu.morozov0412.arrayprocessor;

/**
 * The generator of numbers from given range
 * 
 * @author Morozov
 */
public class RandomNumbersFromRangeGenerator {

	/**
	 * Prevents instantiation of this class.
	 */
	private RandomNumbersFromRangeGenerator() {
	}

	/**
	 * Generate a randomly integer within the specified range.
	 *
	 * @param lowerLimitGeneratedNumbers the lower bound of the range
	 * @param upperLimitGeneratedNumbers the upper bound of the range
	 * @return randomly generated integer within the specified range
	 */
	public static int generateIntegerInGivenRange(int lowerLimitGeneratedNumbers, int upperLimitGeneratedNumbers) {
		return (int) (Math.random() * (upperLimitGeneratedNumbers - lowerLimitGeneratedNumbers + 1)
				+ lowerLimitGeneratedNumbers);
	}

}
