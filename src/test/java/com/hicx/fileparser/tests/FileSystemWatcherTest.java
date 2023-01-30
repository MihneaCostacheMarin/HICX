package com.hicx.fileparser.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.hicx.fileparser.file.FileSystemWatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileSystemWatcherTest {
    private static final String TEST_DIR = "/Users/mihnea-costachemarin/Downloads/test_dir";
    private static final String TEST_FILE = "test_file.txt";
    private static final String TEST_FILE_PATH = TEST_DIR + File.separator + TEST_FILE;
    private static final String TEST_FILE_PROCESSED_PATH = TEST_DIR + File.separator + "processed" + File.separator + TEST_FILE;
    private static final String UNSUPPORTED_FILE = "unsupported_file.xyz";
    private static final String UNSUPPORTED_FILE_PATH = TEST_DIR + File.separator + UNSUPPORTED_FILE;

    @Before
    public void setUp() throws IOException {
        Files.createDirectory(Paths.get(TEST_DIR));
        Files.createFile(Paths.get(TEST_FILE_PATH));
        Files.createFile(Paths.get(UNSUPPORTED_FILE_PATH));
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE_PROCESSED_PATH));
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
        Files.deleteIfExists(Paths.get(UNSUPPORTED_FILE_PATH));
        Files.deleteIfExists(Paths.get(TEST_DIR));
    }

    @Test
    public void testFileSystemWatcher() {
        FileSystemWatcher fileMonitor = new FileSystemWatcher(TEST_DIR);
        Path path = Paths.get(TEST_FILE_PROCESSED_PATH);
        fileMonitor.onFileCreated(path.toFile());

        assertTrue("Processed file should exist", Files.exists(path));
    }

    @Test
    public void testUnsupportedFileType() {
        FileSystemWatcher fileMonitor = new FileSystemWatcher(TEST_DIR);
        fileMonitor.watchDirectory();

        Path unsupportedFilePath = Paths.get(UNSUPPORTED_FILE_PATH);
        assertFalse("Unsupported file should not exist", Files.exists(unsupportedFilePath));
    }
}
