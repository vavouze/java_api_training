package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

    final InitServer initial = new InitServer(8080);

    @Test
    void createContextPing() throws Exception {
        final HttpServer server = HttpServer.create(new InetSocketAddress(initial.getPort()), 0);
        initial.launchServer(server);
        new PingRoute().createContext(server);
    }
}
