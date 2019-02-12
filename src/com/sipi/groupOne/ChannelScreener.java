package com.sipi.groupOne;

import com.sipi.groupOne.movie.FetchMovieInfo;
<<<<<<< HEAD
import com.sipi.groupOne.train.FetchStationInfo;
import com.sipi.groupOne.train.FetchTrainInfo;
import com.sipi.groupOne.test.FetchGPS;
import com.sipi.groupOne.test.FetchTV;
=======
import com.sipi.groupOne.gps.GetGPS;
>>>>>>> master
import com.sipi.groupOne.tv.TVserier;

public class ChannelScreener {

    public static String message(String sender, String msg) {
        // Cleaning up the chat.message to find a command to process
        // Split the message to an array of the words to extract the command
        String[] msgArray = msg.split(" ");
        switch (msgArray[0].toLowerCase()) {           
            case "station":
                // To call the api from Trafikverket to get a train-stations departure information
                return new FetchStationInfo(sender, searchString(msgArray)).station.getAnswer();
	    case "train":
                return new FetchTrainInfo(sender, searchString(msgArray)).getAnswer();
	    case "movie":
		// To make a call to the omdb api to get some movie-info
                return new FetchMovieInfo(sender, searchString(msgArray)).getAnswer();
            case "!tv":
                return new TVserier(sender, msgArray).getAnswer();
            case "gps":
                return new GetGPS(sender,searchString(msgArray)).getInfo();
           
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
