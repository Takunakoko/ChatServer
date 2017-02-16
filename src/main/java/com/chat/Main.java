package com.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by takunaka on 13.02.17.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket sk = Server.createConnection(8080);
        System.out.println("Server initialized");
        while (true){
            Socket accept = sk.accept();
            Server.handleClient(accept);
        }
    }
}
