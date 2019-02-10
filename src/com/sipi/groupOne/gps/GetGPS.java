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
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;

public class GetGPS {
    
  OkHttpClient client = new OkHttpClient();
  
  String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }
  
  private String jsonvalue;
  private String SEARCHVALUE;
  private String SENDER;
  private boolean run = false;
  
  // Contructor
    public GetGPS(){
        
    }
  
    public GetGPS(String sender, String searchValue) {
        SEARCHVALUE = searchValue;
        SENDER = sender;
        
        if(!run == true){
            run = true;
            GPSinfo();
        }
    }
  
    
    // public String
  private void GPSinfo(){
   //GetGPS example = new GetGPS(SENDER,SEARCHVALUE);
    GetGPS example = new GetGPS();
    JSONParser parser = new JSONParser();
    try{

    //Assigns the json out in a string variabel "response"    
    String response = example.run("https://freegeoip.app/json/");
    
    //Pasring the JSON string into a JSON Object 
    JSONObject jsobjt = (JSONObject)parser.parse(response);
 
    jsonvalue = ("Hej "+SENDER+ ", Du har IP :"+jsobjt.get("ip")+", bor i landet "+jsobjt.get("country_name")
                +" i regionen "+jsobjt.get("region_name")+" i staden "+jsobjt.get("city")
                +" med postnummer "+jsobjt.get("zip_code"));
 
    }catch(ParseException | IOException e){
        System.out.println(e.getMessage());  
    }
  }
    
    public String getInfo() {
        
        return jsonvalue;
    }
}
