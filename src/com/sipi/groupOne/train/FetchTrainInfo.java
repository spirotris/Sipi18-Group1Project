package com.sipi.groupOne.train;

public class FetchTrainInfo {
    public String getAnswer() {
        boolean test = GenerateXML.trainXML("535");
        return "Försökte skapa en fil, fick svaret " + test;
    }
}
