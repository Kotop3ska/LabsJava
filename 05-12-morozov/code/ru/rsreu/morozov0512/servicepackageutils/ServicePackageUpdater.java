package ru.rsreu.morozov0512.servicepackageutils;

import ru.rsreu.morozov0512.datatypes.AdditionalServiceType;
import ru.rsreu.morozov0512.datatypes.ServicePackage;

public class ServicePackageUpdater {

	private ServicePackage currentServicePackage;

	public ServicePackageUpdater(ServicePackage currentServicePackage) {
		this.currentServicePackage = currentServicePackage;
	}

	public void updateServicePackage(AdditionalServiceType... additionalServiceTypes) {
		for (AdditionalServiceType additionalService : additionalServiceTypes) {
			this.addValueForService(additionalService);
		}

	}

	private void addValueForService(AdditionalServiceType additionalService) {
		if (additionalService == AdditionalServiceType.MORE_MINUTES) {
			this.currentServicePackage.addMinutes(additionalService.getBonusValue());
		} else if (additionalService == AdditionalServiceType.MORE_INTERNET) {
			this.currentServicePackage.addGigabytes(additionalService.getBonusValue());
		} else {
			this.currentServicePackage.addSms(additionalService.getBonusValue());
		}
	}
}
