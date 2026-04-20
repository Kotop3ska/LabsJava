package ru.rsreu.morozov0612.fileutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;

import com.prutzkow.projectlogger.ProjectLogger;
import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;

public class OfficeSuppliesFileMover {

	public OfficeSuppliesFileMover() {
	}

	public void moveFile(String sourceFilePath, String targetPath) {
		Resourcer resourcer = ProjectResourcer.getInstance();
		targetPath += File.separator + Paths.get(sourceFilePath).getFileName();
		try {
			Files.move(Paths.get(sourceFilePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
			ProjectLogger.logger.log(Level.INFO, String.format(resourcer.getString("message.out.file.move"),
					Paths.get(sourceFilePath).getFileName(), Paths.get(targetPath).toAbsolutePath()));
		} catch (IOException e) {
			ProjectLogger.logger.log(Level.SEVERE, String.format(resourcer.getString("message.out.file.move.error"),
					Paths.get(sourceFilePath).getFileName(), targetPath));
		}
	}

}
