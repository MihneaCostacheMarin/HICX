package com.hicx.fileparser.file;

import com.hicx.fileparser.main.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileProcessor {
    private File file;

    public FileProcessor(File file) {
        this.file = file;
    }

    public FileStatistics processFile() {

        FileStatistics fileStatistics = calculateFileStatistics();

        return fileStatistics;
    }

    private FileStatistics calculateFileStatistics() {
        int wordCount = 0;
        int dotCount = 0;

        Map<String, Integer> wordFreq = new HashMap<>();
        String mostFreqWord = "";
        int maxFreq = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;

                for(String word : words) {
                    dotCount += word.length() - word.replace(".", "").length();
                    word = word.replace(".", "");

                    int frequency = wordFreq.getOrDefault(word, 0) + 1;
                    wordFreq.put(word, frequency);

                    if(frequency > maxFreq) {
                        mostFreqWord = word;
                        maxFreq = frequency;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new FileStatistics(wordCount, dotCount, mostFreqWord);
    }

    public void printStatistics() {
        FileStatistics fileStatistics = processFile();
        if(Objects.nonNull(fileStatistics)) {
            System.out.println(fileStatistics);
        } else {
            System.out.println("Statistics cannot be displayed");
        }
    }

    public void moveToProcessed() {
        String processedDirPath = file.getPath().substring(0, file.getPath().lastIndexOf(File.separator) + 1) + Main.PROCESSED_FOLDER;
        File processedDirectory = new File(processedDirPath);

        if (!processedDirectory.exists()) {
            processedDirectory.mkdir();
        }

        File originalFile = new File(file.getPath());
        File processedFile = new File(processedDirPath + File.separator + file.getName());

        originalFile.renameTo(processedFile);
    }
}
