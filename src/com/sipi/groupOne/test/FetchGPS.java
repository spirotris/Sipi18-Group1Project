package com.sipi.groupOne.test;

import com.sipi.groupOne.connections.JSONCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class FetchGPS {
    private String url = "https://freegeoip.app/json/";
    private final String SEARCHVALUE;
    private final String SENDER;

    private String jsonValue;

    // Contructor
    public FetchGPS(String sender, String searchValue) {
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
        /*try {
            File key = new File("src/com/sipi/groupOne/movie/application.properties");
            BufferedReader b = new BufferedReader(new FileReader(key));
            url = b.readLine(); // Reading in single line since it is the only thing in the file
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }*/
        return true;
    }

    // Get information from the api using JSONCon
    private void initJSON() {
        //String json = url + SEARCHVALUE.replace(" ", "+");
        String json = "https://freegeoip.app/json/8.8.8.8";
        JSONCon gpsApi = new JSONCon();

        JSONArray responseObjects = gpsApi.tryApi(json);

        System.out.println(responseObjects);
        jsonValue = "Jag fick som svar: " + responseObjects;
        // Gets an array in return from JSONCon
        /*JSONArray responseObjects = omdbApi.tryApi(json);

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
                // Iterates through them to get the information i wan't
                while (movieItr.hasNext()) {
                    Object slide = movieItr.next();
                    JSONObject movie = (JSONObject) slide;
                    if(movie.get("Type").equals("movie"))
                        jsonValue += movie.get("Title") + ", från " + movie.get("Year") + "; ";
                }
            }
        }*/
    }

    // To receive the answer from the api
    public String getAnswer() {
            return jsonValue;
        }
}
