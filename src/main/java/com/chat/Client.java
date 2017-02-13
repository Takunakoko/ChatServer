package com.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by takunaka on 13.02.17.
 */
public class Client implements Runnable {
    private InputStream instr;
    private OutputStream outStr;

    public Client(InputStream instr, OutputStream outStr) {
        this.instr = instr;
        this.outStr = outStr;
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
                } catch (IOException e) {

                }
            }
        }
    }

    public boolean isClosed(){
        try {
            return instr.available() == 0;
        } catch (IOException e) {
            return false;
        }
    }
}
