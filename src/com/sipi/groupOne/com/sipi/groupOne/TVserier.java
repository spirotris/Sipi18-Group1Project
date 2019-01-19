/*
 * Source for API info: https://www.tvmaze.com/api
 * Author: Marcus Laitala
 * Date: 2019-01-17 
 */

package com.sipi.groupOne;
import java.net.*;
import org.json.simple.JSONObject;

class TVserier {

	//https://www.tvmaze.com/api/search/shows?q=
	private static URL linkToAPI;
	private static final String embed = "&embed=";
	private static final String search = "search/";
	private static String searchValue = "";
	
	TVserier() {
		try {
			linkToAPI = new URL("https://api.tvmaze.com/");		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
	}
	
	private static String jsonResponse() {
        String json = linkToAPI + searchValue;
        ApiCon tvmazeAPI = new ApiCon();
        JSONObject response = tvmazeAPI.tryApi(json);        
        return String.format("Svaret är tomt, boten under arbete");
}
	
	public static String search (String[] keywords) {
		// cast, episodes (nr of), characters, gallery (link to pictures), main link, get specific episode (by season nr and episode nr)
		//show psych cast
		/*Switch on the following
		 * Show
		 * People (Person?)
		 * Seasons
		 * Episodes
		 */
		searchValue = keywords[0].toLowerCase() + embed + keywords[1];	
		return jsonResponse();
	}
			
}