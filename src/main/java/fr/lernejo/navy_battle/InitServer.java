package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
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
        logger.info(" Server started on port "+this.port);
    }
}
