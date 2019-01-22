/*
 * Source for API info: https://www.tvmaze.com/api
 * Author: Marcus Laitala
 * Date: 2019-01-17 
 */

package com.sipi.groupOne.tv;

import java.net.*;
import com.sipi.groupOne.connections.JSONCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class TVserier {


	private static URL linkToAPI;
	private static final String API = "API";
	private static final String NAME_OF_API_HOST_SITE = "TVMAZE";
	private static final String EMBED = "&embed=";
	private static final String SEARCH = "search/";
	private static final String QUERY = "?q=";
	private static final String SHOWS = "shows", PEOPLE = "people";
	private static final String MANUAL = "Uses the API of " + NAME_OF_API_HOST_SITE + "to leverage results of querys, type API for more link. Example usage:\ntv show [name of tvshow]\ntv people [name of person]";
	private static final String ERROR_NOT_ENOUGH_KEYWORDS = "Need more keywords to perform a search. Type ? or help for more information";


	private static String searchValue = "";
	private static String show = "show";

	TVserier() {
		try {
			linkToAPI = new URL("https://api.tvmaze.com/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private static String jsonResponse() {
		String json = linkToAPI + searchValue;
		JSONCon tvmazeAPI = new JSONCon();
		JSONArray response = tvmazeAPI.tryApi(json);
		return String.format("Svaret är tomt, boten under arbete");
	}

	/**
	 * Performs the search through the @NAME_OF_HOST_SITE
	 * 
	 * @param keywords The specific keywords to use for the search
	 * @return query through the API of @NAME_OF_HOST_SITE
	 */
	public static String search(String[] keywords) {
		// cast, episodes (nr of), characters, gallery (link to pictures), main link,
		// get specific episode (by season nr and episode nr)
		// show psych cast
		/*
		 * Switch on the following Show People (Person?) Seasons Episodes
		 */
		if (keywords.length <= 1) {
			if (keywords[0] == "?" || keywords[0].toLowerCase().contains("help"))
				return MANUAL;
			else if (keywords[0].toUpperCase().contains(API))
				return linkToAPI.toString();
			return ERROR_NOT_ENOUGH_KEYWORDS;
		}
		else if (keywords.length == 2) {
			if (keywords[0].toLowerCase().contains(show)) {
				// If entered string is something like [name of tv series] then return URL for
				// that series
				searchValue = SEARCH;
				searchValue += QUERY;
				searchValue += SHOWS;
			} else if (keywords[0].toLowerCase().contains(PEOPLE)) {
				searchValue = SEARCH;
				searchValue += QUERY;
				searchValue += PEOPLE;
			}

		} else if (keywords.length == 3)
			searchValue += keywords[0].toLowerCase() + EMBED + keywords[1];
		else
			return "Unsupported number of keywords";

		String response = jsonResponse();

		return response;
	}

}