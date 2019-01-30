package com.sipi.groupOne;

import com.sipi.groupOne.ui.MainFrame;
import java.io.IOException;
import org.jibble.pircbot.*;

public class BotProject extends PircBot {

    private MainFrame ui;
    
    public BotProject(String server, String nickname, String channel) {
        // Launching GUI
        ui = new MainFrame(this);
        ui.run();
        
        // Setup and start bot
        this.setName(nickname);
        this.setAutoNickChange(true);
        this.setLogin("botProject");
        this.setVerbose(true);
        try {
            this.connect(server);
        } catch (IrcException e) {
            System.err.println("IRC Exception: \n" + e.getLocalizedMessage());
        } catch (IOException e) {
            System.err.println("IO Exception: \n" + e.getLocalizedMessage());
        }
        this.joinChannel(channel);
    }

    // Scanning the chat in the channel for commands
    @Override
    public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
        // Getting the result
        String searchResult = ChannelScreener.message(sender, message);

        // Checking that the answer is null, if it is it's not a command for Anna and she shouldn't do a thing
        if (searchResult != null) {
            sendMessage(channel, searchResult);
        }
    }

    @Override
    protected void onUserList(String channel, User[] users) {
        ui.updateUserList();
    }
    
    
}
