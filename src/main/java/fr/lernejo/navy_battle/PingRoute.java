package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;

public class PingRoute implements RouteInterface
{
    @Override
    public void createContext(HttpServer server)
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
