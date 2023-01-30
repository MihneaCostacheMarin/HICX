package com.hicx.fileparser.file;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileProcessor {
    private File file;

    public FileProcessor(File file) {
        this.file = file;
    }

    public FileStatistics processFile() {
        Tika tika = new Tika();
        try {
            String fileContent = tika.parseToString(new java.io.File(new File(file.getFilePath()) + "/" + file.getFileName()));
            FileStatistics fileStatistics = calculateFileStatistics();

            return fileStatistics;
        } catch (TikaException e) {
            System.err.println("Error processing file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }

        return null;
    }

    private FileStatistics calculateFileStatistics() {
        int wordCount = 0;
        int dotCount = 0;

        Map<String, Integer> wordFreq = new HashMap<>();
        String mostFreqWord = "";
        int maxFreq = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(new File(file.getFilePath()))))) {
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
            fileStatistics.toString();
        } else {
            System.out.println("Statistics cannot be displayed");
        }
    }
}
