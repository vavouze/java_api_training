package fr.lernejo;

public class Launcher
{
    public static void main(String[] args)
    {
        InitServer server = new InitServer(9876);
        try {
            server.launchServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
