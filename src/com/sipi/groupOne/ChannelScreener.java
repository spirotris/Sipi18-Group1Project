package com.sipi.groupOne;

import com.sipi.groupOne.movie.FetchMovieInfo;
import com.sipi.groupOne.train.FetchStationInfo;
import com.sipi.groupOne.train.FetchTrainInfo;
import com.sipi.groupOne.test.FetchGPS;
import com.sipi.groupOne.test.FetchTV;
import com.sipi.groupOne.tv.TVserier;

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
            case "station":
                // To call the api from Trafikverket to get a train-stations departure information
                FetchStationInfo station = new FetchStationInfo(sender, searchString(msgArray));
                return station.getAnswer();
            case "train":
                FetchTrainInfo train = new FetchTrainInfo();
                return train.getAnswer();
            case "serie":
                FetchTV serie = new FetchTV(sender, searchString(msgArray));
                return serie.getAnswer();
            case "gps":
                FetchGPS gps = new FetchGPS(sender,searchString(msgArray));
                return gps.getAnswer();
            case "!tv":
            	TVserier tv = new TVserier(sender, msgArray);
                return tv.getAnswer();
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
