package ru.rsreu.morozov0412;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import java.util.Locale;
import ru.rsreu.morozov0412.arrayprocessor.SymmetricDifferenceBetweenTwoColumnsToThirdColumnWriter;

/**
 * Client application demonstrating computation of the symmetric difference
 * between two matrix columns and writing the result to another column.
 * 
 * @author Morozov
 */
public class ClientRunner {

	/**
	 * Upper bound of generated numbers.
	 */
	private static final int UPPER_LIMIT_GENERATED_NUMBERS = 10;

	/**
	 * Lower bound of generated numbers.
	 */
	private static final int LOWER_LIMIT_GENERATED_NUMBERS = 0;

	/**
	 * Number of matrix rows.
	 */
	private static final int ROW_COUNT = 5;

	/**
	 * Number of matrix columns.
	 */
	private static final int COL_COUNT = 3;

	/**
	 * Index of the column where the symmetric difference is written.
	 */
	private static final int TARGET_COL_INDEX = 0;

	/**
	 * Index of the first column used to compute the symmetric difference.
	 */
	private static final int FIRST_COL_FOR_DIFFERENCE_INDEX = 1;

	/**
	 * Index of the second column used to compute the symmetric difference.
	 */
	private static final int SECOND_COL_FOR_DIFFERENCE_INDEX = 2;

	/**
	 * Resource manager used for retrieving localized messages.
	 */
	private static Resourcer resourcer = ProjectResourcer.getInstance();

	/**
	 * Prevents instantiation of this class.
	 */
	private ClientRunner() {
	}

	/**
	 * Starts the client application.
	 *
	 * Creates a matrix, fills it with random values, computes the symmetric
	 * difference of two columns, writes the result to another column and prints the
	 * matrix before and after processing.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		StringBuilder resultString = new StringBuilder();

		SymmetricDifferenceBetweenTwoColumnsToThirdColumnWriter symmetricDifferenceBetweenTwoColumnsToThirdColumnWriter = 
				new SymmetricDifferenceBetweenTwoColumnsToThirdColumnWriter(
				ClientRunner.ROW_COUNT, ClientRunner.COL_COUNT);

		symmetricDifferenceBetweenTwoColumnsToThirdColumnWriter.fill(ClientRunner.LOWER_LIMIT_GENERATED_NUMBERS,
				ClientRunner.UPPER_LIMIT_GENERATED_NUMBERS);
		resultString.append(resourcer.getString("message.source.array"))
				.append(symmetricDifferenceBetweenTwoColumnsToThirdColumnWriter.toString());

		symmetricDifferenceBetweenTwoColumnsToThirdColumnWriter.writeSymmetricDifferenceBetweenTwoColumnsToThirdColumn(
				ClientRunner.TARGET_COL_INDEX, ClientRunner.FIRST_COL_FOR_DIFFERENCE_INDEX,
				ClientRunner.SECOND_COL_FOR_DIFFERENCE_INDEX);

		resultString.append(resourcer.getString("message.processed.array"))
				.append(symmetricDifferenceBetweenTwoColumnsToThirdColumnWriter.toString());

		System.out.println(resultString);

	}

}
