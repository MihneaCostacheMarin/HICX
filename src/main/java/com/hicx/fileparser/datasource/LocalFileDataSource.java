package com.hicx.fileparser.datasource;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;

public class LocalFileDataSource implements DataSource {
    private final File[] files;
    private int currentIndex = 0;

    public LocalFileDataSource(File[] files) {
        System.out.println("just for testing");
        this.files = files;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < files.length;
    }

    @Override
    public byte[] next() {
        try {
            return Files.readAllBytes(files[currentIndex++].toPath());
        } catch (IOException e) {
            System.out.println("Error reading file: " + files[currentIndex - 1].getName());
            return new byte[0];
        }
    }
}


