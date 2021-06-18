package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
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

        List<RouteInterface> routeList = new ArrayList<>();
        routeList.add(new PingRoute());
        routeList.add(new StartRoute());

        for (RouteInterface route: routeList)
        {
            route.createContext(server);
        }
        server.start();
        logger.info(" Server started on port "+ this.port);
    }

    public void sendRequest(String adversaryUrl) throws Exception
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"hello\"}"))
            .build();
        HttpResponse<String> servResponse = client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        logger.info("contacted server: " + adversaryUrl);
        System.out.println(servResponse);
    }
}
