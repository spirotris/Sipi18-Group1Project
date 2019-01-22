package com.sipi.groupOne;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

// TODO: 2019-01-22 Borde det vara return null om något exception blir catchat?

public class ApiCon {

    // Constructor
    public ApiCon() {
    }

    // Getting the JSON answer and returning an JSONObject
    public JSONObject tryApi(String apiUrl) {
        JSONObject obj = null;
        URL url = null;
        HttpURLConnection con = null;
        int responseCode = 0;
        Scanner scan = null;
        String jsonResponse = "";
        // Handling errors for the address to the api
        try {
            url = new URL(apiUrl);
        } catch (MalformedURLException e) {
            System.out.println("Något är fel med URL-adressen");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Något blev fel:");
            e.printStackTrace();
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
        }
        // Check the responsecode, throw exception if not successful
        if (responseCode != 200) {
            throw new RuntimeException("Fel vid anslutning responsecode: " + responseCode);
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
            }
        }

        JSONParser parse = new JSONParser();

        // Try to make an JSONObject of the response from the api
        try {
            obj = (JSONObject) parse.parse(jsonResponse);
        } catch (ParseException e) {
            System.out.println("Kunde inte utvinna JSON-koden från svaret.");
            e.printStackTrace();
        }
        return obj;
    }
}
