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
  private JSONObject json;
  private final String URL_IN = "https://freegeoip.app/json/";
  private StringBuilder jsonvalue;
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
 
  
  private String readAll (Reader read) throws IOException{
      int count;
      StringBuilder value = new StringBuilder();
      while ((count = read.read()) != -1){
          value.append((char)count);
      }
      return value.toString();
  } 
  
  private JSONObject readJsonFromUrl(String url){
    
      JSONParser parser = new JSONParser();
    
      
      try (InputStream inputstrm = new URL(url).openStream()){
      BufferedReader buffread = new BufferedReader(new InputStreamReader(inputstrm, Charset.forName("UTF-8")));    
      String jsontext = readAll(buffread);
    
     json = (JSONObject)parser.parse(jsontext);     
      }catch(ParseException | IOException e){
          System.out.println(e.getMessage());
      }
      return json;     
  }  
    
  private void GPSinfo(){
      json = readJsonFromUrl(URL_IN);
      String jsonvalue_tmp = ("Hej "+SENDER+ ", Du har IP :"+json.get("ip")+", bor i landet "+json.get("country_name")
              +" i regionen "+json.get("region_name")+" i staden "+json.get("city")
              +" med postnummer "+json.get("zip_code"));
      jsonvalue = new StringBuilder(jsonvalue_tmp);
  } 
  
  public String getInfo() {
        
        return jsonvalue.toString();
    }

  }
    

