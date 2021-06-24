package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.route.PingRoute;
import fr.lernejo.navy_battle.server.InitServer;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

class RouteTest {

    final InitServer initial = new InitServer(8080);

    @Test
    void createContextPing() throws Exception {
        final HttpServer server = HttpServer.create(new InetSocketAddress(initial.getPort()), 0);
        initial.launchServer(server);
        new PingRoute().createContext(server);
    }
}
