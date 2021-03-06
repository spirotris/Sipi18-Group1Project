package com.sipi.groupOne.connections;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class JSONCon {

    // Constructor
    public JSONCon() {
    }

    // Getting the JSON answer and returning an JSONObject
    public JSONArray tryApi(String apiUrl) {
        Object obj;
        JSONArray answers = new JSONArray();
        URL url;
        HttpURLConnection con;
        int responseCode;
        Scanner scan;
        StringBuilder jsonResponse = new StringBuilder();

        // Handling errors for the address to the api
        try {
            url = new URL(apiUrl);
        } catch (MalformedURLException e) {
            System.err.println("Något är fel med URL-adressen" + apiUrl);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Något blev fel:");
            e.printStackTrace();
            return null;
        }
        // Testing the connection
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            responseCode = con.getResponseCode();
        } catch (Exception e) {
            System.err.println("Anslutningen till Api:t misslyckades: " + apiUrl);
            e.printStackTrace();
            return null;
        }
        // Check the responsecode, throw exception if not successful
        if (responseCode != 200) {
            System.err.println("Fel vid anslutning response-code: " + responseCode + ". The call: " + apiUrl);
            return null;
        } else {
            try {
                scan = new Scanner(url.openStream());
                while(scan.hasNext()) {
                    jsonResponse.append(scan.nextLine());
                }
                scan.close();
            } catch (IOException e) {
                System.err.println("Lyckades inte öppna strömmen!");
                e.printStackTrace();
                return null;
            }
        }

        // Make an JSONObject of the response from the api
        obj = JSONValue.parse(jsonResponse.toString());

        // And turn all the answer into an array and return it
        answers.add(obj);
        return answers;
    }
}
