package com.sipi.groupOne.movie;

import com.sipi.groupOne.connections.JSONCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class FetchMovieInfo {
    private String url;
    private final String SEARCHVALUE;
    private final String SENDER;
    private final String HELPSTRING = "Med kommandot !movie så söker du efter en film-titel, första bästa svar kommer att fås. För att få till funktionen så anges sökningen: !movie <sökvärde>, t.ex: !movie Titanic";

    private StringBuilder jsonValue = new StringBuilder();

    // Contructor
    public FetchMovieInfo(String sender, String searchValue) {
        SEARCHVALUE = searchValue;
        SENDER = sender;
        System.out.println(searchValue);
        if(SEARCHVALUE.toLowerCase().equals("!help")) {
            // The user want help and the help-string is set
            jsonValue.append(sender + "! " + HELPSTRING);
        } else if(SEARCHVALUE.isEmpty()) {
            jsonValue.append(SENDER + ", du måste ange ett sökvärde. Ange !movie !help för hjälp.");
        } else {
            // If the user doesn't wan't help
            if (initURL()) {
                // The URL to the was possible to be loaded, the call to the api will be made
                initJSON();
            } else {
                // If the url can't be loaded to the file
                jsonValue.append(sender + ", jag kunde tyvärr inte genomföra sökningen!");
                System.err.println("Kunde inte ladda urlen från filen application.properties i movie-klassen");
            }
        }
    }

    // Get the url with auth-key to the API from application.properties
    private boolean initURL() {
        try {
            File key = new File("src/com/sipi/groupOne/movie/application.properties");
            BufferedReader b = new BufferedReader(new FileReader(key));
            url = b.readLine(); // Reading in single line since it is the only thing in the file
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    // Get information from the api using JSONCon
    private void initJSON() {
        // Setting the url-call for the API, a standard-answer if there isn't any response and a counter for number of answers
        String json = url + SEARCHVALUE.replace(" ", "+");
        String firstResult = "tomt svar.";
        int nrResults = 0;
        JSONCon omdbApi = new JSONCon(); // Initiating the class for connection to the API

        // Gets an array in return from JSONCon
        JSONArray responseObjects = omdbApi.tryApi(json);

        // Picks the first row from the api-response
        JSONObject responseObject = (JSONObject)responseObjects.get(0);

        // Checks whether the response is true or false (if there was any movies or not) and a safety check so that the array isn't empty
        if(responseObject.get("Response").equals("False") || responseObjects.isEmpty()) {
            jsonValue.append("Hej " + SENDER + "! Jag hittade ingen film med det namnet!");
        } else {
            jsonValue.append("Hej " + SENDER + "! Jag hittade ");
            // Picks ut the search-results
            for(Object searchResults : responseObjects) {
                JSONObject o = (JSONObject)searchResults;
                JSONArray resultsArr = (JSONArray)o.get("Search");
                Iterator movieItr = resultsArr.iterator();

                // Iterates through the results to find the first movie and print it out
                while (movieItr.hasNext()) {
                    Object slide = movieItr.next();
                    JSONObject movie = (JSONObject) slide;
                    // Checks if the answer is a movie
                    if(movie.get("Type").equals("movie")) {
                        // If it is the first result if it is it will be displayed
                        if(nrResults == 0) {
                            firstResult = movie.get("Title") + ", från " + movie.get("Year") + ". ";
                        }
                        nrResults++;
                    }
                }
            }
            // Sets a nice output
            if(nrResults == 1) {
                jsonValue.append(firstResult);
            } else {
                jsonValue.append(nrResults + " filmer som matchade din förfrågan. Första svaret var: " + firstResult + "Försök att göra en mer noggrann sökning om det var fel svar!");
            }
        }
    }

    // To receive the answer from the api
    public String getAnswer() {
        return jsonValue.toString();
    }

}
