package com.sipi.groupOne;

import java.io.IOException;
import org.jibble.pircbot.*;

public class BotProject extends PircBot {

    public BotProject() throws IOException {
        this.setName("Anna");
        this.setVerbose(true);
        try {
            this.connect("port80a.se.quakenet.org");
        } catch (IrcException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        this.joinChannel("#group1-lernia");
    }

    // Scanning the chat in the channel for commands
    @Override
    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {
        // Getting the result
        String searchResult = Direct.message(sender,message);

        // Checking that the answer is null, if it is it's not a command for Anna and she shouldn't do a thing
        if(searchResult == null || searchResult.isEmpty()) {
        } else {
            sendMessage(channel, searchResult);
        }
    }
}