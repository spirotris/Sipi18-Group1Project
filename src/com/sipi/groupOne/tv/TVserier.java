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
	private static final String NAME_OF_API_HOST_SITE = "TV MAZE";
	private static final String EMBED = "&embed=";
	private static final String SEARCH = "search/";
	private static final String QUERY = "?q=";
	private static final String SHOWS = "shows", PEOPLE = "people";
	private static final String MANUAL = "Använder API från " + NAME_OF_API_HOST_SITE
			+ " för att svara på frågor. Skriv API för länk till den. Exempel på användning: tv show [namn på show] eller tv people [namn på person]";
	private static final String ERROR_NOT_ENOUGH_KEYWORDS = "Behöver fler nyckelord för att utföra en sökning. Skriv ? eller hjälp för mer inforamtion";
	private static final String SHOW_MANUAL = "?", SHOW_MANUAL2 = "help";
	private static final String EMPTY_RESULT = "Jag fick inga svar med den sökningen!";
	private static final String TOO_MANY_PARAMETERS = "För många nyckelord eller så har jag inte stöd för dessa ord";

	private static final int FIRST_ARGUMENT = 1, SECOND_ARGUMENT = 2, THIRD_ARGUMENT = 3, FOURTH_ARGUMENT = 4,
			MINIMUM_ARGUMENTS = 2, MIDDLE_VALUE_ARGUMENTS = 3, MAXIMUM_ARGUMENTS = 4;

	private static String searchValue = "";
	private static String result = "";
	private static String show = "show";

	private static boolean isResultEmpty = true;
	private static boolean isAskingForManual = false;
	private static boolean isSearchValid = false;

	public TVserier(String sender, String[] searchValue) {	
		
		try {
			linkToAPI = new URL("https://api.tvmaze.com/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			// Check and sort the parameters
			sortSearch(searchValue);			

			// No need to call an API if user is asking for the manual
			if (!isAskingForManual && isSearchValid) {
				// Actually make the call to the API and save the response
				result = jsonResponse();

				// Make sure the result is readable and looks good
				formatResult(sender);
			}
			
		} catch (Exception e) {
			result = e.getMessage();
			System.err.println();
		}
	}

	private boolean isResponseEmpty(JSONObject responseObject, JSONArray responseObjects) {
		if (responseObject.isEmpty() || responseObjects.isEmpty())
			return true;
		else
			return false;
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
		if (keywords.length < MINIMUM_ARGUMENTS) {
			result = MANUAL;
		} else if (keywords.length == MINIMUM_ARGUMENTS) {
			if (keywords[FIRST_ARGUMENT].contains(SHOW_MANUAL)
					|| keywords[FIRST_ARGUMENT].toLowerCase().contains(SHOW_MANUAL2)) {
				result = MANUAL;
				isAskingForManual = true;
				return;
			} else if (keywords[FIRST_ARGUMENT].toUpperCase().contains(API)) {
				result = linkToAPI.toString();
				isAskingForManual = false;
				isSearchValid = false;
				return;
			}
			result = ERROR_NOT_ENOUGH_KEYWORDS;
		} else if (keywords.length == MIDDLE_VALUE_ARGUMENTS) {
			if (keywords[FIRST_ARGUMENT].toLowerCase().contains(show)) {
				// If entered string is something like [name of tv series] then return URL for
				// that series
				searchValue = SEARCH + SHOWS + QUERY + keywords[SECOND_ARGUMENT];
				isSearchValid = true;
				isAskingForManual = false;
				return;
			} else if (keywords[FIRST_ARGUMENT].toLowerCase().contains(PEOPLE)) {
				searchValue = SEARCH + PEOPLE + QUERY + keywords[SECOND_ARGUMENT];
				isSearchValid = true;
				isAskingForManual = false;
				return;
			}

		} else if (keywords.length == MAXIMUM_ARGUMENTS) {
			// TODO När kommer den hit och hur ser det anropet ut?
			searchValue += keywords[FIRST_ARGUMENT].toLowerCase() + EMBED + keywords[SECOND_ARGUMENT];
			isSearchValid = true;
			isAskingForManual = false;
		} else {
			result = TOO_MANY_PARAMETERS;
			isSearchValid = false;
			isAskingForManual = false;
		}

	}

	private String jsonResponse() {
		String json = linkToAPI + searchValue;
		JSONCon tvmazeAPI = new JSONCon();  ;
		JSONArray responseObjects = (JSONArray) (tvmazeAPI.tryApi(json)).get(0);
		
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

	private void formatResult(String Sender) {
		String savedResult = result;
		if (isResultEmpty)
			result = "Hej " + Sender + "!" + EMPTY_RESULT;
		else
			result = "Hej " + Sender + "! Jag hittade " + savedResult;
	}

	public String getAnswer() {
		return result;
	}
}