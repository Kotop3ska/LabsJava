package ru.rsreu.morozov0612.officesupply;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.morozov0612.datatypes.Department;
import ru.rsreu.morozov0612.datatypes.OfficeSupplyName;
import ru.rsreu.morozov0612.tablecreator.TableRepresentable;

public record OfficeSupply(int inventoryNumber, OfficeSupplyName name, Department department)
		implements TableRepresentable {

	public static final OfficeSupply DEFAULT = new OfficeSupply(-1, OfficeSupplyName.NULL, Department.NULL);

	@Override
	public String[] getTableHeaders() {
		Resourcer resourcer = ProjectResourcer.getInstance();
		return new String[] { resourcer.getString("message.out.headers.number"),
				resourcer.getString("message.out.headers.name"),
				resourcer.getString("message.out.headers.department") };
	}

	@Override
	public Object[] getRowData() {
		return new Object[] { this.inventoryNumber, this.name, this.department };
	}

}
