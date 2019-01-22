package com.sipi.groupOne.movie;

import com.sipi.groupOne.ApiCon;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Movie {
    private String url;
    private String searchValue;
    private String sender;

    private String jsonValue;

    // Contructor
    public Movie(String sender, String searchValue) {
        this.searchValue = searchValue;
        this.sender = sender;
        if(initURL()) {
            initJSON();
        } else {
            jsonValue = sender + ", jag kunde tyvärr inte hitta filen med giltigt URL";
        }
    }

    // Get the url from application.properties
    private boolean initURL() {
        try {
            File key = new File("src/com/sipi/groupOne/movie/application.properties");
            BufferedReader b = new BufferedReader(new FileReader(key));
            url = b.readLine(); // Reading in single line since it is the only thing in the file
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Get information from the api using ApiCon
    private void initJSON() {
        String json = url + searchValue.replace(" ", "+");
        ApiCon omdbApi = new ApiCon();
        JSONObject responseObject = omdbApi.tryApi(json);
        if(responseObject.get("Response").toString().equalsIgnoreCase("false") || responseObject == null || responseObject.isEmpty()) {
            jsonValue = "Hej " + sender + "! Jag hittade ingen film med det namnet!";
        } else {
            jsonValue = "Hej " + sender + "! Jag hittade " + responseObject.get("Title") +
                    ", som släpptes " + responseObject.get("Released") +
                    ". Den är " + responseObject.get("Runtime") + " lång.";

        }
    }

    // To receive the answer from the api
    public String getAnswer() {
        return jsonValue;
    }

}
