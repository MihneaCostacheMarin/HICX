package com.hicx.fileparser.file;

public class FileStatistics {
    private int wordCount;
    private int dotCount;
    private String mostFrequentWord;

    public FileStatistics(int wordCount, int dotCount, String mostFrequentWord) {
        this.wordCount = wordCount;
        this.dotCount = dotCount;
        this.mostFrequentWord = mostFrequentWord;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getDotCount() {
        return dotCount;
    }

    public String getMostFrequentWord() {
        return mostFrequentWord;
    }

    @Override
    public String toString() {
        return "FileStatistics{" +
                "wordCount=" + wordCount +
                ", dotCount=" + dotCount +
                ", mostFrequentWord='" + mostFrequentWord + '\'' +
                '}';
    }
}
