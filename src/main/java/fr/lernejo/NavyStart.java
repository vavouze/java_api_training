package fr.lernejo;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class NavyStart implements HttpHandler
{

    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST"))
        {

        }
    }
}
