package com.sipi.groupOne.train;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SetXMLTrain extends SetXML{
    public SetXMLTrain(String searchValue) {
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

            auth.setAttribute("authenticationkey", this.auth);

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

            return doc;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return null;
        }
    }
}
