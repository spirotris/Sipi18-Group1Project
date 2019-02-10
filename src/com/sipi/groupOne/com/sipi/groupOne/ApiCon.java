package com.sipi.groupOne;


// API-Key: 3f3e80c9
// Request-adress(title): http://www.omdbapi.com/?apikey=[3f3e80c9]&t=

import org.json.simple.JSONObject;
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
    public JSONObject tryApi(String apiUrl) {
        JSONObject obj = null;
        URL url = null;
        HttpURLConnection con = null;
        int responseCode = 0;
        Scanner scan = null;
        String jsonResponse = "";
        String json = ""; // TA BORT!
        // Handling errors for the address to the api
        try {
            url = new URL(json);
        } catch (MalformedURLException e) {
            System.out.println("Något blev fel!");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Något blev fel:");
            System.out.println(e.getMessage());
        }
        // Testing the connection
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            responseCode = con.getResponseCode();
        } catch (Exception e) {
            System.out.println("Anslutningen till Api:t misslyckades");
            System.out.println(e.getMessage());
        }
        // Check the responsecode, throw exception if not succesful
        if (responseCode != 200) {
            throw new RuntimeException("Fel vid anslutning responsecode: " + responseCode);
        } else {

            try {
                scan = new Scanner(url.openStream());
                while(scan.hasNext()) {
                    jsonResponse += scan.nextLine();
                }
                System.out.println("\n JSON data-string");
                System.out.println(jsonResponse);
                scan.close();
            } catch (IOException e) {
                System.out.println("Lyckades inte öppna strömmen!");
                System.out.println(e.getMessage());
            }
        }
        JSONParser parse = new JSONParser();
        // Try to make an JSONObject of the response from the api
        try {
            obj = (JSONObject) parse.parse(jsonResponse);
        } catch (ParseException e) {
            System.out.println("Kunde inte utvinna JSON-koden från svaret.");
            System.out.println(e.getErrorType());
        }
        return obj;
    }
}