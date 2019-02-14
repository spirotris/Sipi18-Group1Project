package com.sipi.groupOne.train;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.Iterator;

public class FetchTrainInfo extends FetchInfo {
    public FetchTrainInfo(JSONArray jsonArray, String searchValue) {
        super(jsonArray, searchValue);
    }

    @Override
    protected void getResponse() {
        JSONObject responseObj = (JSONObject)jObject.get("RESPONSE");
        System.out.println(responseObj.toString());
        JSONArray resultArray = (JSONArray)responseObj.get("RESULT");
        if(resultArray.get(0) == null || jObject.isEmpty()) {
            jsonValue.append("Jag hittade inga uppifter om tåg " + SEARCHVALUE);
        } else {
            for(Object searchResults : resultArray) {
                JSONObject o = (JSONObject)searchResults;
                System.out.println(o.toString());
                JSONArray resultsArr = (JSONArray)o.get("TrainAnnouncement");
                if(!resultsArr.isEmpty()) {
                    // If the response isn't empty, the response is checked and writes out
                    JSONObject station = (JSONObject) resultsArr.get(0);
                    LocalDateTime advertisedTime = LocalDateTime.parse(station.get("AdvertisedTimeAtLocation").toString());
                    LocalDateTime timeAtLocation = LocalDateTime.parse(station.get("TimeAtLocation").toString());
                    long minutesDiff = advertisedTime.compareTo(timeAtLocation); // Finds out how the arrival-time differs from the scheldued time
                    jsonValue.append("Tåg " + SEARCHVALUE + " ankom " + station.get("LocationSignature") + " med en diff på " + minutesDiff + " mot tidtabell.");
                    //}
                    System.out.println(jsonValue);
                }
                else {
                    jsonValue.append("Jag hittade inga uppifter om tåg " + SEARCHVALUE);

                }
            }
        }
    }
}
