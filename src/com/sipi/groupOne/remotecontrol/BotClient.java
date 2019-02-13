package com.sipi.groupOne.remotecontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class BotClient {

    public void start(int port) throws IOException, InterruptedException {
        Socket clientSocket = new Socket("127.0.0.1", port);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine = "";
        while (true) {
            inputLine = in.readLine();
            if(!inputLine.equals(in.readLine())) {
             System.out.println(inputLine);   
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new BotClient().start(6668);
    }
}
