package fr.lernejo.navy_battle.route;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.server.InitServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Route404
{

    public void return404(HttpExchange exchange) throws IOException {
        String response = "Not Found";
        exchange.sendResponseHeaders(404,response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public void return400(HttpExchange exchange) throws IOException {
        String response = "Bad Request";
        exchange.sendResponseHeaders(400,response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public void Shoot(InitServer initServer) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requeteGet = HttpRequest.newBuilder().uri(URI.create(initServer.getAdversary().get(0) +"/api/game/fire?cell="+initServer.getRandomCase())).setHeader("accept", "application/json").setHeader("Content-Type", "application/json").GET().build();
        HttpResponse<String> servResponse = client.send(requeteGet, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(servResponse.body());
        if (!actualObj.get("shipLeft").asBoolean()){
            System.out.println("vous avez gagné");
        }
    }
}
