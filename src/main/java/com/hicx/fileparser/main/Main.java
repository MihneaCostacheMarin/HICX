package com.hicx.fileparser.main;

import com.hicx.fileparser.file.FileSystemWatcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static final String PROCESSED_FOLDER = "processed";

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Error: Please provide a directory location!");
            return ;
        }

        String dirPath = args[0];
        System.out.println("DirPath: " + dirPath);
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(dirPath);

        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileSystemWatcher.onFileCreated(file);
            }
        }
        fileSystemWatcher.watchDirectory();
    }
}
