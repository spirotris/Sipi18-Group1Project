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

    private String jsonValue;

    // Contructor
    public FetchMovieInfo(String sender, String searchValue) {
        SEARCHVALUE = searchValue;
        SENDER = sender;
        if(initURL()) {
            initJSON();
        } else {
            jsonValue = sender + ", jag kunde tyvärr inte hitta filen med giltigt URL";
        }
    }

    // Get the url from application.properties
    private boolean initURL() {
        try {
            File key = new File("src/com/sipi/groupOne/movie/application.properties");
            BufferedReader b = new BufferedReader(new FileReader(key));
            url = b.readLine(); // Reading in single line since it is the only thing in the file
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Get information from the api using JSONCon
    private void initJSON() {
        String json = url + SEARCHVALUE.replace(" ", "+");
        String firstResult = "tomt svar.";
        int nrResults = 0;
        JSONCon omdbApi = new JSONCon();

        // Gets an array in return from JSONCon
        JSONArray responseObjects = omdbApi.tryApi(json);

        // Picks the first row from the api-response
        JSONObject responseObject = (JSONObject)responseObjects.get(0);

        // Checks whether the response is true or false (if there was any movies or not) and a safety check so that the array isn't empty
        if(responseObject.get("Response").equals("False") || responseObjects.isEmpty()) {
            jsonValue = "Hej " + SENDER + "! Jag hittade ingen film med det namnet!";
        } else {
            jsonValue = "Hej " + SENDER + "! Jag hittade ";
            // Picks ut the search-results
            for(Object searchResults : responseObjects) {
                JSONObject o = (JSONObject)searchResults;
                System.out.println(o.toJSONString());
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
            if(nrResults == 1) {
                jsonValue += firstResult;
            } else {
                jsonValue += nrResults + " filmer som matchade din förfrågan. Första svaret var: " + firstResult + "Försök att göra en mer noggrann sökning om det var fel svar!";
            }
        }
    }

    // To receive the answer from the api
    public String getAnswer() {
        return jsonValue;
    }

}
