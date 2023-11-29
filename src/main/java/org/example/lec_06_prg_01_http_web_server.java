package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class lec_06_prg_01_http_web_server implements HttpHandler {

    private void printHttpRequestDetail(HttpExchange exchange) {
        System.out.println("::Client address   : " + exchange.getRemoteAddress());
        System.out.println("::Request method   : " + exchange.getRequestMethod());
        System.out.println("::Request URI      : " + exchange.getRequestURI());
        System.out.println("::Request headers  : " + exchange.getRequestHeaders());
    }

    private void sendHttpResponseHeader(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, 0);
    }

    private int simpleCalc(int para1, int para2) {
        return para1 * para2;
    }

    private Map<String, Integer> parameterRetrieval(String query) {
        Map<String, Integer> result = new HashMap<>();
        String[] fields = query.split("&");
        for (String field : fields) {
            String[] keyValue = field.split("=");
            result.put(keyValue[0], Integer.parseInt(keyValue[1]));
        }
        return result;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("## Handler activated.");

        // GET response header generation
        printHttpRequestDetail(exchange);
        sendHttpResponseHeader(exchange);

        // GET request for multiplication (parameter transfer through Get request)
        // ex: http://localhost:8080/?var1=9&var2=9
        if (exchange.getRequestURI().getQuery() != null) {
            // parameter retrieval and calculation
            Map<String, Integer> parameters = parameterRetrieval(exchange.getRequestURI().getQuery());
            int result = simpleCalc(parameters.get("var1"), parameters.get("var2"));

            // GET response generation
            String response = "<html><p>GET request for calculation => " +
                    parameters.get("var1") + " x " + parameters.get("var2") + " = " + result + "</p></html>";

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }

            System.out.println("## GET request for calculation => " + parameters.get("var1") + " x " +
                    parameters.get("var2") + " = " + result + ".");
        }
        // GET request for directory retrieval
        // ex: http://localhost:8080/directory_sub/
        else {
            // GET response generation
            String response = "<html><p>HTTP Request GET for Path: " + exchange.getRequestURI().getPath() + "</p></html>";

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }

            System.out.println("## GET request for directory => " + exchange.getRequestURI().getPath() + ".");
        }
    }

    public static void main(String[] args) throws IOException {
        // Main function
        int serverPort = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

        server.createContext("/", new lec_06_prg_01_http_web_server());
        server.setExecutor(null);

        System.out.println("## HTTP server started at http://localhost:" + serverPort + ".");
        server.start();
    }
}