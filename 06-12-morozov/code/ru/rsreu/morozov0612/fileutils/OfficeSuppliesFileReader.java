package ru.rsreu.morozov0612.fileutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.morozov0612.datatypes.Department;
import ru.rsreu.morozov0612.datatypes.OfficeSupplyName;
import ru.rsreu.morozov0612.officesupply.OfficeSupply;
import ru.rsreu.morozov0612.suppliesutils.OfficeSupplyValidator;
import ru.rsreu.morozov0612.suppliesutils.VerifiedOfficeSupplyCreator;

import java.util.logging.Level;
import com.prutzkow.projectlogger.ProjectLogger;

public class OfficeSuppliesFileReader {

	private static final int SUPPLY_FIELDS_COUNT = 3;
	private static final String FIELD_SEPARATOR = ",";

	public OfficeSuppliesFileReader() {
	}

	public OfficeSupply[] readSuppliesFromFile(String pathName) {
		Resourcer resourcer = ProjectResourcer.getInstance();
		File file = new File(pathName);

		if (!this.isValidFile(file, resourcer)) {
			return new OfficeSupply[] { OfficeSupply.DEFAULT };
		}

		try {
			OfficeSupply[] supplies = parseSuppliesFile(file, resourcer);

			ProjectLogger.logger.log(Level.INFO, String.format(resourcer.getString("message.out.object.loaded"),
					file.getName(), file.getAbsolutePath()));
			return supplies;

		} catch (IOException e) {
			ProjectLogger.logger.log(Level.SEVERE, String.format(resourcer.getString("message.out.object.load.error"),
					file.getName(), file.getAbsolutePath()));
			return new OfficeSupply[] { OfficeSupply.DEFAULT };
		}
	}

	private boolean isValidFile(File file, Resourcer resourcer) {
		if (!file.exists()) {
			ProjectLogger.logger.log(Level.SEVERE, String.format(resourcer.getString("message.out.object.not.found"),
					file.getName(), file.getAbsolutePath()));
			return false;
		}
		return true;
	}

	private int countValidEntries(File file) throws IOException {
		int count = 0;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.isEmpty()) {
				continue;
			}
			String[] parts = line.split(OfficeSuppliesFileReader.FIELD_SEPARATOR);
			if (parts.length == OfficeSuppliesFileReader.SUPPLY_FIELDS_COUNT
					&& this.isValidInventoryNumber(parts[0].trim()) && this.isValidDepartment(parts[2].trim())) {
				count++;
			}
		}
		reader.close();

		return count;
	}

	private boolean isValidDepartment(String value) {
		if (value == null || value.isEmpty()) {
			return false;
		}
		try {
			Department.valueOf(value.toUpperCase());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	private boolean isValidInventoryNumber(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private OfficeSupply[] parseSuppliesFile(File file, Resourcer resourcer) throws IOException {

		int suppliesCount = this.countValidEntries(file);

		if (suppliesCount == 0) {
			return new OfficeSupply[0];
		}

		OfficeSupply[] supplies = new OfficeSupply[suppliesCount];
		int index = 0;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		int lineNumber = 0;

		while ((line = reader.readLine()) != null) {
			lineNumber++;
			supplies[index++] = this.parseLine(line, lineNumber, file);
		}
		reader.close();

		return supplies;
	}

	private OfficeSupply parseLine(String line, int lineNumber, File file) {
		line = line.trim();
		String[] parts = line.split(OfficeSuppliesFileReader.FIELD_SEPARATOR);

		if (line.isEmpty() || parts.length != OfficeSuppliesFileReader.SUPPLY_FIELDS_COUNT) {
			return OfficeSupply.DEFAULT;
		}

		try {
			int inventoryNumber = Integer.parseInt(parts[0].trim());
			OfficeSupplyName name = OfficeSupplyName.valueOf(parts[1].trim());
			Department department = Department.valueOf(parts[2].trim());

			return this.createVerifiedSupply(inventoryNumber, name, department);

		} catch (IllegalArgumentException e) {
			return OfficeSupply.DEFAULT;
		}
	}

	private OfficeSupply createVerifiedSupply(int inventoryNumber, OfficeSupplyName name, Department department) {
		OfficeSupply officeSupply = new OfficeSupply(inventoryNumber, name, department);
		OfficeSupplyValidator supplyValidator = new OfficeSupplyValidator(officeSupply);
		VerifiedOfficeSupplyCreator verifiedOfficeSupplyCreator = new VerifiedOfficeSupplyCreator();

		return verifiedOfficeSupplyCreator.createReliablySupply(supplyValidator);
	}
}
