package com.sipi.groupOne.train;

// http://api.trafikinfo.trafikverket.se/v1.3/data.json?6303830c40c8444aa528618c9ffac0d3

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchStationInfo {

    private URL trvUrl;
    String jsonValue;

    JAXBObject jaxbObject = new JAXBObject();

    public FetchStationInfo() {
        init();
    }

    private void init() {
    }

    private void sendRequest() {
        JAXB
        try {
            URL url = new URL("http://api.trafikinfo.trafikverket.se/v1.3/data.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");

            OutputStream os = connection.getOutputStream();
            // Write your XML to the OutputStream (JAXB is used in this example)
            jaxbContext.createMarshaller().marshal(customer, os);
            os.flush();
            connection.getResponseCode();
            connection.disconnect();
        } catch (MalformedURLException e){
            jsonValue = "Jag kunde tyvärr inte ansluta till API:et";
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
