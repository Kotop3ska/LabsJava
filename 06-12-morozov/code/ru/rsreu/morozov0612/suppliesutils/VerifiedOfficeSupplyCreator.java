package ru.rsreu.morozov0612.suppliesutils;

import ru.rsreu.morozov0612.officesupply.OfficeSupply;

public class VerifiedOfficeSupplyCreator {

	public VerifiedOfficeSupplyCreator() {
	}

	public OfficeSupply createReliablySupply(OfficeSupplyValidator supplyValidator) {
		boolean isValid = supplyValidator.isValid();
		if (isValid) {
			OfficeSupply officeSupply = supplyValidator.getOfficeSupply();

			return officeSupply;
		} else {

			return OfficeSupply.DEFAULT;
		}
	}

}
