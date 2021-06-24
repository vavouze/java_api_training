package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;

public interface RouteInterface
{
    void createContext(HttpServer server);
}
