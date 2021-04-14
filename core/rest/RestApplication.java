package core.rest;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;

public class RestApplication extends Application {

    public static void main (String [] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 25);
        HttpContext context = server.createContext("/resources");
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new RestApplication(), HttpHandler.class);
        context.setHandler(handler);
    }
}
