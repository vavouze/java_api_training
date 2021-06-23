package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Launcher
{
    public static void main(String[] args)
    {
        try {
            InitServer initialServer = new InitServer(Integer.parseInt(args[0]));
            final HttpServer server = HttpServer.create(new InetSocketAddress(initialServer.getPort()), 0);
            initialServer.launchServer(server);
            if (args.length > 1)
            {
                initialServer.sendRequest(args[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
