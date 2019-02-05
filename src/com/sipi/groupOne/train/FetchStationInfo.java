package com.sipi.groupOne.train;

// http://api.trafikinfo.trafikverket.se/v1.3/data.json?6303830c40c8444aa528618c9ffac0d3

import com.sipi.groupOne.connections.XMLtoJSONCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Iterator;

public class FetchStationInfo {
    private String APIUrl = "http://api.trafikinfo.trafikverket.se/v1.3/data.json";
    private File tempFile;
    private String fileToSend = "src\\com\\sipi\\groupOne\\train\\xmlCalls\\leCall.txt";

    private final String SENDER;
    private final String SEARCHVALUE;

    private StringBuilder jsonValue;

    public FetchStationInfo(String sender, String searchValue) {
        SENDER = sender;
        SEARCHVALUE = searchValue;
        if(init()) {
            sendRequest();
        } else {
            jsonValue = new StringBuilder("Lyckades inte hitta något.");
        }
    }

    private boolean init() {
        try{
            tempFile = new File(fileToSend);
            return tempFile.exists();
        } catch (Exception e) {
            System.out.println("Fel vid försök att ladda filen: " + e.getMessage());
            return false;
        }
    }

    private void sendRequest() {
        XMLtoJSONCon apiCon = new XMLtoJSONCon();
        JSONArray responseObjects = apiCon.tryApi(APIUrl,fileToSend);
        JSONObject jObject = (JSONObject) responseObjects.get(0);

        JSONObject responseObj = (JSONObject)jObject.get("RESPONSE");
        JSONArray resultArray = (JSONArray)responseObj.get("RESULT");
        if(jObject.get("RESPONSE") == null || jObject.isEmpty()) {
            jsonValue = new StringBuilder("Hej " + SENDER + "! Jag hittade inga uppifter om stationen " + SEARCHVALUE);
        } else {
            jsonValue = new StringBuilder("Hej " + SENDER + "! Jag hittade följande avgångar:");
            for(Object searchResults : resultArray) {
                JSONObject o = (JSONObject)searchResults;
                JSONArray resultsArr = (JSONArray)o.get("TrainAnnouncement");
                Iterator departureItr = resultsArr.iterator();

                // Iterates through the results to find the first movie and print it out
                while (departureItr.hasNext()) {
                    Object departure = departureItr.next();
                    JSONObject train = (JSONObject) departure;

                    jsonValue.append(" tåg " + train.get("AdvertisedTrainIdent") + " avgår " + train.get("AdvertisedTimeAtLocation"));
                    if(train.get("ToLocation") != null) {
                        JSONArray location = (JSONArray) train.get("ToLocation");
                        JSONObject jLocation = (JSONObject) location.get(0);
                        jsonValue.append(" mot " + jLocation.get("LocationName") + ",");
                    } else {
                        jsonValue.append(", ");
                    }
                }
            }
        }
    }

    public String getAnswer() {
        return jsonValue.toString();
    }
}


