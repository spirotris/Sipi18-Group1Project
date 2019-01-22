package com.sipi.groupOne;

public class Direct {

    public static String message(String sender, String msg) {
        // Cleaning up the chat.message to find a command to process
        // Split the message to an array of the words to extract the command
        String[] msgArray = msg.split(" ");
        switch (msgArray[0].toLowerCase()) {
            case "time":
                String time = new java.util.Date().toString();
                return String.format(sender + ": The time is now " + time);
            case "movie":
                // To call the omdb api and get a movie-title
                Movie movie = new Movie(searchString(msgArray));
                return movie.getJSONvalue();
                //return String.format("Hej " + sender + "! Du sökte på " + msgArray[0]+ " och skrev: " + searchString(msgArray)); // OBS
            case "serie":
                return String.format("Hej " + sender + "! Du sökte på " + msgArray[0]+ " och skrev: " + searchString(msgArray));
        }

        return null;
    }

    // Generates a string with the searchterm from the message and returning it
    // Jumping over the first word since it is the command
    private static String searchString(String[] searchArr) {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < searchArr.length; i++) {
            str.append(searchArr[i] + " ");
        }
        return str.toString();
    }
}
