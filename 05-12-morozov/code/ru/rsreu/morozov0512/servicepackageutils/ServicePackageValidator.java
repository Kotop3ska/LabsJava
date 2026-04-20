package ru.rsreu.morozov0512.servicepackageutils;

import ru.rsreu.morozov0512.datatypes.ServicePackage;

public class ServicePackageValidator {

	private ServicePackage servicePackage;

	public ServicePackageValidator(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}

	public boolean isValid() {
		boolean isServicePackageValid = true;
		isServicePackageValid = isServicePackageValid && this.isServicePackageValuesValid();
		return isServicePackageValid;
	}

	public final ServicePackage getServicePackage() {
		return this.servicePackage;
	}

	private boolean isServicePackageValuesValid() {
		boolean isServicePackageValuesValid = this.isServiceValueValid(this.servicePackage.getGigabytesCount())
				&& this.isServiceValueValid(this.servicePackage.getMinutesCount())
				&& this.isServiceValueValid(this.servicePackage.getSmsCount());

		return isServicePackageValuesValid;
	}

	private boolean isServiceValueValid(int serviceValue) {
		return serviceValue >= 0;
	}
}
