package fr.lernejo.navy_battle.server;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.route.FireRoute;
import fr.lernejo.navy_battle.route.PingRoute;
import fr.lernejo.navy_battle.route.StartRoute;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

public class InitServer
{
    private final int port;
    private final List<String> Adversary;
    private final BoatList BoatList;
    private final List<String> alreadyShotAt = new ArrayList<>();
    private final String[] column = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private final String[] line = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};


    public InitServer(int port, List<String> adversary, BoatList boatList)
    {
        this.port = port;
        Adversary = adversary;
        BoatList = boatList;
    }
    public int getPort() {
        return port;
    }

    public List<String> getAdversary() {
        return Adversary;
    }

    public void launchServer(HttpServer server) throws Exception {
        server.setExecutor(Executors.newFixedThreadPool(1));
        List<RouteInterface> routeList = new ArrayList<>();
        routeList.add(new PingRoute());
        routeList.add(new StartRoute());
        routeList.add(new FireRoute());

        for (RouteInterface route: routeList) {
            route.createContext(server,this);
        }
        server.start();
    }

    public void sendRequest(String adversaryUrl,long startTime) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"hello\"}"))
            .build();
        client.send(requetePost, HttpResponse.BodyHandlers.ofString());
        System.out.println("execution time: " +(System.nanoTime() - startTime) /1000000+"ms");
    }

    public BoatList getBoatList() {
        return BoatList;
    }
    public String getRandomCase(){
        this.alreadyShotAt.add("");
        String result = "";
        while (this.alreadyShotAt.contains(result)){
            int rndCol = new Random().nextInt(this.column.length);
            int rndLine = new Random().nextInt(this.line.length);
            result = this.column[rndCol] + this.line[rndLine];
        }
        this.alreadyShotAt.add(result);
        return result;
    }
}
