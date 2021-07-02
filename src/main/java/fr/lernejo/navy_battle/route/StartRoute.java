package fr.lernejo.navy_battle.route;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.json.JsonBodyScheme;
import fr.lernejo.navy_battle.server.InitServer;
import fr.lernejo.navy_battle.server.RouteInterface;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


public class StartRoute implements RouteInterface {

    @Override
    public void createContext(HttpServer server, InitServer initServer) {
        server.createContext("/api/game/start",exchange -> {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                String bodySTr = new String(exchange.getRequestBody().readAllBytes(),StandardCharsets.UTF_8);
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode actualObj = mapper.readTree(bodySTr);
                    initServer.getAdversary().add(actualObj.get("url").asText());
                    sendJsonResponse(exchange,server);
                    new Route404().Shoot(initServer);
                }catch (Exception e) {
                    new Route404().return400(exchange);}
            } else {
                new Route404().return404(exchange);}});}

    public void sendJsonResponse(HttpExchange exchange, HttpServer server) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", String.format("application/json; charset=%s", StandardCharsets.UTF_8));
        exchange.sendResponseHeaders(202, 0);
        JsonBodyScheme response = new JsonBodyScheme("2","http://localhost:"+server.getAddress().getPort(),"May the best code win");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(response);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(jsonString.getBytes());
        }
    }

}
