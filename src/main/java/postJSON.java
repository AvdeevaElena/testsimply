
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;

public class postJSON {

    static HttpServer server;

    postJSON() throws IOException{
        createAndStartServer();
    }

    static void createAndStartServer() throws IOException {

        int port = 8080;
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/",new MyHandler());
        server.setExecutor(null);
        server.start();

    }

    static class MyHandler implements HttpHandler {


        public void handle(HttpExchange he) throws IOException {

            JSONObject jsonOut = new JSONObject();
            JSONObject jsonIn = getJSON.readJsonFromUrl("http://94.140.216.17:8888/");

            jsonOut.put("Zone1", jsonIn.get("Zone1").toString());
            jsonOut.put("Zone2", jsonIn.get("Zone2").toString());
            jsonOut.put("Zone3", jsonIn.get("Zone3").toString());

            he.sendResponseHeaders(200, jsonOut.toString().length());
            OutputStream os = he.getResponseBody();
            os.write(jsonOut.toString().getBytes());
            os.close();


           // he.getRequestURI().getPath();
            System.out.println(he.getRequestURI().getPath());
        }

    }

    public static void main(String[] args) throws IOException {

        int port = 8080;
        server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/",new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println(server.getAddress().getPort());
        System.out.println(server.getAddress().getAddress().isAnyLocalAddress());
          }



}

