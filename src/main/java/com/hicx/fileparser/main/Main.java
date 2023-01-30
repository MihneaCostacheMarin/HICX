package com.hicx.fileparser.main;

public class Main {
    public static final String PROCESSED_FOLDER = "processed";

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Error: Please provide a directory location!");
            return ;
        }

        String dirPath = args[0];
        System.out.println("DirPath: " + dirPath);
    }
}
