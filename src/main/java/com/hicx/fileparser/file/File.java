package com.hicx.fileparser.file;

public class File {
    private String filePath;
    private String fileName;

    public File(String filePath) {
        this.filePath = filePath;
        this.fileName = new java.io.File(filePath).getName();
    }
}
