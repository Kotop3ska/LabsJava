package ru.rsreu.morozov0612.suppliesutils;

import ru.rsreu.morozov0612.officesupply.OfficeSupply;
import ru.rsreu.morozov0612.datatypes.Department;
import ru.rsreu.morozov0612.datatypes.OfficeSupplyName;

public class OfficeSupplyValidator {

	private OfficeSupply officeSupply;

	public OfficeSupplyValidator(OfficeSupply officeSupply) {
		this.officeSupply = officeSupply;
	}

	public OfficeSupply getOfficeSupply() {
		return this.officeSupply;
	}

	public boolean isValid() {
		boolean isValid = true;
		isValid = isValid && this.isInventoryNumberValid() && this.isNameValid() && this.isDepartmentValid();

		return isValid;
	}

	private boolean isInventoryNumberValid() {
		boolean isInventoryNumberValid = true;
		isInventoryNumberValid = isInventoryNumberValid && this.officeSupply.inventoryNumber() >= 0;

		return isInventoryNumberValid;
	}

	private boolean isNameValid() {
		boolean isNameValid = true;
		isNameValid = isNameValid && this.officeSupply.name() != OfficeSupplyName.NULL;

		return isNameValid;
	}

	private boolean isDepartmentValid() {
		boolean isDepartmentValid = true;
		isDepartmentValid = isDepartmentValid && this.officeSupply.department() != Department.NULL;

		return isDepartmentValid;
	}
}
