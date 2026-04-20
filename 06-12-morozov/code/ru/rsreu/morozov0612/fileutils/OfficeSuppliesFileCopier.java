package ru.rsreu.morozov0612.fileutils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

import com.prutzkow.projectlogger.ProjectLogger;
import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;

public class OfficeSuppliesFileCopier {

	public OfficeSuppliesFileCopier() {
	}

	public void copyFile(String sourcePath, String targetPath) {
		Resourcer resourcer = ProjectResourcer.getInstance();

		try {
			Files.copy(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

			ProjectLogger.logger.log(Level.INFO, String.format(resourcer.getString("message.out.file.copy"),
					Paths.get(sourcePath).getFileName(), Paths.get(targetPath).toAbsolutePath()));

		} catch (IOException e) {
			ProjectLogger.logger.log(Level.SEVERE, String.format(resourcer.getString("message.out.file.copy.error"),
					Paths.get(sourcePath).getFileName(), Paths.get(targetPath).toAbsolutePath()));
		}
	}

}
