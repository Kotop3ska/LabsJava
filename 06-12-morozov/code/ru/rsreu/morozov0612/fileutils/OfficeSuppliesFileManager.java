package ru.rsreu.morozov0612.fileutils;

import ru.rsreu.morozov0612.officesupply.OfficeSupply;

public class OfficeSuppliesFileManager {

	private OfficeSuppliesFileCreator fileCreator = new OfficeSuppliesFileCreator();
	private OfficeSuppliesFileReader fileReader = new OfficeSuppliesFileReader();
	private OfficeSuppliesFileWriter fileWriter = new OfficeSuppliesFileWriter();
	private OfficeSuppliesFileCopier fileCopier = new OfficeSuppliesFileCopier();
	private OfficeSuppliesFileMover fileMover = new OfficeSuppliesFileMover();

	public OfficeSuppliesFileManager() {
	}

	public void createFileFromPathName(String pathName) {
		this.fileCreator.createFileFromPathName(pathName);
	}

	public void moveFile(String sourceFilePath, String targetPath) {
		this.fileMover.moveFile(sourceFilePath, targetPath);
	}

	public void writeSuppliesToFile(OfficeSupply[] supplies, String pathName) {
		this.fileWriter.writeSuppliesToFile(supplies, pathName);
	}

	public OfficeSupply[] readSuppliesFromFile(String filePath) {
		return this.fileReader.readSuppliesFromFile(filePath);
	}

	public void copyFile(String sourcePath, String targetPath) {
		this.fileCopier.copyFile(sourcePath, targetPath);
	}
}