package fr.lernejo.navy_battle.route;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.server.InitServer;
import fr.lernejo.navy_battle.server.RouteInterface;

import java.io.OutputStream;

public class PingRoute implements RouteInterface
{
    @Override
    public void createContext(HttpServer server, InitServer initServer)
    {
        server.createContext("/ping", exchange -> {
            String response = "OK";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
    }
}
