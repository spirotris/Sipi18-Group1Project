package com.sipi.groupOne.test;

import com.sipi.groupOne.connections.JSONCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class FetchTV {
    private final String SEARCHVALUE;
    private final String SENDER;

    private String jsonValue;

    // Contructor
    public FetchTV(String sender, String searchValue) {
        SEARCHVALUE = searchValue;
        SENDER = sender;
        if(initURL()) {
            initJSON();
        } else {
            jsonValue = sender + ", jag kunde tyvärr inte hitta filen med giltigt URL";
        }
    }

    private boolean initURL() {
        return true;
    }

    private void initJSON() {

        String json = "http://api.tvmaze.com/search/shows?q=girls";
        JSONCon gpsApi = new JSONCon();

        JSONArray responseObjects = gpsApi.tryApi(json);

        if(responseObjects.isEmpty()) {
            jsonValue = "Hej " + SENDER + "! Jag hittade ingen film med det namnet!";
        } else {
            jsonValue = "Hej " + SENDER + "! Jag hittade ";
            // Picks ut the search-results
            for (Object searchResults : responseObjects) {
                //JSONObject o = (JSONObject) searchResults;
                JSONArray resultsArr = (JSONArray) searchResults;

                Iterator serieItr = resultsArr.iterator();
                // Iterates through them to get the information i wan't
                while (serieItr.hasNext()) {
                    Object slide = serieItr.next();
                    JSONObject serie = (JSONObject) slide;
                    System.out.println(serie.toJSONString());
                    String name = "";
                    JSONObject showInfo = (JSONObject) serie.get("show");
                    jsonValue += showInfo.get("name") + ", har poäng " + serie.get("score") + "; ";
                }
            }
        }
        System.out.println(jsonValue);
    }
    // To receive the answer from the api
    public String getAnswer() {
        return jsonValue;
    }
}
