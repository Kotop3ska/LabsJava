package ru.rsreu.morozov0612.suppliesutils;

import ru.rsreu.morozov0612.datatypes.Department;
import ru.rsreu.morozov0612.datatypes.OfficeSupplyName;
import ru.rsreu.morozov0612.officesupply.OfficeSupply;

public class OfficeSuppliesInitializer {

	private final OfficeSupplyName[] names = { OfficeSupplyName.PEN, OfficeSupplyName.NOTEBOOK, OfficeSupplyName.MARKER,
			OfficeSupplyName.CALCULATOR, OfficeSupplyName.SCISSORS, OfficeSupplyName.NOTEBOOK,
			OfficeSupplyName.CALCULATOR, OfficeSupplyName.PEN, OfficeSupplyName.SCISSORS, OfficeSupplyName.MARKER };
	private final Department[] departments = { Department.IT, Department.IT, Department.HR, Department.LEGAL,
			Department.MARKETING, Department.FINANCE, Department.HR, Department.LEGAL, Department.FINANCE,
			Department.HR };

	public OfficeSuppliesInitializer() {
	}

	public OfficeSupply[] initializeSupplies() {
		OfficeSupply[] officeSupplies = new OfficeSupply[this.getSuppliesCount()];

		for (int inventoryNumber = 0; inventoryNumber < this.getSuppliesCount(); inventoryNumber++) {
			officeSupplies[inventoryNumber] = this.createVerifiedSupply(inventoryNumber, this.names[inventoryNumber],
					this.departments[inventoryNumber]);
		}

		return officeSupplies;
	}

	private OfficeSupply createVerifiedSupply(int inventoryNumber, OfficeSupplyName name, Department department) {
		OfficeSupply officeSupply = new OfficeSupply(inventoryNumber, name, department);
		OfficeSupplyValidator supplyValidator = new OfficeSupplyValidator(officeSupply);
		VerifiedOfficeSupplyCreator verifiedOfficeSupplyCreator = new VerifiedOfficeSupplyCreator();

		return verifiedOfficeSupplyCreator.createReliablySupply(supplyValidator);
	}

	private int getSuppliesCount() {
		return Math.min(this.names.length, this.departments.length);
	}
}
