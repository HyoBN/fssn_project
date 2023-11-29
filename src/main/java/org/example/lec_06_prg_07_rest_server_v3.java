package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class lec_06_prg_07_rest_server_v3 {

    private static Map<String, String> database = new HashMap<>();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/membership_api", new MembershipHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server is running on port 8080");
    }

    static class MembershipHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String[] pathParts = path.split("/");
            String memberId = pathParts[pathParts.length - 1];
            String method = exchange.getRequestMethod();
            String response = "";

            switch (method) {
                case "GET":
                    response = read(memberId);
                    break;
                case "POST":
                    String value = getValueFromRequest(exchange);
                    response = create(memberId, value);
                    break;
                case "PUT":
                    value = getValueFromRequest(exchange);
                    response = update(memberId, value);
                    break;
                case "DELETE":
                    response = delete(memberId);
                    break;
            }
            System.out.println();

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private String create(String memberId, String value) {
            if (database.containsKey(memberId)) {
                return "{\"" + memberId + "\": \"None\"}";
            } else {
                database.put(memberId, value);
                return "{\"" + memberId + "\": \"" + database.get(memberId) + "\"}";
            }
        }

        private String read(String memberId) {
            return "{\"" + memberId + "\": \"" + database.getOrDefault(memberId, "None") + "\"}";
        }

        private String update(String memberId, String value) {
            if (database.containsKey(memberId)) {
                database.put(memberId, value);
                return "{\"" + memberId + "\": \"" + database.get(memberId) + "\"}";
            } else {
                return "{\"" + memberId + "\": \"None\"}";
            }
        }

        private String delete(String memberId) {
            if (database.containsKey(memberId)) {
                database.remove(memberId);
                return "{\"" + memberId + "\": \"Removed\"}";
            } else {
                return "{\"" + memberId + "\": \"None\"}";
            }
        }
        private String getValueFromRequest(HttpExchange exchange) throws IOException {
            // For simplicity, assuming the value is sent as a query parameter

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "utf-8"))) {
                StringBuilder value = new StringBuilder();
                String inputLine;

                while ((inputLine = reader.readLine()) != null) {
                    value.append(inputLine);
                }

                return value.toString();
            }
        }
    }
}