package com.sipi.groupOne.train;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SetXMLStation extends SetXML {
    public SetXMLStation(String searchValue) {
        super(searchValue);
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

            auth.setAttribute("authenticationkey", String.valueOf(auth));

            // The QUERY Contains what you request
            Element query = doc.createElement("QUERY");
            rootElement.appendChild(query);

            query.setAttribute("objecttype", "TrainAnnouncement");
            query.setAttribute("orderby", "AdvertisedTimeAtLocation");

            // Possibility for a narrower filter of the result
            Element filter = doc.createElement("FILTER");
            query.appendChild(filter);

            // Filter attribute
            Element and = doc.createElement("AND");
            filter.appendChild(and);

            // States what info you want
            Element actEq = doc.createElement("EQ");
            and.appendChild(actEq);
            actEq.setAttribute("name", "ActivityType");
            actEq.setAttribute("value", "Avgang");

            // States what station you want the info about
            Element locEq = doc.createElement("EQ");
            and.appendChild(locEq);
            locEq.setAttribute("LocationSignature", searchValue);

            // Filter-attribute
            Element or = doc.createElement("OR");

            // Another level Filter-attribute to choose between which timestamps i want the answer
            Element andTwo = doc.createElement("AND");
            or.appendChild(andTwo);

            // Greater-than value
            Element gt = doc.createElement("GT");
            andTwo.appendChild(gt);

            gt.setAttribute("name", "AdvertisedTimeAtLocation");
            gt.setAttribute("value", "$dateadd(-00:15:00)");

            // Less-than value
            Element lt = doc.createElement("LT");
            andTwo.appendChild(lt);

            lt.setAttribute("name", "AdvertisedTimeAtLocation");
            lt.setAttribute("value", "$dateadd(14:00:00)");

            // Another and-filter to able to get one hour delayed departures
            Element andThree = doc.createElement("AND");
            or.appendChild(andThree);

            Element gtTwo = doc.createElement("GT");
            andThree.appendChild(gtTwo);

            gtTwo.setAttribute("name", "AdvertisedTimeAtLocation");
            gtTwo.setAttribute("value", "$dateadd(00:30:00)");

            Element ltTwo = doc.createElement("LT");
            andThree.appendChild(ltTwo);

            ltTwo.setAttribute("name", "EstimatedTimeAtLocation");
            ltTwo.setAttribute("value", "$dateadd(-01:00:00)");

            // Include-attributes to choose what answers I want to receive
            Attr includeOne = doc.createAttribute("INCLUDE");
            includeOne.setValue("AdvertisedTrainIdent");
            query.setAttributeNode(includeOne);

            Attr includeTwo = doc.createAttribute("INCLUDE");
            includeTwo.setValue("AdvertisedTimeAtLocation");
            query.setAttributeNode(includeTwo);

            Attr includeThree = doc.createAttribute("INCLUDE");
            includeThree.setValue("TrackAtLocation");
            query.setAttributeNode(includeThree);

            Attr includeFour = doc.createAttribute("INCLUDE");
            includeFour.setValue("ToLocation");
            query.setAttributeNode(includeFour);

            return doc;
        } catch (ParserConfigurationException pce) {
            System.err.println(pce.getLocalizedMessage());
        }
        return null;
    }

}
