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
        // Cleaning up the chat to find a command to process
        String command = message.substring(0, message.indexOf(" "));
        String searchValue = message.substring(message.indexOf(" "));
        if (command == "time") {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        } else if(command == "movie") {
            // To call the omdb api and get a movie-title
            //Movie movie = new Movie(searchValue.trim());
        }
    }
}