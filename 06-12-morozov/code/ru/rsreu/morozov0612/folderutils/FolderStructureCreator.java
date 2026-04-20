package ru.rsreu.morozov0612.folderutils;

import java.io.File;
import java.util.logging.Level;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import com.prutzkow.projectlogger.ProjectLogger;

public class FolderStructureCreator {

	public FolderStructureCreator() {
	}

	public void createDirectoryFromPathName(String pathName) {
		Resourcer resourcer = ProjectResourcer.getInstance();
		File directory = new File(pathName);
		String absolutePath = directory.getAbsolutePath();

		try {
			String message;
			if (directory.mkdirs()) {
				message = resourcer.getString("message.out.folder.created");
			} else {
				message = resourcer.getString("message.out.folder.not.created");
			}
			ProjectLogger.logger.log(Level.INFO, String.format(message, pathName, absolutePath));
		} catch (Exception e) {
			ProjectLogger.logger.log(Level.SEVERE,
					String.format(resourcer.getString("message.out.folder.error"), pathName, absolutePath));
		}
	}

}
