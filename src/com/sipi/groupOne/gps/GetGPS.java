/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipi.groupOne.gps;

/**
 *
 * @author mbutt
 */
import java.io.BufferedReader;
import java.io.Reader;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.Charset;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

public class GetGPS {

    private JSONObject jsonObject;
    private final String URL_IN = "https://freegeoip.app/json/";
    private String resultStr;
    private String SEARCHVALUE;
    private String SENDER;

    public GetGPS(String sender, String searchValue) {
        SEARCHVALUE = searchValue;
        SENDER = sender;

        GPSinfo();
    }

    private String readAll(Reader read) {
        int count;
        StringBuilder value = new StringBuilder();
        try {
            while ((count = read.read()) != -1) {
                value.append((char) count);
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            return "Fel!";
        }
        return value.toString();
    }

    private JSONObject readJsonFromUrl(String url) {
        JSONParser parser = new JSONParser();

        try (InputStream inputstrm = new URL(url).openStream()) {
            BufferedReader buffread = new BufferedReader(new InputStreamReader(inputstrm, Charset.forName("UTF-8")));
            String jsontext = readAll(buffread);

            jsonObject = (JSONObject) parser.parse(jsontext);
        } catch (ParseException | IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return jsonObject;
    }

    private void GPSinfo() {
        jsonObject = readJsonFromUrl(URL_IN);

        resultStr = ("Hej " + SENDER
                + ", Du har IP :" + jsonObject.get("ip")
                + ", bor i landet " + jsonObject.get("country_name")
                + " i regionen " + jsonObject.get("region_name")
                + " i staden " + jsonObject.get("city")
                + " med postnummer " + jsonObject.get("zip_code"));
    }

    public String getInfo() {
        return resultStr;
    }
}
