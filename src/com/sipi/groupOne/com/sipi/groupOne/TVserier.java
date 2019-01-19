/*
 * Source: https://www.tvmaze.com/api
 * Author: Marcus Laitala
 * Date: 2019-01-17 
 */

// Example cases: tv psych
package com.sipi.groupOne;
import java.net.*;
import org.json.simple.JSONObject;

class TVserier {
	
	
	//Full API link
	private URL linkToAPI;
	private String searchValue = "";
	
	TVserier() {
		try {
			linkToAPI = new URL("https://www.tvmaze.com/api/search/shows?q=");		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private String jsonResponse() {
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
		switch (keywords[0].toLowerCase()) {
		case "show":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
			
		default:
			break;
		}
		
		return "";
	}	
}