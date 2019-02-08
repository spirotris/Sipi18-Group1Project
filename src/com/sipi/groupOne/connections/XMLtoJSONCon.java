package com.sipi.groupOne.connections;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

// TODO: 2019-02-08 Kör lite trackback på Copy för att förstå vad som händer - behövs det ens?

// Calls API from trafikverket using a XML-file getting answer in JSON-format
public class XMLtoJSONCon {

    // Trying to connect to the API
    public JSONArray tryApi(String apiURL, String xmlFileToSend) {
        try {
            URL url = new URL(apiURL);

            // Opening the connection
            URLConnection conn = url.openConnection();
            HttpURLConnection con = (HttpURLConnection) conn;

            // Reading the xml-file
            FileInputStream fin = new FileInputStream(xmlFileToSend);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            // Copying the xml to a ByteArray
            copy(fin, bout);
            fin.close();

            // Generating a array and making it a string
            byte[] b = bout.toByteArray();
            StringBuffer buf = new StringBuffer();
            String s = new String(b);

            // Cleaning for the call
            s = s.replaceAll("VALUE", "value");
            b = s.getBytes();

            // Setting properties for the call
            con.setRequestProperty("Content-Length", String.valueOf(b.length));
            con.setRequestProperty("Content-Type", "text/xml; charset=utf8");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            con.setReadTimeout(30000);

            // Performing the call
            OutputStream out = con.getOutputStream();
            out.write(b);
            out.close();

            con.connect();
            System.out.println("Status på anslutning: " + con.getResponseMessage());

            // Getting the response
            InputStreamReader isr = new InputStreamReader(con.getInputStream());
            BufferedReader in = new BufferedReader(isr);

            String inputLine;

            // Working with the response and returning it
            while ((inputLine = in.readLine()) != null) {
                buf.append(inputLine);
            }
            in.close();
            JSONArray answers = new JSONArray();
            answers.add(JSONValue.parse(buf.toString()));
            return answers;

        } catch (MalformedURLException e){
            System.err.println("Kunde inte ansluta till API:et: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Något gick fel vid filhanteringen: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Copying the xml-file and generating a byte-array
    private void copy(InputStream in, OutputStream out) {
        try{
            synchronized (in) {
                synchronized (out) {
                    byte[] buffer = new byte[256];
                    while(true) {
                        int bytesRead = in.read(buffer);
                        if(bytesRead == -1)
                            break;
                        out.write(buffer,0,bytesRead);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Fel vid kopiering: " + e.getMessage());
        }
    }

}
