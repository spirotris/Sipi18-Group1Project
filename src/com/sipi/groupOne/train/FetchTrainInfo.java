package com.sipi.groupOne.train;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class FetchTrainInfo extends FetchInfo {
    public FetchTrainInfo(JSONArray jsonArray, String searchValue) {
        super(jsonArray, searchValue);
    }

    @Override
    protected void sendRequest() {
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
                Iterator departureItr = resultsArr.iterator();

                // Iterates through the results to find the first movie and print it out
                while (departureItr.hasNext()) {
                    Object departure = departureItr.next();
                    JSONObject train = (JSONObject) departure;
                    System.out.println(train.toString());

                    jsonValue.append(" tåg " + train.get("AdvertisedTrainIdent") + " avgår " + train.get("AdvertisedTimeAtLocation"));
                    if(train.get("ToLocation") != null) {
                        JSONArray location = (JSONArray) train.get("ToLocation");
                        JSONObject jLocation = (JSONObject) location.get(0);
                        jsonValue.append(" mot " + jLocation.get("LocationName") + ",");
                    } else {
                        jsonValue.append(", ");
                    }
                }
                System.out.println(jsonValue);
            }
        }
    }
}
