package com.sipi.groupOne;

import com.sipi.groupOne.help.Helper;
import com.sipi.groupOne.movie.FetchMovieInfo;

public class ChannelScreener {

    public static String message(String sender, String msg) {
        // Cleaning up the chat.message to find a command to process
        // Split the message to an array of the words to extract the command
        String[] msgArray = msg.split(" ");
        switch (msgArray[0].toLowerCase()) {
            case "time":
                String time = new java.util.Date().toString();
                return sender + ": The time is now " + time;
            case "movie":
                // To call the omdb api and get some movie-info
                FetchMovieInfo movie = new FetchMovieInfo(sender, searchString(msgArray));
                return movie.getAnswer();
            case "serie":
                return "Hej " + sender + "! Du sökte på " + msgArray[0]+ " och skrev: " + searchString(msgArray);
            case "help":
                Helper helper = new Helper();
                return "Hej " + sender + "! " + helper.getAnswer();
        }

        return null;
    }

    // Generates a string with the searchterm from the message and returning it
    // Jumping over the first word since it is the command
    private static String searchString(String[] searchArr) {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < searchArr.length; i++) {
            if((searchArr.length -1) != i)
                str.append(searchArr[i] + " ");
            else
                str.append(searchArr[i]);
        }
        return str.toString();
    }
}
