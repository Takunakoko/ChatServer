package com.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by takunaka on 13.02.17.
 */
public class Client implements Runnable {
    private InputStream instr;
    private OutputStream outStr;
    private Socket sket;

    public Client(Socket s) throws IOException {
        this.instr = s.getInputStream();
        this.outStr = s.getOutputStream();
        this.sket = s;
    }


    public void run() {
        Scanner sc = new Scanner(instr);
        PrintWriter pw = new PrintWriter(outStr);

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            System.out.println(line);


            if (line.equals("closeConnection")){
                try {
                    instr.close();
                    outStr.close();
                    sket.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public boolean isClosed(){
        return sket.isClosed();
    }
}
