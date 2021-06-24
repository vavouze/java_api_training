package fr.lernejo.navy_battle.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.json.JsonResponseScheme;
import fr.lernejo.navy_battle.server.RouteInterface;
import fr.lernejo.navy_battle.ShipState;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FireRoute implements RouteInterface {

    @Override
    public void createContext(HttpServer server)
    {
        server.createContext("/api/game/fire",exchange -> {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                String cellParams =  getQueryParameters(exchange.getRequestURI().getQuery()).get("cell");
                System.out.println(cellParams);
                if (cellParams == null) {
                    new Route404().return400(exchange);
                }
                sendJsonResponse(exchange,server);
            }
            else {
                new Route404().return404(exchange);
            }
        });}
    public Map<String, String> getQueryParameters(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }
    public void sendJsonResponse(HttpExchange exchange, HttpServer server) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", String.format("application/json; charset=%s", StandardCharsets.UTF_8));
        exchange.sendResponseHeaders(202, 0);
        JsonResponseScheme response = new JsonResponseScheme(ShipState.MISS,true);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(response);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(jsonString.getBytes());
        }
    }
}
