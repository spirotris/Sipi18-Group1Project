package com.sipi.groupOne.train;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;

// TODO: 2019-02-12 Ta bort fileToSend?

// Generates a XML-file to be able to call the API of Trafikverket
class GenerateXML {
    private static Document doc;
    private static String path = "src\\com\\sipi\\groupOne\\train\\xmlCalls\\";
    private static String fileToSend;
    private static String fileName;

    // Creates a XML-file called trainCall.xml with contains the xml to request the status of a train-depature
    public static String addStationXML(String searchValue) {
        fileName = searchValue + "Call.xml";
        fileToSend = path + fileName;
        if(searchValue.length() < 5){
            doc = new SetXMLStation(searchValue).generateXML();
        } else {
            doc = new SetXMLStationSignature(searchValue).generateXML();
        }
        if(doc != null){
            createXML();
            return fileName;
        } else {
            return null;
        }
    }
    public static String addTrainXML(String searchValue) {
        fileName = searchValue + "Call.xml";
        fileToSend = path + fileName;
        doc = new SetXMLTrain(searchValue).generateXML();
        if(doc != null){
            createXML();
            return fileName;
        } else {
            return null;
        }
    }

    private static boolean createXML() {
        try {
            // Write the information to xml-file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src\\com\\sipi\\groupOne\\train\\xmlCalls\\" + fileName));

            transformer.transform(source, result);

            System.out.println("Filen sparad som " + fileName);

            return true;
        } catch (TransformerException te) {
            System.err.println(te.getMessageAndLocation());
            return false;
        }
    }

    /*
    // Checking if requested file exists
    private static boolean init() {
        try{
            tempFile = new File(fileToSend);
            return tempFile.exists();
        } catch (Exception e) {
            System.err.println("Fel vid försök att ladda filen: " + e.getMessage());
            return false;
        }
    }
    */
}
