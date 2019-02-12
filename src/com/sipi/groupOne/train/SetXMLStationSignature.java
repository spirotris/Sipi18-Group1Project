package com.sipi.groupOne.train;

/* <REQUEST>
      <LOGIN authenticationkey="openapiconsolekey" />
      <QUERY objecttype="TrainStation">
            <FILTER>
                <EQ name="AdvertisedShortLocationName" value="Luleå" />
            </FILTER>
      </QUERY>
</REQUEST>
*/

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SetXMLStationSignature {
    // Creates a XML-file called trainCall.xml with contains the xml to request the status of a train-depature
    public Document locationXML(String searchValue) {
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

            query.setAttribute("objecttype", "TrainStation");

            // Possibility for a narrower filter of the result
            Element filter = doc.createElement("FILTER");
            query.appendChild(filter);


            Element eq = doc.createElement("EQ");
            filter.appendChild(eq);

            // States which train number you want to get info about
            eq.setAttribute("name", "AdvertisedShortLocationName");
            eq.setAttribute("value", searchValue);

            return doc;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }

        return null;
    }
}
