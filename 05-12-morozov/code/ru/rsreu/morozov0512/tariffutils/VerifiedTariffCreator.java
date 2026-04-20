package ru.rsreu.morozov0512.tariffutils;

import ru.rsreu.morozov0512.tariffs.AbstractTariff;
import ru.rsreu.morozov0512.tariffs.NullTariff;

public class VerifiedTariffCreator {

	public static final AbstractTariff DEFAULT = new NullTariff();

	public VerifiedTariffCreator() {
	}

	public AbstractTariff createReliablyTariff(TariffValidator tariffValidator) {
		boolean isValid = tariffValidator.isValid();
		if (isValid) {
			AbstractTariff tariff = tariffValidator.getTariff();

			return tariff;
		} else {

			return VerifiedTariffCreator.DEFAULT;
		}
	}
}
