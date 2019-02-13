/*
 * Authors: Tommy Ohrling & Marcus Laitala
 * Date: 2019-02-13
 */
package com.sipi.groupOne.help;

public class Helper {
    private String time = "!time - visar aktuell tid";
    private String omdbApi = "!movie - söker filmtitlar med önskat sökord";
    private String tvMaze = "!tv - söker serier på olika sätt";
    private String gpsApi = "!gps - söker position där önskat ip befinner sig";
    
    private final String HOWTO = " sökning görs med: <Vad du söker> <Sök-kommando>. " + time + ", " + omdbApi + ", " + tvMaze + ", " + gpsApi;
    private static final String HELLO = "Hej ";    
    
    String sender;
    String[] msgArray;
    
    public Helper(String sender, String[] msgArray) {
    	this.sender = sender;
    	
    	//Vet inte vad denna ska användas till än, men kan vara bra att ha
    	this.msgArray = msgArray;
    }

    public String getAnswer() {
        return  HELLO + sender + HOWTO;
    }
}
