package ru.rsreu.morozov0412.arrayprocessor;

import com.prutzkow.twodimarray.Matrix;

/**
 * The Writer of the symmetric difference of two matrix columns to a third
 * column. All remaining cells of the target column are filled with zeros.
 * 
 * @author Morozov
 */
public class SymmetricDifferenceBetweenTwoColumnsToThirdColumnWriter extends Matrix {

	/**
	 * Creates a matrix with the specified dimensions.
	 *
	 * @param sizeX number of rows in the matrix
	 * @param sizeY number of columns in the matrix
	 */
	public SymmetricDifferenceBetweenTwoColumnsToThirdColumnWriter(int sizeX, int sizeY) {
		super(sizeX, sizeY);
	}

	/**
	 * Fills the matrix with randomly generated integer values within the specified
	 * range.
	 *
	 * @param lowerLimitGeneratedNumbers lower bound of generated numbers
	 * @param upperLimitGeneratedNumbers upper bound of generated numbers
	 */
	public void fill(int lowerLimitGeneratedNumbers, int upperLimitGeneratedNumbers) {
		for (int i = 0; i < this.getRowCount(); i++) {
			for (int j = 0; j < this.getColCount(); j++) {
				this.arrayBody[i][j] = RandomNumbersFromRangeGenerator
						.generateIntegerInGivenRange(lowerLimitGeneratedNumbers, upperLimitGeneratedNumbers);
			}
		}
	}

	/**
	 * Calculates the symmetric difference of two columns and writes the result to
	 * the specified target column. Remaining cells of the target column are filled
	 * with zeros.
	 *
	 * @param targetColumnIndex              index of the column where the result
	 *                                       will be written
	 * @param firstColumnForDifferenceIndex  index of the first column used for the
	 *                                       symmetric difference
	 * @param secondColumnForDifferenceIndex index of the second column used for the
	 *                                       symmetric difference
	 */
	public void writeSymmetricDifferenceBetweenTwoColumnsToThirdColumn(int targetColumnIndex,
			int firstColumnForDifferenceIndex, int secondColumnForDifferenceIndex) {

		int[] elementsFromFirstColumn = this.getElementsFromColumn(firstColumnForDifferenceIndex);
		int[] elementsFromSecondColumn = this.getElementsFromColumn(secondColumnForDifferenceIndex);

		int[] symmetricDifferenceResult = SymmetricDifferenceBetweenTwoArraysFinder
				.findSymmetricDifference(elementsFromFirstColumn, elementsFromSecondColumn);

		this.setColumnToZero(targetColumnIndex);
		this.writeElementsToColumn(targetColumnIndex, symmetricDifferenceResult);
	}

	/**
	 * Returns the string representation of the matrix and appends a message
	 * containing the sum of the first and last matrix elements.
	 *
	 * @return formatted string representation of the matrix
	 */
	@Override
	public String toString() {

		StringBuilder resultString = new StringBuilder(super.toString());
		int sumOfFirstAndLastElement = this.getSumOfFirstAndLastElement();

		resultString.append(
				String.format("the sum of the first and last element in an array: %d\n\n", sumOfFirstAndLastElement));

		return resultString.toString();
	}

	/**
	 * Calculates the sum of the first element and the last element of the matrix.
	 *
	 * @return sum of the element at position [0][0] and the last matrix element
	 */
	private int getSumOfFirstAndLastElement() {
		int lastRowIndex = this.getRowCount() - 1;
		int lastColIndex = this.getColCount() - 1;
		return this.arrayBody[0][0] + this.arrayBody[lastRowIndex][lastColIndex];
	}

	/**
	 * Sets all elements of the specified column to zero.
	 *
	 * @param columnIndex index of the column to reset
	 */
	private void setColumnToZero(int columnIndex) {
		for (int i = 0; i < this.getRowCount(); i++) {
			this.arrayBody[i][columnIndex] = 0;
		}
	}

	/**
	 * Writes the specified elements to the given column of the matrix. If the
	 * number of elements exceeds the number of rows, only the first elements that
	 * fit into the column are written.
	 *
	 * @param columnIndex index of the column where elements will be written
	 * @param elements    array of elements to write
	 */
	private void writeElementsToColumn(int columnIndex, int[] elements) {
		for (int i = 0; i < Math.min(this.getRowCount(), elements.length); i++) {
			this.arrayBody[i][columnIndex] = elements[i];
		}
	}

	/**
	 * Extracts all elements from the specified column of the matrix.
	 *
	 * @param columnIndex index of the column
	 * @return array containing all elements of the column
	 */
	private int[] getElementsFromColumn(int columnIndex) {
		int[] columnElements = new int[this.getRowCount()];

		for (int i = 0; i < this.getRowCount(); i++) {
			columnElements[i] = this.arrayBody[i][columnIndex];
		}

		return columnElements;
	}
}