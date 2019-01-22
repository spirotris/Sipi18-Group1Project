package com.sipi.groupOne;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ApiCon {

    // Constructor
    public ApiCon() {
    }

    // Getting the JSON answer and returning an JSONObject
    public JSONArray tryApi(String apiUrl) {
        Object obj;
        JSONArray answers = new JSONArray();
        URL url;
        HttpURLConnection con;
        int responseCode;
        Scanner scan;
        String jsonResponse = "";

        // Handling errors for the address to the api
        try {
            url = new URL(apiUrl);
        } catch (MalformedURLException e) {
            System.out.println("Något är fel med URL-adressen");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Något blev fel:");
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
            System.out.println("Anslutningen till Api:t misslyckades");
            e.printStackTrace();
            return null;
        }
        // Check the responsecode, throw exception if not successful
        if (responseCode != 200) {
            System.out.println("Fel vid anslutning response-code: " + responseCode);
            return null;
        } else {
            try {
                scan = new Scanner(url.openStream());
                while(scan.hasNext()) {
                    jsonResponse += scan.nextLine();
                }
                scan.close();
            } catch (IOException e) {
                System.out.println("Lyckades inte öppna strömmen!");
                e.printStackTrace();
                return null;
            }
        }

        JSONParser parse = new JSONParser();

        // Make an JSONObject of the response from the api
        obj = JSONValue.parse(jsonResponse);

        // And turn all the answer into an array and return it
        answers.add(obj);
        return answers;
    }
}
