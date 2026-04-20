package ru.rsreu.morozov0312.arrayprocessor;

public class IntegerArray {
	private final int arrayLength;
	private int[] arrayElements;

	public IntegerArray(int... elements) {
		this.arrayElements = elements;
		this.arrayLength = this.arrayElements.length;
	}

	public int getValueByIndex(int index) {
		return this.arrayElements[index];
	}

	public int getArrayLength() {
		return this.arrayLength;
	}

	public void setValueByIndex(int index, int value) {
		this.arrayElements[index] = value;
	}

	@Override
	public String toString() {
		StringBuilder outputString = new StringBuilder();
		for (int i = 0; i < this.arrayLength; i++) {
			outputString.append(this.arrayElements[i]);
			if (i < this.arrayLength - 1) {
				outputString.append(", ");
			}
		}
		return outputString.toString();
	}
}
