package com.sipi.groupOne.train;

import com.sipi.groupOne.connections.XMLtoJSONCon;

import java.io.File;

public class TrainSearch {
    private String APIUrl = "http://api.trafikinfo.trafikverket.se/v1.3/data.json";
    private File tempFile;
    private String fileToSend = "src\\com\\sipi\\groupOne\\train\\xmlCalls\\";
    private final String SENDER;
    private final String SEARCHVALUE;

    XMLtoJSONCon xmlJson = new XMLtoJSONCon();

    FetchStationInfo fsi;


    private StringBuilder jsonValue = new StringBuilder();

    // Constructor
    public TrainSearch(String sender, String searchValue) {
        SENDER = sender;
        SEARCHVALUE = searchValue;
        generateXMLInfo();
    }

    private void generateXMLInfo() {
        if(!setSearchValue(SEARCHVALUE))
        {
            fileToSend += GenerateXML.addStationXML(SEARCHVALUE);

            fsi = new FetchStationInfo(xmlJson.tryApi(APIUrl, fileToSend), SEARCHVALUE);
            jsonValue.append("Hej " + SENDER + "! Jag hittade: " + fsi.getAnswer());
        } else {
            fileToSend += GenerateXML.addTrainXML(SEARCHVALUE);

        }
    }

    private boolean setSearchValue(String searchValue) {
        try {
            Integer.parseInt(searchValue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAnswer() {
        if(jsonValue.length() < 0) {
            return jsonValue.toString();
        } else {
            return "Hej " + SENDER + "! Jag hittade tyvärr inget.";
        }
    }
}