package com.sipi.groupOne.remotecontrol;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

public class BotServer {
    
    private static PrintStream psOut;
    
    public void start(int port) throws IOException, InterruptedException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(BotServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("waiting for connection");
        Socket clientSocket = serverSocket.accept();
        System.out.println("connection recieved");
        
        psOut = new PrintStream(clientSocket.getOutputStream(), true);
        System.setOut(psOut);
        //BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    
    public static void main(String[] args) throws IOException {
        try {
            new BotServer().start(6668);
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
        Thread t = new Thread(new BotTest());
        t.start();
    }
    
    private static class BotTest extends PircBot implements Runnable {
        
        public BotTest() {
            this.setName("AnnaServer");
            this.setVerbose(true);
        }
        
        
        @Override
        public void run() {
            try {
                System.out.println("Connecting to IRC server..");
                this.connect("port80a.se.quakenet.org");
                this.joinChannel("#group1-lernia");
            } catch (IOException | IrcException ex) {
                Logger.getLogger(BotServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
