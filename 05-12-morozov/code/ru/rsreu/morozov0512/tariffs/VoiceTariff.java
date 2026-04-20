package ru.rsreu.morozov0512.tariffs;

import ru.rsreu.morozov0512.datatypes.ServicePackage;
import ru.rsreu.morozov0512.datatypes.TariffName;
import ru.rsreu.morozov0512.datatypes.AdditionalServiceType;

public class VoiceTariff extends AbstractTariff {

	private static final AdditionalServiceType BONUS_MINUTES = AdditionalServiceType.MORE_MINUTES;
	private static final AdditionalServiceType BONUS_SMS = AdditionalServiceType.MORE_SMS;
	private static final double COST_DIVISOR = 2;

	public VoiceTariff(double basePrice, ServicePackage servicePackage, int clientsCount) {
		super(TariffName.VOICE_TARIFF, basePrice, servicePackage, clientsCount);
		super.updateServicePackage(VoiceTariff.BONUS_MINUTES, VoiceTariff.BONUS_SMS);
	}

	@Override
	protected double calculateMonthlyCost() {
		return super.getBasePrice() + VoiceTariff.BONUS_MINUTES.getServicePrice()
				+ VoiceTariff.BONUS_SMS.getServicePrice();
	}

	@Override
	protected double calculateActivationFee() {
		return super.getBasePrice() / VoiceTariff.COST_DIVISOR;
	}
}
