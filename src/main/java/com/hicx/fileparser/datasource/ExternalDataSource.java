package com.hicx.fileparser.datasource;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ExternalDataSource implements DataSource {
    private final String url;
    private boolean hasData = true;

    public ExternalDataSource(String url) {
        this.url = url;
    }

    @Override
    public boolean hasNext() {
        if (!hasData) {
            return false;
        }

        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            hasData = connection.getResponseCode() == 200;
            return hasData;
        } catch (IOException e) {
            System.out.println("Error checking for data from URL: " + url);
            return false;
        }
    }

    @Override
    public byte[] next() {
        if (!hasNext()) {
            return new byte[0];
        }

        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
                builder.append("\n");
            }
            scanner.close();

            return builder.toString().getBytes();
        } catch (IOException e) {
            System.out.println("Error retrieving data from URL: " + url);
            return new byte[0];
        }
    }
}