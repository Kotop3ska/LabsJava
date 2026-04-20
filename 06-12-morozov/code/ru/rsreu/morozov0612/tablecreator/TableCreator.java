package ru.rsreu.morozov0612.tablecreator;

import java.util.Arrays;

public class TableCreator {

	private static final String DEFAULT_CORNER_SIGN = "+";
	private static final String DEFAULT_HORIZONTAL_SIGN = "–";
	private static final String DEFAULT_VERTICAL_SIGN = "|";

	private String cornerSign;
	private String horizontalSign;
	private String verticalSign;
	private Alignment alignment;

	public TableCreator() {
		this.cornerSign = TableCreator.DEFAULT_CORNER_SIGN;
		this.horizontalSign = TableCreator.DEFAULT_HORIZONTAL_SIGN;
		this.verticalSign = TableCreator.DEFAULT_VERTICAL_SIGN;
		this.alignment = Alignment.LEFT;
	}

	public TableCreator(Alignment alignment) {
		this.cornerSign = TableCreator.DEFAULT_CORNER_SIGN;
		this.horizontalSign = TableCreator.DEFAULT_HORIZONTAL_SIGN;
		this.verticalSign = TableCreator.DEFAULT_VERTICAL_SIGN;
		this.alignment = alignment;
	}

	public TableCreator(String cornerSign, String horizontalSign, String verticalSign, Alignment alignment) {
		this.cornerSign = cornerSign;
		this.horizontalSign = horizontalSign;
		this.verticalSign = verticalSign;
		this.alignment = alignment;
	}

	public String createTable(String[] headers, Object[][] data) {
		StringBuilder resultTable = new StringBuilder();
		int[] columnWidths = TableCreator.getColumnWidths(headers, data);

		resultTable.append(this.createHeaders(headers, columnWidths));
		resultTable.append(this.createRows(data, columnWidths));
		resultTable.append(this.makeSeparatorLine(columnWidths)).append("\n\n");

		return resultTable.toString();
	}

	private static int[] getColumnWidths(String[] headers, Object[][] data) {
		int[] columnWidths = TableCreator.getHeadersWidths(headers);

		for (Object[] row : data) {
			for (int i = 0; i < row.length; i++) {
				columnWidths[i] = Math.max(columnWidths[i], row[i].toString().length());
			}
		}

		return TableCreator.addPadding(columnWidths);
	}

	private static int[] getHeadersWidths(String[] headers) {
		int[] headersWidths = new int[headers.length];

		for (int i = 0; i < headers.length; i++) {
			headersWidths[i] = headers[i].length();
		}

		return headersWidths;
	}

	private static int[] addPadding(int[] widths) {
		int[] widthsWithPadding = Arrays.copyOf(widths, widths.length);
		for (int i = 0; i < widths.length; i++) {
			widthsWithPadding[i] += 2;
		}

		return widthsWithPadding;
	}

	private String fillRow(Object[] cells, int[] widths) {
		StringBuilder filledRow = new StringBuilder(this.verticalSign);

		for (int i = 0; i < widths.length; i++) {
			String value = cells[i].toString();
			int cellWidth = widths[i];
			String formattedValue;

			switch (this.alignment) {
			case LEFT:
				formattedValue = String.format("%-" + cellWidth + "s", value);
				break;
			case RIGHT:
				formattedValue = String.format("%" + cellWidth + "s", value);
				break;
			case CENTER:
				formattedValue = centerText(value, cellWidth);
				break;
			default:
				formattedValue = value;
			}

			filledRow.append(formattedValue).append(this.verticalSign);
		}

		return filledRow.toString();
	}

	private String centerText(String text, int width) {
		if (text.length() >= width) {
			return text;
		}
		int totalPadding = width - text.length();
		int leftPadding = totalPadding / 2;
		int rightPadding = totalPadding - leftPadding;
		return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
	}

	private String makeSeparatorLine(int[] widths) {
		StringBuilder separatorLine = new StringBuilder(this.cornerSign);

		for (int width : widths) {
			separatorLine.append(this.horizontalSign.repeat(width)).append(this.cornerSign);
		}

		return separatorLine.toString();
	}

	private String createHeaders(String[] headers, int[] columnWidths) {
		StringBuilder filledHeaders = new StringBuilder();

		filledHeaders.append(this.makeSeparatorLine(columnWidths)).append("\n");
		filledHeaders.append(this.fillRow(headers, columnWidths)).append("\n");
		filledHeaders.append(this.makeSeparatorLine(columnWidths)).append("\n");

		return filledHeaders.toString();
	}

	private String createRows(Object[][] data, int[] columnWidths) {
		StringBuilder filledRows = new StringBuilder();

		for (Object[] row : data) {
			filledRows.append(this.fillRow(row, columnWidths)).append("\n");
		}

		return filledRows.toString();
	}

}
