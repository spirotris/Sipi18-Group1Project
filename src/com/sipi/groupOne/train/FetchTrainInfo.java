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
        if(jObject.get("RESPONSE") == null || jObject.isEmpty()) {
            jsonValue.append("Jag hittade inga uppifter om tåg " + SEARCHVALUE);
        } else {
            jsonValue.append("Information:");
            for(Object searchResults : resultArray) {
                JSONObject o = (JSONObject)searchResults;
                System.out.println(o.toString());
                JSONArray resultsArr = (JSONArray)o.get("TrainAnnouncement");
                Iterator stationItr = resultsArr.iterator();

                // Iterates through the results to find the first movie and print it out
                while (stationItr.hasNext()) {
                    Object stationObj = stationItr.next();
                    JSONObject station = (JSONObject) stationObj;
                    System.out.println(station.get("LocationSignature").toString());
                    LocalDateTime advertisedTime = LocalDateTime.parse(station.get("AdvertisedTimeAtLocation").toString());
                    System.out.println(advertisedTime);
                }
                System.out.println(jsonValue);
            }
        }
    }
}
