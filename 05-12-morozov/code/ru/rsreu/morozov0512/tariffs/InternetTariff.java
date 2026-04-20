package ru.rsreu.morozov0512.tariffs;

import ru.rsreu.morozov0512.datatypes.ServicePackage;
import ru.rsreu.morozov0512.datatypes.TariffName;
import ru.rsreu.morozov0512.datatypes.AdditionalServiceType;

public class InternetTariff extends AbstractTariff {

	private static final AdditionalServiceType BONUS_GIGABYTES = AdditionalServiceType.MORE_INTERNET;

	public InternetTariff(double basePrice, ServicePackage servicePackage, int clientsCount) {
		super(TariffName.INTHERNET_TARIFF, basePrice, servicePackage, clientsCount);
		super.updateServicePackage(InternetTariff.BONUS_GIGABYTES);
	}

	@Override
	protected double calculateMonthlyCost() {
		return super.getBasePrice() + InternetTariff.BONUS_GIGABYTES.getServicePrice();
	}

	@Override
	protected double calculateActivationFee() {
		return InternetTariff.BONUS_GIGABYTES.getServicePrice();
	}
}
