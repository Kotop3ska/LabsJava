package ru.rsreu.morozov0512.datatypes;

public enum AdditionalServiceType {

	MORE_MINUTES(150.0, 200), MORE_INTERNET(200.0, 25), MORE_SMS(100.0, 100);

	private double servicePrice;
	private int bonusValue;

	AdditionalServiceType(double servicePrice, int bonusValue) {
		this.servicePrice = servicePrice;
		this.bonusValue = bonusValue;
	}

	public double getServicePrice() {
		return this.servicePrice;
	}

	public int getBonusValue() {
		return this.bonusValue;
	}
}
