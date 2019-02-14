package com.sipi.groupOne.train;

import com.sipi.groupOne.connections.XMLtoJSONCon;

public class TrainSearch {
    private String APIUrl = "http://api.trafikinfo.trafikverket.se/v1.3/data.json";
    private String fileName;
    private String filePath = "src\\com\\sipi\\groupOne\\train\\xmlCalls\\";
    private String fileToSend;
    private final String SENDER;
    private String searchvalue;

    XMLtoJSONCon xmlJson = new XMLtoJSONCon();

    FetchStationInfo fsi;
    FetchTrainInfo fti;

    private StringBuilder jsonValue = new StringBuilder();

    // Constructor
    public TrainSearch(String sender, String searchValue) {
        SENDER = sender;
        searchvalue = searchValue;
        generateXMLInfo();
    }

    private void generateXMLInfo() {
        if(!checkSearchValue(searchvalue))
        {
            // Loop until jsonValue has a value
            while (jsonValue.length() == 0) {

                // If the searchvalue is less then 5 its most likely a station signature and a request for departures is possible
                if (searchvalue.length() < 5) {
                    fileName = GenerateXML.addStationXML(searchvalue);
                    fileToSend = filePath + fileName;
                    fsi = new FetchStationInfo(xmlJson.tryApi(APIUrl, fileToSend), searchvalue);
                    jsonValue.append("Hej " + SENDER + "! " + fsi.getAnswer());
                } else {
                    // If searchvalue is longer the trying to find tha signature to be able to make a request
                    fileName = GenerateXML.addStationXML(searchvalue);
                    fileToSend = filePath + fileName;
                    searchvalue = new FetchStationSignature(xmlJson.tryApi(APIUrl, fileToSend), searchvalue).getAnswer();
                }
            }
            if (fileName != null) {
                System.out.println("fileName not null = " + fileName);
            } else {
                System.out.println(xmlJson.tryApi(APIUrl, fileToSend).toString());
                jsonValue.append("Hej " + SENDER + "! Jag hittade tyvärr inget!");
                System.err.println("Fel vid skapande av fil.");
            }
        } else {
            fileName = GenerateXML.addTrainXML(searchvalue);
            fileToSend = filePath + fileName;
            fti = new FetchTrainInfo(xmlJson.tryApi(APIUrl,fileToSend), searchvalue);
            jsonValue.append("Hej " + SENDER + "! " + fti.getAnswer());
        }
    }

    private boolean checkSearchValue(String searchValue) {
        try {
            Integer.parseInt(searchValue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAnswer() {
        if(jsonValue.length() > 0) {
            return jsonValue.toString();
        } else {
            return "Hej " + SENDER + "! Jag hittade tyvärr inget.";
        }
    }
}
