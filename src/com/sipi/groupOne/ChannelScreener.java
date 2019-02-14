package com.sipi.groupOne;

import com.sipi.groupOne.movie.FetchMovieInfo;
import com.sipi.groupOne.gps.GetGPS;
import com.sipi.groupOne.train.TrainSearch;
import com.sipi.groupOne.tv.TVserier;

public class ChannelScreener {

    public static String message(String sender, String hostname, String msg) {
        // Cleaning up the chat.message to find a command to process
        // Split the message to an array of the words to extract the command
        String[] msgArray = msg.split(" ");
        switch (msgArray[0].toLowerCase()) {
            case "!train":
                // To call the api from Trafikverket to get a train-stations departure information
                return new TrainSearch(sender, searchString(msgArray)).getAnswer();
            case "!movie":
                // To make a call to the omdb api to get some movie-info           
                return new FetchMovieInfo(sender, searchString(msgArray)).getAnswer();
            case "!tv":
            case "!serie":
                // To make a call to the TVMaze-api to get info about series
                return new TVserier(sender, msgArray).getAnswer();
            case "!gps":
                // To make a call to the freegeoip-api to get tha position of the users IP
                return new GetGPS(sender,searchString(msgArray)).getInfo();
        }
        return null;
    }

    // Generates a string with the searchterm from the message and returning it
    // Jumping over the first word since it is the command
    private static String searchString(String[] searchArr) {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < searchArr.length; i++) {
            if ((searchArr.length - 1) != i) {
                str.append(searchArr[i]).append(" ");
            } else {
                str.append(searchArr[i]);
            }
        }
        return str.toString();
    }
}
