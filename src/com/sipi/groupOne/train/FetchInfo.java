package com.sipi.groupOne.train;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class FetchInfo {
    final String SEARCHVALUE;
    StringBuilder jsonValue = new StringBuilder();
    JSONObject jObject;

    public FetchInfo(JSONArray jsonArray, String searchValue) {
        jObject = (JSONObject) jsonArray.get(0);
        SEARCHVALUE = searchValue;
        // Checking if the XML-file exists using init()
        if (!jObject.isEmpty()) {
            sendRequest();
        } else {
            jsonValue.append("Jag hittade tyvärr inget!");
        }
    }

    protected abstract void sendRequest();

    public String getAnswer() {
        return jsonValue.toString();
    }
}