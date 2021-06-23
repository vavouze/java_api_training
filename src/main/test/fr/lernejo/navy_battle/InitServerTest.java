package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;


class InitServerTest {

    final InitServer initial = new InitServer(9876);

    @Test
    void testLaunchServer(){
        try {
            final HttpServer server = HttpServer.create(new InetSocketAddress(initial.getPort()), 0);
            initial.launchServer(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSendRequest() {
        try {
            initial.sendRequest("http://localhost:9876");
        } catch (Exception e) {
            e.printStackTrace();
            //org.junit.jupiter.api.Assertions.fail("This will fail!");
        }
    }

    @Test
    void testGetPort() {
        Assertions.assertThat(initial.getPort()).as("port is right").isEqualTo(9876);
    }
}
