package ru.rsreu.morozov0512.datatypes;

import ru.rsreu.morozov0512.servicepackageutils.ServicePackageValidator;

public class VerifiedServicePackage extends ServicePackage {

	public static final VerifiedServicePackage DEFAULT = new VerifiedServicePackage();

	private VerifiedServicePackage() {
	}

	private VerifiedServicePackage(int minutesCount, int smsCount, int gigabytesCount) {
		super(minutesCount, smsCount, gigabytesCount);
	}

	private VerifiedServicePackage(ServicePackage servicePackage) {
		super(servicePackage.getMinutesCount(), servicePackage.getSmsCount(), servicePackage.getGigabytesCount());
	}

	public static VerifiedServicePackage createReliably(ServicePackageValidator servicePackageValidator) {
		boolean isValid = servicePackageValidator.isValid();
		if (isValid) {
			ServicePackage servicePackage = servicePackageValidator.getServicePackage();
			VerifiedServicePackage verifiedServicePackage = new VerifiedServicePackage(servicePackage);

			return verifiedServicePackage;
		} else {
			return VerifiedServicePackage.DEFAULT;
		}
	}
}
