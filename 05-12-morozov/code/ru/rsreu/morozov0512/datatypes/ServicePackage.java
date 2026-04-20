package ru.rsreu.morozov0512.datatypes;

public class ServicePackage {

	private int minutesCount;
	private int smsCount;
	private int gigabytesCount;

	public ServicePackage() {
	}

	public ServicePackage(int minutesCount, int smsCount, int gigabytesCount) {
		this.minutesCount = minutesCount;
		this.smsCount = smsCount;
		this.gigabytesCount = gigabytesCount;
	}

	public int getMinutesCount() {
		return this.minutesCount;
	}

	public int getSmsCount() {
		return this.smsCount;
	}

	public int getGigabytesCount() {
		return this.gigabytesCount;
	}

	public void addMinutes(int additionalMinutes) {
		this.minutesCount += additionalMinutes;
	}

	public void addSms(int additionalSms) {
		this.smsCount += additionalSms;
	}

	public void addGigabytes(int additionalGigabytes) {
		this.gigabytesCount += additionalGigabytes;
	}

}