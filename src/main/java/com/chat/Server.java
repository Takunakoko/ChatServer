package com.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by takunaka on 13.02.17.
 */
public class Server {
    private static List<Client> list = new ArrayList<Client>();


    public static ServerSocket createConnection(int port) throws IOException {
        return new ServerSocket(port);
    }

    public static void handleClient(Socket s) throws IOException {
        Client client = new Client(s.getInputStream(), s.getOutputStream());
        new Thread(client).start();
        list.add(client);
    }
    private static class ClientChecker implements Runnable {

        public void run() {
            while (true){
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {

                }
                Iterator it = list.iterator();
                while (it.hasNext()){
                    Client c = (Client) it.next();
                    if (c.isClosed()){
                        it.remove();
                    }
                }
            }
        }
    }

}
