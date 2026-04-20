package ru.rsreu.morozov0612.fileutils;

import java.io.File;
import java.io.IOException;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;

import java.util.logging.Level;
import com.prutzkow.projectlogger.ProjectLogger;

public class OfficeSuppliesFileCreator {

	public OfficeSuppliesFileCreator() {
	}

	public void createFileFromPathName(String pathName) {
		Resourcer resourcer = ProjectResourcer.getInstance();
		File file = new File(pathName);

		try {
			String message;
			if (file.createNewFile()) {
				message = resourcer.getString("message.out.file.created");
			} else {
				message = resourcer.getString("message.out.file.not.created");
			}
			ProjectLogger.logger.log(Level.INFO, String.format(message, file.getName(), file.getAbsolutePath()));
		} catch (IOException e) {
			ProjectLogger.logger.log(Level.SEVERE,
					String.format(resourcer.getString("message.out.file.error"), pathName, file.getAbsolutePath()));
		}
	}

}
