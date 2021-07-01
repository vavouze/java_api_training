package fr.lernejo.navy_battle.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.Boat;
import fr.lernejo.navy_battle.json.JsonResponseScheme;
import fr.lernejo.navy_battle.server.BoatList;
import fr.lernejo.navy_battle.server.InitServer;
import fr.lernejo.navy_battle.server.RouteInterface;
import fr.lernejo.navy_battle.ShipState;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireRoute implements RouteInterface {

    @Override
    public void createContext(HttpServer server, InitServer initServer) {
        server.createContext("/api/game/fire",exchange -> {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                String cellParams =  getQueryParameters(exchange.getRequestURI().getQuery()).get("cell");
                if (cellParams == null) {
                    new Route404().return400(exchange);
                }
                try {if (initServer.getBoatList().getBoatList().size() > 0 ){ checkBoatList(exchange,initServer.getBoatList(),cellParams,initServer);}} catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
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
    public void checkBoatList(HttpExchange exchange, BoatList boatlist, String cellParams,InitServer initServer) throws IOException, InterruptedException {
        ShipState state = ShipState.MISS;
        for (Map.Entry<Boat,Integer> entry: boatlist.getBoatList().entrySet()) {
            List<String> boatCases = entry.getKey().getCasesList();
            if (boatCases.contains(cellParams)) {
                boatCases.remove(cellParams);
                if (boatCases.size() == 0){
                    state = ShipState.SUNK;
                    boatlist.getBoatList().remove(entry.getKey());
                }else { state = ShipState.HIT; }
                break; } }
        sendJsonResponse(exchange,!boatlist.getBoatList().isEmpty(),state);
        if (boatlist.getBoatList().size() > 0 ){
            new Route404().Shoot(initServer);
        }else {System.out.println("vous avez perdu");}}

    public void sendJsonResponse(HttpExchange exchange,boolean shipleft,ShipState state) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", String.format("application/json; charset=%s", StandardCharsets.UTF_8));
        exchange.sendResponseHeaders(202, 0);
        System.out.println("state: " + state.state + " Shipleft: " +shipleft);
        JsonResponseScheme response = new JsonResponseScheme(state.state,shipleft);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(response);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(jsonString.getBytes());
        }
    }
}
