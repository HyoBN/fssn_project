package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class lec_06_prg_08_rest_client_v3 {

    private static final String BASE_URL = "http://127.0.0.1:8080/membership_api/";

    public static void main(String[] args) {
        // Reads a non registered member: error-case
        sendGetRequest("0001");

        // Creates a new registered member: non-error case
        sendPostRequest("0001", "apple");

        // Reads a registered member: non-error case
        sendGetRequest("0001");

        // Creates an already registered member: error case
        sendPostRequest("0001", "xpple");

        // Updates a non registered member: error case
        sendPutRequest("0002", "xrange");

        // Updates a registered member: non-error case
        sendPostRequest("0002", "xrange");

        sendPutRequest("0002", "orange");

        // Delete a registered member: non-error case
        sendDeleteRequest("0001");

        // Delete a non registered member: non-error case
        sendDeleteRequest("0001");
    }

    private static void sendGetRequest(String memberId) {
        try {
            URL url = new URL(BASE_URL + memberId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("# Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("# JSON Result: " + response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendPostRequest(String memberId, String data) {
        try {
            URL url = new URL(BASE_URL + memberId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = (memberId+"=" + data).getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("# Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("# JSON Result: " + response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendPutRequest(String memberId, String data) {
        try {
            URL url = new URL(BASE_URL + memberId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = (memberId+"=" + data).getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("# Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("# JSON Result: " + response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendDeleteRequest(String memberId) {
        try {
            URL url = new URL(BASE_URL + memberId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            System.out.println("# Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("# JSON Result: " + response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}