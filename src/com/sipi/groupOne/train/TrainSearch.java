package com.sipi.groupOne.train;

import java.io.File;

public class TrainSearch {
    private String APIUrl = "http://api.trafikinfo.trafikverket.se/v1.3/data.json";
    private File tempFile;
    private String fileToSend;

    private static int trainNr;
    private static String station;

    private final String SENDER;
    private final String SEARCHVALUE;

    private StringBuilder jsonValue;

    // Constructor
    public TrainSearch(String sender, String searchValue) {
        SENDER = sender;
        SEARCHVALUE = searchValue;
        // Checking if the XML-file exists using init()
        if(init()) {
            generateXMLInfo();
        } else {
            jsonValue = new StringBuilder("Lyckades inte hitta något.");
        }
    }

    private void generateXMLInfo() {
        if(SEARCHVALUE.length() < 5) {
            station = new SetXMLStationSignature().locationXML(SEARCHVALUE);
        }
        if(station != null) {
            doc = new SetXMLStationSignature().locationXML(station);
            fileName = station + "Call.xml";
        } else {
            fileName = trainNr + "Call.xml";
        }
    }

    private static void setSearchValue(String searchValue) {
        try {
            trainNr = Integer.parseInt(searchValue);
        } catch (Exception e) {
            station = searchValue;
        }
    }

    // Checking if requested file exists
    private boolean init() {
        try{
            tempFile = new File(fileToSend);
            return tempFile.exists();
        } catch (Exception e) {
            System.err.println("Fel vid försök att ladda filen: " + e.getMessage());
            return false;
        }
    }
}
