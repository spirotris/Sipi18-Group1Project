/*
 * Authors: Tommy Ohrling & Marcus Laitala
 * Date: 2019-02-13
 */
package com.sipi.groupOne.help;

// For the function !help
// Describes the different searches the bot can do
public class Helper {
    private final String OMDBAPI = "!movie - söker efter filmtitlar";
    private final String TVMAZE = "!tv - söker serier på olika sätt";
    private final String GPSAPI = "!gps - söker position där önskat ip befinner sig";
    
    private final String HOWTO = " sökning görs med: <Vad du söker> <Sök-kommando>. , " + OMDBAPI + ", " + TVMAZE + ", " + GPSAPI;
    private static final String HELLO = "Hej ";    
    
    private final String SENDER;
    private String[] msgArray;
    
    public Helper(String sender, String[] msgArray) {
    	this.SENDER = sender;
    	
    	//Vet inte vad denna ska användas till än, men kan vara bra att ha
    	this.msgArray = msgArray;
    }

    public String getAnswer() {
        return  HELLO + SENDER + HOWTO;
    }
}
