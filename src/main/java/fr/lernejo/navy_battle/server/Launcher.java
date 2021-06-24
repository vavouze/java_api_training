package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.Boat;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Launcher
{
    public static void main(String[] args)
    {
        try {
            HashMap<Boat,Integer> BoatList = new HashMap<>();
            BoatList.put(new Boat(new ArrayList<>(),"porte-avion"),5);
            BoatList.put(new Boat(new ArrayList<>(),"croiseur"),4);
            BoatList.put(new Boat(new ArrayList<>(),"destroyer_1"),3);
            BoatList.put(new Boat(new ArrayList<>(),"destroyer_2"),3);
            BoatList.put(new Boat(new ArrayList<>(),"torpilleur"),2);
            BoatList listOfBoats = new BoatList(BoatList);
            listOfBoats.initiateAllboats();
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
