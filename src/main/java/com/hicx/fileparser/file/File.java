package com.hicx.fileparser.file;

import com.hicx.fileparser.main.Main;

public class File {
    private String filePath;
    private String fileName;

    public File(String filePath) {
        this.filePath = filePath;
        this.fileName = new java.io.File(filePath).getName();
    }

    /**
     * This method takes care of finding the main directory path and appending the "processed" to it
     * If the directory does not exist, it will create it
     * Then, it will move the original file to it
     */
    public void moveToProcessed() {
        String processedDirPath = filePath.substring(0, filePath.lastIndexOf(java.io.File.separator) + 1) + Main.PROCESSED_FOLDER;
        java.io.File processedDirectory = new java.io.File(processedDirPath);

        if (!processedDirectory.exists()) {
            processedDirectory.mkdir();
        }

        java.io.File originalFile = new java.io.File(filePath);
        java.io.File processedFile = new java.io.File(processedDirPath + java.io.File.separator + fileName);

        originalFile.renameTo(processedFile);
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }
}
