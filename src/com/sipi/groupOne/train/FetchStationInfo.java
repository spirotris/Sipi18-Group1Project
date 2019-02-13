package com.sipi.groupOne.train;

// http://api.trafikinfo.trafikverket.se/v1.3/data.json?6303830c40c8444aa528618c9ffac0d3

import com.sipi.groupOne.connections.XMLtoJSONCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Iterator;

// Getting departures from Luleå-station from the API of Trafikverket
public class FetchStationInfo {
    private String APIUrl = "http://api.trafikinfo.trafikverket.se/v1.3/data.json";
    private File tempFile;
    private String fileToSend = "src\\com\\sipi\\groupOne\\train\\xmlCalls\\";

    private final String SEARCHVALUE;

    private StringBuilder jsonValue;

    private JSONObject jObject;

    // Constructor
    public FetchStationInfo(JSONArray jsonArray, String searchValue) {
        SEARCHVALUE = searchValue;
        jObject = (JSONObject) jsonArray.get(0);
        // Checking if the XML-file exists using init()
        if(!jObject.isEmpty()) {
            sendRequest();
        } else {
            jsonValue.append("Jag hittade tyvärr inget!");
        //    jsonValue = new StringBuilder("Lyckades inte hitta något.");
        }
    }

    // Checking if requested file exists
    private boolean init() {
        try{
            tempFile = new File(fileToSend);
            if(tempFile.exists())
            {
                tempFile.delete();
            }
            System.err.println(tempFile + " raderades för att skapa en ny.");
            return tempFile.exists();
        } catch (Exception e) {
            System.out.println("Fel vid försök att ladda filen: " + e.getMessage());
            return false;
        }
    }

    // Sending the request from the XML-file and getting the information requested from the response
    private void sendRequest() {
        // Generating the connection to the api
        //XMLtoJSONCon apiCon = new XMLtoJSONCon();
        // Getting the response using choosen parameters
        //JSONArray responseObjects = apiCon.tryApi(APIUrl,fileToSend);

        // Starting to work with the response
        //jObject = (JSONObject) responseObjects.get(0);

        JSONObject responseObj = (JSONObject)jObject.get("RESPONSE");
        System.out.println(responseObj.toString());
        JSONArray resultArray = (JSONArray)responseObj.get("RESULT");
        if(jObject.get("RESPONSE") == null || jObject.isEmpty()) {
            jsonValue = new StringBuilder("Jag hittade inga uppifter om stationen " + SEARCHVALUE);
        } else {
            jsonValue = new StringBuilder("Jag hittade följande avgångar:");
            for(Object searchResults : resultArray) {
                JSONObject o = (JSONObject)searchResults;
                System.out.println(o.toString());
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


