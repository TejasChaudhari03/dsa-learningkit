package javaprograms;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Random;

public class GuessGame {
    private static int numberToGuess = new Random().nextInt(100) + 1;

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/guess", GuessGame::handleGuess);

        server.setExecutor(null); // default executor
        server.start();

        System.out.println("GuessGame API running on http://localhost:8080");
        System.out.println("Try: http://localhost:8080/guess?number=50");
    }

    private static void handleGuess(HttpExchange exchange) throws IOException {

        String query = exchange.getRequestURI().getQuery();
        int response;

        if (query == null || !query.startsWith("number=")) {
            response = 400; // Bad Request
            sendResponse(exchange, response);
            return;
        }

        int userGuess = Integer.parseInt(query.split("=")[1]);

        if (userGuess > numberToGuess) {
            response = 1; // Too High
        } else if (userGuess < numberToGuess) {
            response = -1; // Too Low
        } else {
            response = 0; // Correct Guess
            numberToGuess = new Random().nextInt(100) + 1;
        }

        sendResponse(exchange, response);
    }

    private static void sendResponse(HttpExchange exchange, int response) throws IOException {
        exchange.sendResponseHeaders(200, String.valueOf(response).length());
        OutputStream os = exchange.getResponseBody();
        os.write(String.valueOf(response).getBytes());
        os.close();
    }
}
