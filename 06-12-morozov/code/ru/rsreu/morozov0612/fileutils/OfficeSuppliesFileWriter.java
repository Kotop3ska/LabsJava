package ru.rsreu.morozov0612.fileutils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.morozov0612.folderutils.FolderStructureCreator;
import ru.rsreu.morozov0612.officesupply.OfficeSupply;

import java.util.logging.Level;
import com.prutzkow.projectlogger.ProjectLogger;

public class OfficeSuppliesFileWriter {

	private static final String FORMATTED_STRING_FOR_WRITING = "%d,%s,%s%n";

	public OfficeSuppliesFileWriter() {
	}

	public void writeSuppliesToFile(OfficeSupply[] supplies, String pathName) {
		Resourcer resourcer = ProjectResourcer.getInstance();
		File file = new File(pathName);

		try {
			if (!this.ensureFileExists(file)) {
				ProjectLogger.logger.log(Level.SEVERE, String.format(resourcer.getString("message.out.file.error"),
						file.getName(), file.getAbsolutePath()));
				return;
			}
			PrintWriter writer = new PrintWriter(new FileWriter(file));
			this.writeSuppliesData(writer, supplies);
			writer.close();

			ProjectLogger.logger.log(Level.INFO, String.format(resourcer.getString("message.out.file.write"),
					file.getName(), file.getAbsolutePath()));

		} catch (IOException e) {
			ProjectLogger.logger.log(Level.SEVERE, String.format(resourcer.getString("message.out.file.write.error"),
					file.getName(), file.getAbsolutePath()));
		}
	}

	private boolean ensureFileExists(File file) {
		try {

			if (!file.exists()) {
				File parentDir = file.getParentFile();
				if (parentDir != null) {
					FolderStructureCreator folderCreator = new FolderStructureCreator();
					folderCreator.createDirectoryFromPathName(parentDir.getPath());
				}
				file.createNewFile();
			}
			return true;

		} catch (IOException e) {
			return false;
		}
	}

	private void writeSuppliesData(PrintWriter writer, OfficeSupply[] supplies) {
		for (OfficeSupply supply : supplies) {
			writer.printf(OfficeSuppliesFileWriter.FORMATTED_STRING_FOR_WRITING, supply.inventoryNumber(),
					supply.name(), supply.department());
		}
	}
}
