/*
 * Source for API info: https://www.tvmaze.com/api
 * Author: Marcus Laitala
 * Date: 2019-01-17 
 */

package com.sipi.groupOne.tv;

import java.net.*;
import java.util.Iterator;

import com.sipi.groupOne.connections.JSONCon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TVserier {

	private static URL linkToAPI;
	private static final String API = "API";
	private static final String NAME_OF_API_HOST_SITE = "TVMAZE";
	private static final String EMBED = "&embed=";
	private static final String SEARCH = "search/";
	private static final String QUERY = "?q=";
	private static final String SHOWS = "shows", PEOPLE = "people";
	private static final String MANUAL = "Uses the API of " + NAME_OF_API_HOST_SITE
			+ "to leverage results of querys, type API for more link. Example usage:\ntv show [name of tvshow]\ntv people [name of person]";
	private static final String ERROR_NOT_ENOUGH_KEYWORDS = "Need more keywords to perform a search. Type ? or help for more information";
	private static final String SHOW_MANUAL = "?", SHOW_MANUAL2 = "help";
	private static final String EMPTY_RESULT = "Jag fick inga svar med den sökningen!";
	private static final String TOO_MANY_PARAMETERS = "Har ej stöd för detta antal parametrar!";

	private static final int FIRST_ARGUMENT = 1, SECOND_ARGUMENT = 2, THIRD_ARGUMENT = 3, FOURTH_ARGUMENT = 4,
			MINIMUM_ARGUMENTS = 2, MIDDLE_VALUE_ARGUMENTS = 3, MAXIMUM_ARGUMENTS = 4;

	private static String searchValue = "";
	private static String result = "";
	private static String show = "show";
	
	private static boolean isResultEmpty = true;

	public TVserier(String sender, String[] searchValue) {
		try {
			linkToAPI = new URL("https://api.tvmaze.com/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		// Check and sort the parameters
		sortSearch(searchValue);

		String response = jsonResponse();
		// TODO, när ska denna metod anropas?
		
		//Make sure the result is readable and looks good
		formatResult(sender);

	}

	private boolean isResponseEmpty(JSONObject responseObject, JSONArray responseObjects) {
		if (responseObject.get("Response").equals("False") || responseObjects.isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * Performs the search through the @NAME_OF_HOST_SITE
	 * 
	 * @param keywords The specific keywords to use for the search
	 * @return query through the API of @NAME_OF_HOST_SITE
	 */
	private void sortSearch(String[] keywords) {
		// cast, episodes (nr of), characters, gallery (link to pictures), main link,
		// get specific episode (by season nr and episode nr)
		// show psych cast
		/*
		 * Switch on the following Show People (Person?) Seasons Episodes
		 */
		if (keywords.length <= MINIMUM_ARGUMENTS) {
			if (keywords[FIRST_ARGUMENT] == SHOW_MANUAL
					|| keywords[FIRST_ARGUMENT].toLowerCase().contains(SHOW_MANUAL2))
				result = MANUAL;
			else if (keywords[FIRST_ARGUMENT].toUpperCase().contains(API))
				result = linkToAPI.toString();
			result = ERROR_NOT_ENOUGH_KEYWORDS;
		} else if (keywords.length == MIDDLE_VALUE_ARGUMENTS) {
			if (keywords[FIRST_ARGUMENT].toLowerCase().contains(show)) {
				// If entered string is something like [name of tv series] then return URL for
				// that series
				searchValue = SEARCH;
				searchValue += QUERY;
				searchValue += SHOWS;
			} else if (keywords[FIRST_ARGUMENT].toLowerCase().contains(PEOPLE)) {
				searchValue = SEARCH;
				searchValue += QUERY;
				searchValue += PEOPLE;
			}

		} else if (keywords.length == MAXIMUM_ARGUMENTS)
			searchValue += keywords[FIRST_ARGUMENT].toLowerCase() + EMBED + keywords[SECOND_ARGUMENT];
		else
			result = TOO_MANY_PARAMETERS;

	}

	private String jsonResponse() {
		String json = linkToAPI + searchValue;
		JSONCon tvmazeAPI = new JSONCon();
		JSONArray responseObjects = tvmazeAPI.tryApi(json);

		// Picks the first row from the api-response
		JSONObject responseObject = (JSONObject) responseObjects.get(0);
		if (isResponseEmpty(responseObject, responseObjects))
			return EMPTY_RESULT;
		else {
			isResultEmpty = false;
			for (Object searchResults : responseObjects) {
				JSONObject o = (JSONObject) searchResults;
				System.out.println(o.toJSONString());
				JSONArray resultsArr = (JSONArray) o.get("Search");
				Iterator tvItr = resultsArr.iterator();
				// Iterates through them to get the information i wan't
				while (tvItr.hasNext()) {
					Object slide = tvItr.next();
					JSONObject tv = (JSONObject) slide;

					// Detta behöver anpassas specifik för vad man har sökt på
					// if (tv.get("Type").equals("movie"))
					// jsonValue += tv.get("Title") + ", från " + movie.get("Year") + "; ";
				}
			}
		}

		return "";
	}
	
	private void formatResult(String Sender)
	{
		if(isResultEmpty)
			result = "Hej " + Sender + "!" + EMPTY_RESULT;
		else
			result = "Hej " + Sender + "! Jag hittade ";
	}
	
	public String getAnswer() {		
		return result;
	}
}