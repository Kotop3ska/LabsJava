package ru.rsreu.morozov0612;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import ru.rsreu.morozov0612.officesupply.OfficeSupply;
import ru.rsreu.morozov0612.suppliesutils.OfficeSuppliesInitializer;
import ru.rsreu.morozov0612.suppliesstorage.OfficeSuppliesStorage;
import ru.rsreu.morozov0612.folderutils.FolderStructureCreator;
import ru.rsreu.morozov0612.fileutils.OfficeSuppliesFileManager;
import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;

public class ClientRunner {

	private static final char EXTENSION_SEPARATOR = '.';

	private ClientRunner() {
	}

	public static void main(String[] args) {
		Resourcer resourcer = ProjectResourcer.getInstance();

		String sourceFolderPath = resourcer.getString("files.folder.source.name");

		String moveFolderPath = resourcer.getString("files.folder.move.name");

		String copyFolderPath = moveFolderPath + File.separator + resourcer.getString("files.folder.copy.name");

		String sourceFilePath = sourceFolderPath + File.separator + resourcer.getString("files.file.data.name")
				+ ClientRunner.EXTENSION_SEPARATOR + resourcer.getString("files.file.data.extention");

		String copiedFilePath = copyFolderPath + File.separator + resourcer.getString("files.file.data.name")
				+ ClientRunner.EXTENSION_SEPARATOR + resourcer.getString("files.file.backup.extention");

		FolderStructureCreator folderStructureCreator = new FolderStructureCreator();

		folderStructureCreator.createDirectoryFromPathName(sourceFolderPath);
		folderStructureCreator.createDirectoryFromPathName(copyFolderPath);

		OfficeSuppliesInitializer initializer = new OfficeSuppliesInitializer();
		OfficeSuppliesStorage sourceOfficeSupplies = new OfficeSuppliesStorage(initializer.initializeSupplies());

		OfficeSuppliesFileManager officeSuppliesFileManager = new OfficeSuppliesFileManager();

		officeSuppliesFileManager.createFileFromPathName(sourceFilePath);
		officeSuppliesFileManager.writeSuppliesToFile(sourceOfficeSupplies.getOfficeSupplies(), sourceFilePath);

		officeSuppliesFileManager.createFileFromPathName(copiedFilePath);
		officeSuppliesFileManager.copyFile(sourceFilePath, copiedFilePath);

		if (ClientRunner.requestConfirmation(String.format(resourcer.getString("prompt.file.move.confirm"),
				Paths.get(sourceFilePath).getFileName(), Paths.get(moveFolderPath).toAbsolutePath()), resourcer)) {
			officeSuppliesFileManager.moveFile(sourceFilePath, moveFolderPath);
		}

		OfficeSupply[] readSupplies = officeSuppliesFileManager.readSuppliesFromFile(copiedFilePath);
		OfficeSuppliesStorage suppliesFromCopy = new OfficeSuppliesStorage(readSupplies);

		String movedFilePath = moveFolderPath + File.separator + resourcer.getString("files.file.data.name")
				+ ClientRunner.EXTENSION_SEPARATOR + resourcer.getString("files.file.data.extention");
		readSupplies = officeSuppliesFileManager.readSuppliesFromFile(movedFilePath);
		OfficeSuppliesStorage suppliesFromMove = new OfficeSuppliesStorage(readSupplies);

		StringBuilder resultString = new StringBuilder();
		resultString.append(resourcer.getString("message.out.supplies.source")).append(sourceOfficeSupplies.toString())
				.append(resourcer.getString("message.out.supplies.copied")).append(suppliesFromCopy.toString())
				.append(resourcer.getString("message.out.supplies.moved")).append(suppliesFromMove.toString());

		boolean comparisonResult = Arrays.equals(sourceOfficeSupplies.getOfficeSupplies(),
				suppliesFromCopy.getOfficeSupplies())
				&& Arrays.equals(sourceOfficeSupplies.getOfficeSupplies(), suppliesFromMove.getOfficeSupplies());
		resultString.append(String.format(resourcer.getString("message.out.comparison.results"), comparisonResult));

		System.out.println(resultString.toString());
	}

	private static boolean requestConfirmation(String message, Resourcer resourcer) {
		System.out.print(message);

		Scanner scanner = new Scanner(System.in);
		String response = scanner.nextLine().trim().toLowerCase();
		scanner.close();
		return response.equals(resourcer.getString("message.confirmation"));
	}
}
