package com.sipi.groupOne.gps;

/**
 *
 * @author mbutt
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class GetGPS {

    private StringBuilder outputMsg = new StringBuilder();
    private String USER;
    private String HOST;

    public GetGPS(String sender, String hostname) {
        USER = sender;
        System.out.println("hostname: " + hostname);
        try {
            HOST = InetAddress.getByName(hostname).getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println(e.getLocalizedMessage());
            outputMsg.append("");
            return;
        }
        System.out.println("host: " + HOST);

        try {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://freegeoip.app/json/" + HOST)
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("content-type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            String jsonStr = response.body().string();

            JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonStr);
            System.out.println("response: " + response);

            outputMsg.append("Hej ").append(USER)
                    .append(", Du har IP :").append(jsonObj.get("ip"))
                    .append(", bor i landet ").append(jsonObj.get("country_name"))
                    .append(" i regionen ").append(jsonObj.get("region_name"))
                    .append(" i staden ").append(jsonObj.get("city"))
                    .append(" med postnummer ").append(jsonObj.get("zip_code"));

        } catch (ParseException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getInfo() {
        if (!outputMsg.toString().isEmpty()) {
            return outputMsg.toString();
        } else {
            return "Unable to find GPS information.";
        }

    }
}