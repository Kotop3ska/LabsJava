package ru.rsreu.morozov0612.suppliesstorage;

import java.util.Arrays;
import java.util.stream.Stream;
import ru.rsreu.morozov0612.officesupply.OfficeSupply;
import ru.rsreu.morozov0612.tablecreator.Alignment;
import ru.rsreu.morozov0612.tablecreator.TableCreator;

public class OfficeSuppliesStorage {

	private OfficeSupply[] officeSupplies;

	public OfficeSuppliesStorage(OfficeSupply... officeSupplies) {
		this.officeSupplies = officeSupplies;
	}

	public OfficeSupply[] getOfficeSupplies() {
		return Arrays.copyOf(this.officeSupplies, this.officeSupplies.length);
	}

	@Override
	public String toString() {

		String[] supplyHeaders = this.getSupplyHeaders();
		Object[][] suppliesData = this.getSupplyData();

		TableCreator tableCreater = new TableCreator(Alignment.LEFT);

		return tableCreater.createTable(supplyHeaders, suppliesData);
	}

	private String[] getSupplyHeaders() {
		return OfficeSupply.DEFAULT.getTableHeaders();
	}

	private Object[][] getSupplyData() {
		return Stream.of(this.officeSupplies).map(OfficeSupply::getRowData).toArray(Object[][]::new);
	}

}
