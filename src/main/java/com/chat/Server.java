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
    private static List<Client> clientList = new ArrayList<Client>();

    static {
        Thread clientCheker = new Thread(new ClientChecker());
        clientCheker.start();
    }

    public static ServerSocket createConnection(int port) throws IOException {
        return new ServerSocket(port);
    }

    public static void handleClient(Socket s) throws IOException {
        Client client = new Client(s);
        new Thread(client).start();
        clientList.add(client);
    }

    public static void sendMessageToChat(String message, String login) {
        for (Client c : clientList){
            c.reciveMessageFromChat(login, message);
        }
    }

    private static class ClientChecker implements Runnable {

        public void run() {
            while (true){
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {

                }
                Iterator it = clientList.iterator();
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
