package ru.rsreu.morozov0512.datatypes;

public enum TariffName {

	VOICE_TARIFF("Voice"), INTHERNET_TARIFF("Inthernet+"), SOCIAL_TARIFF("Social"), PREMIUM_TARIFF("Premium"),
	NULL("Null");

	private final String name;

	TariffName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
