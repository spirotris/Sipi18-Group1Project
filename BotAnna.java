import org.jibble.pircbot.*;
import java.util.*;
import java.lang.*;

// Taget från demot på http://www.jibble.org/pircbot.php
// Bara döpt om klassen

public class BotAnna extends PircBot {

    // Nog måste väl boten heta Anna?
    public BotAnna() {
        this.setName("Anna");
    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {
        if (message.equalsIgnoreCase("time")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }
    }

}