package ru.rsreu.morozov0512.tablebuilder;

import java.util.Arrays;

public class TableBuilder {

	private final String cornerSign;
	private final String horizontalSign;
	private final String verticalSign;

	public TableBuilder() {
		this.cornerSign = "+";
		this.horizontalSign = "–";
		this.verticalSign = "|";
	}

	public TableBuilder(String cornerSign, String horizontalSign, String verticalSign) {
		this.cornerSign = cornerSign;
		this.horizontalSign = horizontalSign;
		this.verticalSign = verticalSign;
	}

	public String buildTable(String[] headers, Object[][] data) {
		StringBuilder resultTable = new StringBuilder();
		int[] columnWidths = TableBuilder.getColumnWidths(headers, data);

		resultTable.append(this.buildHeaders(headers, columnWidths));
		resultTable.append(this.buildRows(data, columnWidths));
		resultTable.append(this.makeSeparatorLine(columnWidths)).append("\n\n");

		return resultTable.toString();
	}

	private static int[] getColumnWidths(String[] headers, Object[][] data) {
		int[] columnWidths = TableBuilder.getHeadersWidths(headers);

		for (Object[] row : data) {
			for (int i = 0; i < row.length; i++) {
				columnWidths[i] = Math.max(columnWidths[i], row[i].toString().length());
			}
		}

		return TableBuilder.addPadding(columnWidths);
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
			filledRow.append(String.format(" %-" + (widths[i] - 2) + "s " + this.verticalSign, value));
		}

		return filledRow.toString();
	}

	private String makeSeparatorLine(int[] widths) {
		StringBuilder separatorLine = new StringBuilder(this.cornerSign);

		for (int width : widths) {
			separatorLine.append(this.horizontalSign.repeat(width)).append(this.cornerSign);
		}

		return separatorLine.toString();
	}

	private String buildHeaders(String[] headers, int[] columnWidths) {
		StringBuilder filledHeaders = new StringBuilder();

		filledHeaders.append(this.makeSeparatorLine(columnWidths)).append("\n");
		filledHeaders.append(this.fillRow(headers, columnWidths)).append("\n");
		filledHeaders.append(this.makeSeparatorLine(columnWidths)).append("\n");

		return filledHeaders.toString();
	}

	private String buildRows(Object[][] data, int[] columnWidths) {
		StringBuilder filledRows = new StringBuilder();

		for (Object[] row : data) {
			filledRows.append(this.fillRow(row, columnWidths)).append("\n");
		}

		return filledRows.toString();
	}

}
