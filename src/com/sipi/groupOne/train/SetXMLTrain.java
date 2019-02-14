package com.sipi.groupOne.train;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SetXMLTrain extends SetXML{
    String date;

    public SetXMLTrain(String searchValue, String date) {
        super(searchValue);
        this.date = date;
    }

    @Override
    public Document generateXML() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Root Element = REQUEST
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("REQUEST");
            doc.appendChild(rootElement);

            Element auth = doc.createElement("LOGIN");
            rootElement.appendChild(auth);

            auth.setAttribute("authenticationkey", this.auth);

            // The QUERY Contains what you request
            Element query = doc.createElement("QUERY");
            rootElement.appendChild(query);

            query.setAttribute("objecttype", "TrainAnnouncement");
            query.setAttribute("orderby", "AdvertisedTimeAtLocation desc");

            // Possibility for a narrower filter of the result
            Element filter = doc.createElement("FILTER");
            query.appendChild(filter);

            // Sets the train-number the informatation is about
            Element eq = doc.createElement("EQ");
            filter.appendChild(eq);
            // States which train number you want to get info about
            eq.setAttribute("name", "AdvertisedTrainIdent");
            eq.setAttribute("value", searchValue);

            // The date that is checked for
            Element eqDate = doc.createElement("EQ");
            filter.appendChild(eqDate);
            eqDate.setAttribute("name", "ScheduledDepartureDateTime");
            eqDate.setAttribute("value", date);

            // Sets that all stations should be return, not only those where people get of or on
            Element eqTwo = doc.createElement("EQ");
            filter.appendChild(eqTwo);
            eqTwo.setAttribute("name", "Advertised");
            eqTwo.setAttribute("value", "false");

            // The include tag sets what information i want in return
            Attr includeOne = doc.createAttribute("INCLUDE");
            includeOne.setValue("LocationSignature");
            query.setAttributeNode(includeOne);
            Attr includeTwo = doc.createAttribute("INCLUDE");
            includeTwo.setValue("AdvertisedTimeAtLocation");
            query.setAttributeNode(includeTwo);
            Attr includeThree = doc.createAttribute("INCLUDE");
            includeThree.setValue("TimeAtLocation");
            query.setAttributeNode(includeThree);
            Attr includeFour = doc.createAttribute("INCLUDE");
            includeFour.setValue("ToLocation.LocationName");
            query.setAttributeNode(includeFour);
            return doc;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return null;
        }
    }
}
