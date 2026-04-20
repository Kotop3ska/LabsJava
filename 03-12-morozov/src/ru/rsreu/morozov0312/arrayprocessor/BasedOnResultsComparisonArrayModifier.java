package ru.rsreu.morozov0312.arrayprocessor;

public class BasedOnResultsComparisonArrayModifier {
	private static final int SUM_IS_EQUAL_TO_PRODUCT = 0;
	private IntegerArray intArray;

	public BasedOnResultsComparisonArrayModifier(IntegerArray intArray) {
		this.intArray = intArray;
	}

	public int modifyArrayBasedComparisonResults() {
		int comparisonResult = BasedOnResultsComparisonArrayModifier.compareSumAndProduct(this.intArray);
		if (comparisonResult != BasedOnResultsComparisonArrayModifier.SUM_IS_EQUAL_TO_PRODUCT) {
			this.zeroOutElementsDependingOnTheirSign(comparisonResult);
		}
		return comparisonResult;
	}

	private void zeroOutElementsDependingOnTheirSign(int comparisonResult) {
		for (int i = 0; i < this.intArray.getArrayLength(); i++) {
			int value = this.intArray.getValueByIndex(i);

			if (BasedOnResultsComparisonArrayModifier.isSumGreaterAndValuePositive(comparisonResult, value)
					|| BasedOnResultsComparisonArrayModifier.isProductGreaterAndValueNegative(comparisonResult,
							value)) {
				this.intArray.setValueByIndex(i, 0);
			}
		}
	}

	private static int compareSumAndProduct(IntegerArray intArray) {
		int sumOfEvenElements = BasedOnResultsComparisonArrayModifier.getSumOfEvenElements(intArray);
		int productOfOddElements = BasedOnResultsComparisonArrayModifier.getProductOfOddElements(intArray);
		return sumOfEvenElements - productOfOddElements;
	}

	private static int getSumOfEvenElements(IntegerArray intArray) {
		int sumOfEvenElements = 0;
		for (int i = 0; i < intArray.getArrayLength(); i++) {
			int value = intArray.getValueByIndex(i);
			if (BasedOnResultsComparisonArrayModifier.isValueEven(value)) {
				sumOfEvenElements += value;
			}
		}
		return sumOfEvenElements;
	}

	private static int getProductOfOddElements(IntegerArray intArray) {
		if (!BasedOnResultsComparisonArrayModifier.isArrayWithOddNumbers(intArray)) {
			int productOfOddElements = 0;
			return productOfOddElements;
		}
		int productOfOddElements = 1;
		for (int i = 0; i < intArray.getArrayLength(); i++) {
			int value = intArray.getValueByIndex(i);
			if (!BasedOnResultsComparisonArrayModifier.isValueEven(value)) {
				productOfOddElements *= value;
			}
		}
		return productOfOddElements;
	}

	private static boolean isValueEven(int value) {
		return value % 2 == 0;
	}

	private static boolean isSumGreaterAndValuePositive(long comparisonResult, int value) {
		return comparisonResult > 0 && value > 0;
	}

	private static boolean isProductGreaterAndValueNegative(long comparisonResult, int value) {
		return comparisonResult < 0 && value < 0;
	}

	private static boolean isArrayWithOddNumbers(IntegerArray intArray) {
		boolean hasOdd = false;
		for (int i = 0; i < intArray.getArrayLength(); i++) {
			int value = intArray.getValueByIndex(i);
			if (!BasedOnResultsComparisonArrayModifier.isValueEven(value)) {
				return !hasOdd;
			}
		}
		return hasOdd;
	}
}
