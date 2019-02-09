package com.sipi.groupOne;

import com.sipi.groupOne.ui.MainFrame;
import java.io.IOException;
import org.jibble.pircbot.*;

public class BotProject extends PircBot implements Runnable {

    private MainFrame ui;
    private String server;
    private String channel;

    public BotProject(String server, String nickname, String channel, MainFrame ui) {
        this.ui = ui;
        this.server = server;
        this.channel = channel;
        this.setName("[GUI]Anna");
        this.setAutoNickChange(true);
        this.setLogin("botProject");
        this.setVerbose(true);
    }

    @Override
    public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
        String searchResult = ChannelScreener.message(sender, message);
        ui.getTextArea().append(message);
        if (searchResult != null) {
            sendMessage(channel, searchResult);
        }
    }

    @Override
    protected void onUserList(String channel, User[] users) {
        ui.updateUserList();
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {
        ui.updateUserList();
    }

    
    
    @Override
    public void run() {
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
