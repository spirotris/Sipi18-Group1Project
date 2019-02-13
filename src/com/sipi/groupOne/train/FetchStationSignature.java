package com.sipi.groupOne.train;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class FetchStationSignature extends FetchInfo {

    public FetchStationSignature(JSONArray jsonArray, String searchValue) {
        super(jsonArray, searchValue);
    }

    @Override
    protected void sendRequest() {
        JSONObject responseObj = (JSONObject)jObject.get("RESPONSE");
        System.out.println(responseObj.toString());
        JSONArray resultArray = (JSONArray)responseObj.get("RESULT");
        if(jObject.get("RESPONSE") == null || jObject.isEmpty()) {
            jsonValue = new StringBuilder("Jag hittade inga uppifter om stationen " + SEARCHVALUE);
        } else {
            for(Object searchResults : resultArray) {
                JSONObject o = (JSONObject)searchResults;
                System.out.println(o.toString());
                JSONArray resultsArr = (JSONArray)o.get("TrainStation");
                Iterator departureItr = resultsArr.iterator();

                // Iterates through the results to find the first movie and print it out
                while (departureItr.hasNext()) {
                    Object locationSignature = departureItr.next();
                    JSONObject signature = (JSONObject) locationSignature;
                    System.out.println(signature.toString());
                    jsonValue.append(signature.get("LocationSignature"));
                }
            }
        }
    }
}
