package ru.rsreu.morozov0512.tariffs;

import ru.rsreu.morozov0512.datatypes.ServicePackage;
import ru.rsreu.morozov0512.datatypes.TariffName;
import ru.rsreu.morozov0512.datatypes.VerifiedServicePackage;

public class NullTariff extends AbstractTariff {

	public NullTariff() {
		super(TariffName.NULL, 0.0, VerifiedServicePackage.DEFAULT, 0);
	}

	private NullTariff(TariffName name, double basePrice, ServicePackage servicePackage, int clientsCount) {
		super(TariffName.NULL, 0.0, VerifiedServicePackage.DEFAULT, 0);
	}

	public double getTotalRevenue() {
		return -1;
	}

	@Override
	protected double calculateMonthlyCost() {
		return -1;
	}

	@Override
	protected double calculateActivationFee() {
		return -1;
	}
}
