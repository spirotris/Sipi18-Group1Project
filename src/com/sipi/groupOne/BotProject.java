package com.sipi.groupOne;

import com.sipi.groupOne.ui.MainFrame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jibble.pircbot.*;

public class BotProject extends PircBot implements Runnable {

    private MainFrame ui;
    private String server;
    private String channel;

    public BotProject(String server, String nickname, String channel, Boolean saveLog) {
        this.server = server;
        this.channel = channel;
        this.setName(nickname);
        this.setLogin("botProj");
        this.setAutoNickChange(true);
        this.setVerbose(true);
        if (saveLog) {
            try {
                String todaysDate = new SimpleDateFormat("d.M").format(new Date());
                String fileName = "irclog-" + todaysDate + ".log";
                PrintStream fs = new PrintStream(fileName);
                System.setOut(fs);
            } catch (FileNotFoundException e) {
                System.err.println("Unable to create logfile.");
            }
        }
    }

    @Override
    public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
        String searchResult = ChannelScreener.message(sender, hostname, message);
        ui.setChatMessage(sender, message);
        if (searchResult != null) {
            sendMessage(channel, searchResult);
            ui.setChatMessage(this.getNick(), searchResult);
        }
    }

    public void setUi(MainFrame ui) {
        this.ui = ui;
    }

    public String getChannel() {
        return channel;
    }

    @Override
    protected void onUserList(String channel, User[] users) {
        ui.updateUserList();
        ui.setName(getNick() + " (" + this.getNick() + ") in " + channel);
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {
        ui.updateUserList();
        if (sender.equals(this.getNick())) {
            ui.setMessage("joined " + channel);
        } else {
            ui.setMessage(sender + " has joined the channel");
        }

    }

    @Override
    protected void onTopic(String channel, String topic) {
        ui.setTopic(channel, topic);
    }

    @Override
    protected void onPart(String channel, String sender, String login, String hostname) {
        ui.updateUserList();
        ui.setMessage(sender + "has left the channel");
    }

    @Override
    protected void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        ui.updateUserList();
        ui.setMessage(sourceNick + " has quit irc (" + reason + ")");
    }

    @Override
    protected void onDisconnect() {
        ui.setMessage("Disconnected");
    }

    @Override
    public void run() {
        ui.setMessage("Attempting connection to server...");
        try {
            this.connect(server);
        } catch (IrcException e) {
            System.err.println("IRC Exception: \n" + e.getLocalizedMessage());
        } catch (IOException e) {
            System.err.println("IO Exception: \n" + e.getLocalizedMessage());
        }
        this.joinChannel(channel);
    }

}
