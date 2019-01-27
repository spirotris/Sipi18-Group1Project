package com.sipi.groupOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import org.jibble.pircbot.*;

public class BotProject extends PircBot {

    private PrintStream ps;

    public BotProject(String server, String nickname, String channel, boolean log) {
        if (log) {
            try {
                System.setOut(new PrintStream(new File("logs/log.txt")));
            } catch (FileNotFoundException e) {
                System.err.println("Unable to create logfile");
                System.err.println(e.getLocalizedMessage());
            }
        }

        this.setName(nickname);
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
}
