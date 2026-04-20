package ru.rsreu.morozov0412.arrayprocessor;

import java.util.Arrays;

/**
 * Utility for computing the symmetric difference of two integer arrays.
 * 
 * @author Morozov
 */
public class SymmetricDifferenceBetweenTwoArraysFinder {

	/**
	 * Prevents instantiation of this class.
	 */
	private SymmetricDifferenceBetweenTwoArraysFinder() {
	}

	/**
	 * Returns the symmetric difference of two arrays.
	 *
	 * The resulting array contains elements that appear in only one of the
	 * specified arrays. Duplicated elements are removed.
	 *
	 * @param firstArray  the first array
	 * @param secondArray the second array
	 * @return array containing elements that appear in exactly one array
	 */
	public static int[] findSymmetricDifference(int[] firstArray, int[] secondArray) {
		int[] differenceFromFirstArray = SymmetricDifferenceBetweenTwoArraysFinder.findDifference(firstArray,
				secondArray);
		int[] differenceFromSecondArray = SymmetricDifferenceBetweenTwoArraysFinder.findDifference(secondArray,
				firstArray);
		int[] symmetricDifferenceArray = SymmetricDifferenceBetweenTwoArraysFinder
				.unionTwoArrays(differenceFromFirstArray, differenceFromSecondArray);

		return SymmetricDifferenceBetweenTwoArraysFinder.removeDuplicatedElements(symmetricDifferenceArray);
	}

	/**
	 * Returns elements of the first array that are not contained in the second
	 * array.
	 *
	 * @param firstArray  the array whose elements are checked
	 * @param secondArray the array used for comparison
	 * @return array containing elements unique to the first array
	 */
	private static int[] findDifference(int[] firstArray, int[] secondArray) {
		int[] findingDifferenceResult = new int[firstArray.length];
		int resultArrayIndex = 0;
		for (int firstElement : firstArray) {
			boolean containedInSecondArray = SymmetricDifferenceBetweenTwoArraysFinder.findElementInArray(firstElement,
					secondArray);

			if (!containedInSecondArray) {
				findingDifferenceResult[resultArrayIndex++] = firstElement;
			}
		}
		return Arrays.copyOf(findingDifferenceResult, resultArrayIndex);
	}

	/**
	 * Combines two arrays into one array.
	 *
	 * @param firstArray  the first array
	 * @param secondArray the second array
	 * @return array containing elements of both arrays
	 */
	private static int[] unionTwoArrays(int[] firstArray, int[] secondArray) {
		int[] resultArray = new int[firstArray.length + secondArray.length];

		System.arraycopy(firstArray, 0, resultArray, 0, firstArray.length);
		System.arraycopy(secondArray, 0, resultArray, firstArray.length, secondArray.length);

		return resultArray;
	}

	/**
	 * Removes duplicated elements from the specified array.
	 *
	 * @param arrayWithDuplicatedElements array that may contain duplicated values
	 * @return array containing only unique elements
	 */
	private static int[] removeDuplicatedElements(int[] arrayWithDuplicatedElements) {
		int[] arrayWithoutDuplicatedElements = new int[arrayWithDuplicatedElements.length];
		int uniqueCount = 0;

		for (int element : arrayWithDuplicatedElements) {
			boolean alreadyExists = SymmetricDifferenceBetweenTwoArraysFinder.findElementInArray(element,
					Arrays.copyOf(arrayWithoutDuplicatedElements, uniqueCount));

			if (!alreadyExists) {
				arrayWithoutDuplicatedElements[uniqueCount++] = element;
			}
		}

		return Arrays.copyOf(arrayWithoutDuplicatedElements, uniqueCount);
	}

	/**
	 * Tests whether the specified element is contained in the array.
	 *
	 * @param element the element to search for
	 * @param array   the array in which the element is searched
	 * @return <code>true</code> if the array contains the specified element;
	 *         <code>false</code> otherwise
	 */
	private static boolean findElementInArray(int element, int[] array) {

		boolean containedInArray = false;

		for (int currentElement : array) {
			if (element == currentElement) {
				containedInArray = true;
				break;
			}
		}
		return containedInArray;
	}

}
