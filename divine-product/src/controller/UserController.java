package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.User;
import repo.UserRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UserController implements HttpHandler {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        InputStream body = exchange.getRequestBody();
        String response = "";
        int statusCode = 200;

        try {
            switch (method) {
                case "GET":
                    response = repository.findAll().toString();
                    break;

                case "POST":
                    // Simplified: In a real app, you'd parse JSON from exchange.getRequestBody()
                    String string = body.toString();
                    repository.save(new User(0, "New", "User", 25));
                    response = "User added successfully";
                    break;

                case "PUT":
                    // Logic to update (Example: updates user ID 1)
                    repository.update(1, new User(1, "Updated", "Name", 30));
                    response = "User 1 updated";
                    break;

                case "DELETE":
                    // Logic to delete (Example: deletes user ID 1)
                    repository.delete(1);
                    response = "User 1 deleted";
                    break;

                default:
                    statusCode = 405;
                    response = "Method not supported";
            }
        } catch (Exception e) {
            statusCode = 500;
            response = "Server Error: " + e.getMessage();
        }

        sendResponse(exchange, response, statusCode);
    }

    private void sendResponse(HttpExchange exchange, String response, int code) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
