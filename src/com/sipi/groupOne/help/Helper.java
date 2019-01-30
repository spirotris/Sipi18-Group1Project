package com.sipi.groupOne.help;

public class Helper {
    private String time = "time - tar fram aktuell tid";
    private String omdbApi = "movie - söker efter filmtitlar med önskat sökord";
    private String tvMaze = "serie - söker efter serier på olika sätt";
    private String gpsApi = "gps - söker efter position där önskat ip befinner sig";


    public String getAnswer() {
        return "En sökning är uppbyggd på följande sätt: <Vad du söker> <Sök-kommando>. " + time + ", " + omdbApi + ", " + tvMaze + ", " + gpsApi;
    }
}
