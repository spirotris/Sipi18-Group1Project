package com.sipi.groupOne;

import com.sipi.groupOne.ui.MainFrame;
import java.io.IOException;
import org.jibble.pircbot.*;

public class BotProject extends PircBot {

    private MainFrame ui;
    
    public BotProject(String server, String nickname, String channel) {
        ui = new MainFrame(this);
        ui.run();
        
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

    @Override
    public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
        String searchResult = ChannelScreener.message(sender, message);

        if (searchResult != null) {
            sendMessage(channel, searchResult);
        }
    }

    @Override
    protected void onUserList(String channel, User[] users) {
        ui.updateUserList();
    }
    
    
}
