package com.sipi.groupOne.train;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class SetXMLTrain extends SetXML{
    public SetXMLTrain(String searchValue) {

    }

    public String getAnswer() {
        boolean test = GenerateXML.createXML("535");
        return "Försökte skapa en fil, fick svaret " + test;
    }

    @Override
    public boolean generateXML() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Root Element = REQUEST
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("REQUEST");
            doc.appendChild(rootElement);

            Element auth = doc.createElement("LOGIN");
            rootElement.appendChild(auth);

            auth.setAttribute("authenticationkey", String.valueOf(auth));

            // The QUERY Contains what you request
            Element query = doc.createElement("QUERY");
            rootElement.appendChild(query);

            query.setAttribute("objecttype", "TrainAnnouncement");

            // Possibility for a narrower filter of the result
            Element filter = doc.createElement("FILTER");
            query.appendChild(filter);


            Element eq = doc.createElement("EQ");
            filter.appendChild(eq);

            // States which train number you want to get info about
            eq.setAttribute("name", "AdvertisedTrainIdent");
            eq.setAttribute("value", searchValue);

            // Write the information to xml-file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src\\com\\sipi\\groupOne\\train\\xmlCalls\\trainCall.xml"));

            transformer.transform(source, result);

            System.out.println("Filen Sparad!");

            return true;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
    }
}
