package ru.rsreu.morozov0512.tariffutils;

import ru.rsreu.morozov0512.datatypes.TariffName;
import ru.rsreu.morozov0512.datatypes.ServicePackage;
import ru.rsreu.morozov0512.tariffs.AbstractTariff;

public class TariffsInitializer {

	private final TariffName[] tariffNames = { TariffName.INTHERNET_TARIFF, TariffName.PREMIUM_TARIFF,
			TariffName.SOCIAL_TARIFF, TariffName.VOICE_TARIFF };
	private final int[] clientsCountForTariffs = { 1531, 348, 3400, 2104 };
	private final double basePrice = 500.0;
	private final int baseGygabytesCount = 25;
	private final int baseMinutesCount = 150;
	private final int baseSmsCount = 100;

	public TariffsInitializer() {
	}

	public AbstractTariff[] initializeTariffs() {
		AbstractTariff[] tariffs = new AbstractTariff[this.getTariffsCount()];

		for (int i = 0; i < this.getTariffsCount(); i++) {
			tariffs[i] = this.createVerifiedTariff(this.tariffNames[i], this.clientsCountForTariffs[i]);
		}

		return tariffs;
	}

	private AbstractTariff createVerifiedTariff(TariffName tariffName, int clientsCount) {
		ServicePackage baseServicePackage = new ServicePackage(this.baseMinutesCount, this.baseSmsCount,
				this.baseGygabytesCount);

		TariffValidator tariffValidator = new TariffValidator(tariffName, this.basePrice, baseServicePackage,
				clientsCount);
		VerifiedTariffCreator verifiedTariffCreator = new VerifiedTariffCreator();
		AbstractTariff verifiedTariff = verifiedTariffCreator.createReliablyTariff(tariffValidator);

		return verifiedTariff;
	}

	private int getTariffsCount() {
		return Math.min(this.tariffNames.length, this.clientsCountForTariffs.length);
	}
}
