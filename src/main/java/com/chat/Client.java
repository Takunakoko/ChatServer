package com.chat;

import com.chat.db.UsersDAO;
import com.chat.db.UsersEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by takunaka on 13.02.17.
 */
public class Client implements Runnable {
    private InputStream instr;
    private OutputStream outStr;
    private Socket sket;
    private PrintWriter pw;


    public Client(Socket s) throws IOException {
        this.instr = s.getInputStream();
        this.outStr = s.getOutputStream();
        this.sket = s;
    }


    public void run() {
        Scanner sc = new Scanner(instr);
        pw = new PrintWriter(outStr, true);

        String login = "";
        boolean firstEnter = true;
        pw.println("Введите логин: ");

        while (sc.hasNextLine()){
            if(firstEnter) {

                login = sc.nextLine();
                pw.println("Добро пожаловать в чат! Ваш логин : " + login + ". Для отключения введите \"Exit\"." + "\n");
                firstEnter = false;
            }
            String line = sc.nextLine();
            System.out.println(login + " : " + line);
            Server.sendMessageToChat(line, login);

            if (line.equals("Exit")){
                try {
                    instr.close();
                    outStr.close();
                    sket.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public void reciveMessageFromChat(String login, String message) {
        pw.println(login + ": " + message);
        pw.flush();

    }
    public boolean auth(String login, String pass) {
        UsersEntity ue = new UsersEntity();

        try {
            ue = UsersDAO.getUser(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

         if (ue.getPassword().hashCode() == pass.hashCode()) return true;

        return false;

    }

    public boolean isClosed(){
        return sket.isClosed();
    }
}
