package com.sipi.groupOne.test;

import com.sipi.groupOne.connections.JSONCon;
import org.json.simple.JSONArray;

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
        return true;
    }

    // Get information from the api using JSONCon
    private void initJSON() {
        String json = "https://freegeoip.app/json/";
        JSONCon gpsApi = new JSONCon();

        JSONArray responseObjects = gpsApi.tryApi(json);

        System.out.println(responseObjects);
        jsonValue = "Jag fick som svar: " + responseObjects;
    }

    // To receive the answer from the api
    public String getAnswer() {
            return jsonValue;
    }
}
