package ru.rsreu.morozov0512.tariffutils;

import ru.rsreu.morozov0512.datatypes.ServicePackage;
import ru.rsreu.morozov0512.datatypes.TariffName;
import ru.rsreu.morozov0512.datatypes.VerifiedServicePackage;
import ru.rsreu.morozov0512.servicepackageutils.ServicePackageValidator;
import ru.rsreu.morozov0512.tariffs.AbstractTariff;
import ru.rsreu.morozov0512.tariffs.VoiceTariff;
import ru.rsreu.morozov0512.tariffs.InternetTariff;
import ru.rsreu.morozov0512.tariffs.SocialTariff;
import ru.rsreu.morozov0512.tariffs.PremiumTariff;
import ru.rsreu.morozov0512.tariffs.NullTariff;

public class TariffValidator {

	private TariffName name;
	private double basePrice;
	private ServicePackage servicePackage;
	private int clientsCount;

	public TariffValidator(TariffName name, double basePrice, ServicePackage servicePackage, int clientsCount) {
		this.name = name;
		this.basePrice = basePrice;
		this.servicePackage = servicePackage;
		this.clientsCount = clientsCount;
	}

	public AbstractTariff getTariff() {
		switch (this.name) {
		case VOICE_TARIFF:
			return new VoiceTariff(this.basePrice, this.servicePackage, this.clientsCount);
		case INTHERNET_TARIFF:
			return new InternetTariff(this.basePrice, this.servicePackage, this.clientsCount);
		case SOCIAL_TARIFF:
			return new SocialTariff(this.basePrice, this.servicePackage, this.clientsCount);
		case PREMIUM_TARIFF:
			return new PremiumTariff(this.basePrice, this.servicePackage, this.clientsCount);
		default:
			return new NullTariff();
		}
	}

	public boolean isValid() {
		boolean isTariffValid = true;
		isTariffValid = isTariffValid && this.isServisePackageValid() && this.isBasePriceValid() && this.isNameValid()
				&& this.isClientsCountValid();

		return isTariffValid;
	}

	private boolean isServisePackageValid() {
		ServicePackage servicePackage = this.servicePackage;
		ServicePackageValidator servicePackageValidator = new ServicePackageValidator(servicePackage);
		VerifiedServicePackage verifiedServicePackage = VerifiedServicePackage.createReliably(servicePackageValidator);

		return verifiedServicePackage != VerifiedServicePackage.DEFAULT;
	}

	private boolean isBasePriceValid() {
		boolean isBasePriceValid = true;
		isBasePriceValid = isBasePriceValid && this.basePrice >= 0;

		return isBasePriceValid;
	}

	private boolean isNameValid() {
		boolean isNameValid = true;
		isNameValid = isNameValid && this.name != TariffName.NULL;

		return isNameValid;
	}

	private boolean isClientsCountValid() {
		boolean isClientsCountValid = true;
		isClientsCountValid = isClientsCountValid && this.clientsCount >= 0;

		return isClientsCountValid;
	}

}
