package ru.rsreu.morozov0512.tariffs;

import ru.rsreu.morozov0512.datatypes.ServicePackage;
import ru.rsreu.morozov0512.datatypes.TariffName;

public class SocialTariff extends AbstractTariff {

	private static final double DISCOUNT_PERCENTAGE = 30;
	private static final double PERCENTAGE_BASE = 100;

	public SocialTariff(double basePrice, ServicePackage servicePackage, int clientsCount) {
		super(TariffName.SOCIAL_TARIFF, basePrice, servicePackage, clientsCount);
	}

	@Override
	protected double calculateMonthlyCost() {
		return super.getBasePrice() - this.calculateDiscount();
	}

	@Override
	protected double calculateActivationFee() {
		return this.calculateDiscount();
	}

	private double calculateDiscount() {
		return super.getBasePrice() * (SocialTariff.DISCOUNT_PERCENTAGE / SocialTariff.PERCENTAGE_BASE);
	}

}
