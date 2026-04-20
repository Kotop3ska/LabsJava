package ru.rsreu.morozov0512.tariffs;

import ru.rsreu.morozov0512.datatypes.AdditionalServiceType;
import ru.rsreu.morozov0512.datatypes.ServicePackage;
import ru.rsreu.morozov0512.datatypes.TariffName;

public class PremiumTariff extends AbstractTariff {

	private static final AdditionalServiceType BONUS_GYGABYTES = AdditionalServiceType.MORE_INTERNET;
	private static final AdditionalServiceType BONUS_MINUTES = AdditionalServiceType.MORE_MINUTES;
	private static final AdditionalServiceType BONUS_SMS = AdditionalServiceType.MORE_SMS;
	private static final int PRICE_MULTIPLIER = 2;

	public PremiumTariff(double basePrice, ServicePackage servicePackage, int clientsCount) {
		super(TariffName.PREMIUM_TARIFF, basePrice, servicePackage, clientsCount);
		super.updateServicePackage(PremiumTariff.BONUS_GYGABYTES, PremiumTariff.BONUS_MINUTES, PremiumTariff.BONUS_SMS);
	}

	@Override
	protected double calculateMonthlyCost() {
		return super.getBasePrice() * PremiumTariff.PRICE_MULTIPLIER;
	}

	@Override
	protected double calculateActivationFee() {
		return 0.0;
	}

}
