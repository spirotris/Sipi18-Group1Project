/**
 * Authors: Tommy Ohrling & Marcus Laitala
 */
package com.sipi.groupOne.help;

public class Helper {
    private String time = "time - tar fram aktuell tid";
    private String omdbApi = "movie - söker efter filmtitlar med önskat sökord";
    private String tvMaze = "serie - söker efter serier på olika sätt";
    private String gpsApi = "gps - söker efter position där önskat ip befinner sig";
    
    private final String HOWTO = " en sökning är uppbyggd på följande sätt: <Vad du söker> <Sök-kommando>. " + time + ", " + omdbApi + ", " + tvMaze + ", " + gpsApi;
    private static final String HELLO = "Hej ";    
    
    String sender, msgArray;
    
    public Helper(String sender, String msgArray) {
    	this.sender = sender;
    	
    	//Vet inte vad denna ska användas till än, men kan vara bra att ha
    	this.msgArray = msgArray; 
    }

    public String getAnswer() {
        return  HELLO + sender + HOWTO;
    }
}
