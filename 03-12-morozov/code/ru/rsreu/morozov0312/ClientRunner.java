package ru.rsreu.morozov0312;

import java.util.Locale;
import ru.rsreu.morozov0312.arrayprocessor.IntegerArray;
import ru.rsreu.morozov0312.arrayprocessor.BasedOnResultsComparisonArrayModifier;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;

public class ClientRunner {

	private static Resourcer resourcer = ProjectResourcer.getInstance();

	private ClientRunner() {
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("RU", "ru"));
		StringBuilder resultOutputRussian = new StringBuilder();
		StringBuilder resultOutputEnglish = new StringBuilder();

		IntegerArray intArray = new IntegerArray(3, -1, 17, 0, 24, -11, 13, 2, -8, 9, 4, 1);
		BasedOnResultsComparisonArrayModifier arrayModifierBasedOnResultsComparison = new BasedOnResultsComparisonArrayModifier(
				intArray);

		resultOutputRussian.append(String.format(resourcer.getString("message.source.array"), intArray.toString()));
		Locale.setDefault(Locale.US);
		resultOutputEnglish.append(String.format(resourcer.getString("message.source.array"), intArray.toString()));

		int comparingResult = arrayModifierBasedOnResultsComparison.modifyArrayBasedComparisonResults();

		Locale.setDefault(Locale.of("RU", "ru"));
		if (comparingResult > 0) {
			resultOutputRussian.append(resourcer.getString("message.sum.greater"));
		} else if (comparingResult < 0) {
			resultOutputRussian.append(resourcer.getString("message.sum.less"));
		} else {
			resultOutputRussian.append(resourcer.getString("message.equality"));
		}

		resultOutputRussian.append(String.format(resourcer.getString("message.processed.array"), intArray.toString()));

		Locale.setDefault(Locale.US);
		if (comparingResult > 0) {
			resultOutputEnglish.append(resourcer.getString("message.sum.greater"));
		} else if (comparingResult < 0) {
			resultOutputEnglish.append(resourcer.getString("message.sum.less"));
		} else {
			resultOutputEnglish.append(resourcer.getString("message.equality"));
		}

		resultOutputEnglish.append(String.format(resourcer.getString("message.processed.array"), intArray.toString()));

		System.out.println(resultOutputRussian);
		System.out.println(resultOutputEnglish);
	}

}
