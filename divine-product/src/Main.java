import com.sun.net.httpserver.HttpServer;
import controller.UserController;
import repo.UserRepository;

import java.net.InetSocketAddress;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Manually instantiate and link layers (Dependency Injection)
        UserRepository repository = new UserRepository();
        UserController controller = new UserController(repository);

        // 2. Create the HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(6060), 0);

        // 3. Map the context path "/users" to our controller
        server.createContext("/users", controller);

        // 4. Start the server
        server.setExecutor(null); // creates a default executor
        System.out.println("Server started on port 6060...");
        System.out.println("Visit http://localhost:6060/users to see results.");
        server.start();
    }
}