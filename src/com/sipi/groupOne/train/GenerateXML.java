package com.sipi.groupOne.train;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// Generates a XML-file to be able to call the API of Trafikverket
class GenerateXML {
    private static String auth;
    private static Document doc;
    private static String fileName;


    // Creates a XML-file called trainCall.xml with contains the xml to request the status of a train-depature
    public static String createXML(String searchValue) {

        try {
            // Write the information to xml-file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src\\com\\sipi\\groupOne\\train\\xmlCalls\\" + fileName));

            transformer.transform(source, result);

            System.out.println("Filen sparad som " + fileName);

            return fileName;
        } catch (TransformerException te) {
            System.err.println(te.getMessageAndLocation());
            return null;
        }
        // FÖLJANDE KOD FUNGERAR BRA
        /*        boolean isString =

        }
        return false; */
    }
}
