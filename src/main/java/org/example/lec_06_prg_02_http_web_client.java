package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class lec_06_prg_02_http_web_client {

    public static void main(String[] args) {
        System.out.println("## HTTP client started.");

        try {
            // GET request for http://localhost:8080/temp/
            System.out.println("## GET request for http://localhost:8080/temp/");
            String getResponse1 = sendGetRequest("http://localhost:8080/temp/");
            System.out.println("## GET response [start]");
            System.out.println(getResponse1);
            System.out.println("## GET response [end]");

            // GET request for http://localhost:8080/?var1=9&var2=9
            System.out.println("## GET request for http://localhost:8080/?var1=9&var2=9");
            String getResponse2 = sendGetRequest("http://localhost:8080/?var1=9&var2=9");
            System.out.println("## GET response [start]");
            System.out.println(getResponse2);
            System.out.println("## GET response [end]");

            // POST request for http://localhost:8080/ with var1 is 9 and var2 is 9
            System.out.println("## POST request for http://localhost:8080/ with var1 is 9 and var2 is 9");
            String postResponse = sendPostRequest("http://localhost:8080", "var1=9&var2=9");
            System.out.println("## POST response [start]");
            System.out.println(postResponse);
            System.out.println("## POST response [end]");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("## HTTP client completed.");
    }

    private static String sendGetRequest(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Read the response
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            return response.toString();
        }
    }

    private static String sendPostRequest(String url, String parameters) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        // Set the request method to POST
        connection.setRequestMethod("POST");

        // Enable input/output streams
        connection.setDoOutput(true);

        // Send POST request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = parameters.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Read the response
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            return response.toString();
        }
    }
}