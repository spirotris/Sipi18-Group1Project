package com.sipi.groupOne.train;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

// To get the signature of a station if the user make the call with the whole name
// Returns the signature since it is what the api request most of the time.
public class FetchStationSignature extends FetchInfo {

    public FetchStationSignature(JSONArray jsonArray, String searchValue) {
        super(jsonArray, searchValue);
    }

    @Override
    protected void getResponse() {
        // Goes through the returned xml-file
        JSONObject responseObj = (JSONObject)jObject.get("RESPONSE"); // Root-object
        JSONArray resultArray = (JSONArray)responseObj.get("RESULT"); // Result-array
        if(jObject.get("RESPONSE") == null || jObject.isEmpty()) {
            // IF the response is empty
            jsonValue = new StringBuilder("Jag hittade inga uppifter om stationen " + SEARCHVALUE);
        } else {
            // Otherwise, looping through the results to get info needed
            for(Object searchResults : resultArray) {
                JSONObject o = (JSONObject)searchResults;
                System.out.println(o.toString());
                JSONArray resultsArr = (JSONArray)o.get("TrainStation");
                Iterator departureItr = resultsArr.iterator();

                // Iterates through the results to find the signature and print it out
                while (departureItr.hasNext()) {
                    Object locationSignature = departureItr.next();
                    JSONObject signature = (JSONObject) locationSignature;
                    jsonValue.append(signature.get("LocationSignature"));
                }
            }
        }
    }
}
