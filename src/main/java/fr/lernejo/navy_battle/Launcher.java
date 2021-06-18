package fr.lernejo.navy_battle;

public class Launcher
{
    public static void main(String[] args)
    {
        try {
            InitServer server = new InitServer(Integer.parseInt(args[0]));
            server.launchServer();
            if (args.length > 1)
            {
                server.sendRequest(args[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
