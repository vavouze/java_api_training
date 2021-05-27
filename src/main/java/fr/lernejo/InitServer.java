package fr.lernejo;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class InitServer
{
    private final int port;

    Logger logger
        = Logger.getLogger(
        InitServer.class.getName());

    public InitServer(int port)
    {
        this.port = port;
    }
    public void launchServer() throws Exception
    {
        final HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);
        server.setExecutor(Executors.newFixedThreadPool(1));
        server.createContext("/ping", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "OK";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });
        server.createContext("/api/game/start",new NavyStart());
        server.start();
        logger.info(" Server started on port "+this.port);
    }
}
