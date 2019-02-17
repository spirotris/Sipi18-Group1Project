/*
 * Authors: Tommy Ohrling & Marcus Laitala
 * Date: 2019-02-13
 */
package com.sipi.groupOne.help;

// For the function !help
// Describes the different searches the bot can do
public class Helper {

    public String getAnswer(String sender) {
        return "Hej " + sender
                + " sökning görs med: <Vad du söker> <Sök-kommando>. , "
                + "!movie - söker efter filmtitlar"
                + "!tv - söker serier på olika sätt"
                + "!gps - hämtar position där ditt ip befinner sig"
                + ". För mer information: "
                + "https://github.com/spirotris/Sipi18-Group1Project/blob/master/README.md";
    }
}