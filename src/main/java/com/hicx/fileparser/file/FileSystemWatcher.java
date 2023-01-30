package com.hicx.fileparser.file;

import java.io.File;
import java.nio.file.*;

public class FileSystemWatcher {
    private static final String[] SUPPORTED_FILE_TYPES = new String[]{".txt", ".pdf", ".doc"};

    private String directoryPath;

    public FileSystemWatcher(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void watchDirectory() {
        try {
            Path path = Paths.get(directoryPath);
            WatchService watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            while (true) {
                WatchKey watchKey = watchService.take();
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    Path filePath = ((Path) event.context()).toAbsolutePath();
                    File file = new File(filePath.toString());

                    if (file.isFile()) {
                        onFileCreated(file);
                    }
                }

                if (!watchKey.reset()) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error: Failed to watch directory. Exception: " + e.getMessage());
        }
    }

    public void onFileCreated(File file) {
        if (isSupportedFileType(file.getName())) {
            FileProcessor fileProcessor = new FileProcessor(file);
            fileProcessor.processFile();
            fileProcessor.printStatistics();
            fileProcessor.moveToProcessed();
        }
    }

    private boolean isSupportedFileType(String fileName) {
        for (String fileType : SUPPORTED_FILE_TYPES) {
            if (fileName.endsWith(fileType)) {
                return true;
            }
        }
        return false;
    }
}
