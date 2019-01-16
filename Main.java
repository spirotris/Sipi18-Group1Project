import org.jibble.pircbot.*;
import BotAnna.*;
import java.util.*;
import java.lang*;

// Inklistrat från http://www.jibble.org/pircbot.php

public class Main {

    public static void main(String[] args) throws Exception {

        // Now start our bot up.
        Bot bot = new Bot();

        // Enable debugging output.
        bot.setVerbose(true);

        // Connect to the IRC server.
        bot.connect("irc.freenode.net");

        // Join the #pircbot channel.
        bot.joinChannel("#pircbot");  // Ska vi skapa en egen channel eller hur fungerar IRC?

    }

}