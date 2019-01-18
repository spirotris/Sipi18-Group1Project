package com.GroupOne;
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

    @Override
    public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
        if (message.equalsIgnoreCase("time")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }
    }

}
