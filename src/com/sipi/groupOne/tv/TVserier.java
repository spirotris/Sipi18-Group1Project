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
	private static final String SHOW = "show", SHOWS = "shows", PEOPLE = "people", PERSON = "person", NAME = "name",
			SUMMARY = "summary", SINGLE_SEARCH = "single";
	private static final String MANUAL = "Använder API från " + NAME_OF_API_HOST_SITE
			+ " för att svara på frågor. Skriv API för länk till den. Exempel på användning: tv show [namn på show] eller tv people [namn på person]";
	private static final String ERROR_NOT_ENOUGH_KEYWORDS = "Behöver fler nyckelord för att utföra en sökning. Skriv ? eller hjälp för mer inforamtion";
	private static final String SHOW_MANUAL = "?", SHOW_MANUAL2 = "help";
	private static final String EMPTY_RESULT = "Jag fick inga svar med den sökningen!";
	private static final String TOO_MANY_PARAMETERS = "För många nyckelord eller så har jag inte stöd för dessa ord";
	private static final String REMOVE_HTML_REGEX = "(<.*?>)|(&.*?;)|([ ]{2,})";

	private static final int FIRST_ARGUMENT = 1, SECOND_ARGUMENT = 2, MINIMUM_ARGUMENTS = 2, MIDDLE_VALUE_ARGUMENTS = 3,
			MAXIMUM_ARGUMENTS = 4, MAXIMUM_NUMBER_OF_RESULTS = 10;

	private static String searchValue = "";
	private static String result = "";

	private static boolean isResultEmpty = true;
	private static boolean isAskingForManual = false;
	private static boolean isSearchValid = false;

	private static boolean isSearchingForShow = false;
	private static boolean isSearchingForShows = false;
	private static boolean isSearchingForPeople = false;
	private static boolean isSearchingForMultiple = false;

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
				jsonResponse();

				// Make sure the result is readable and looks good
				formatResult(sender);
			}

		} catch (Exception e) {
			result = e.getMessage();
			System.err.println();
		} finally {
			resetBooleans();
		}
	}

	// For when the bot is done with one question and another is asked, the state
	// ofthe booleans is remembered
	private void resetBooleans() {
		isResultEmpty = true;
		isAskingForManual = false;
		isSearchingForShow = false;
		isSearchingForShows = false;
		isSearchingForPeople = false;
		isSearchingForMultiple = false;
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
		 * TODO change below first tier if to switch case on keywords.length Switch on
		 * the following Show People (Person?) Seasons Episodes
		 */
		switch (keywords.length) {
		case 0:
		case 1:
			result = MANUAL;
			break;
		case MINIMUM_ARGUMENTS:
			if (keywords[FIRST_ARGUMENT].contains(SHOW_MANUAL)
					|| keywords[FIRST_ARGUMENT].toLowerCase().contains(SHOW_MANUAL2)) {
				result = MANUAL;
				isAskingForManual = true;
			} else if (keywords[FIRST_ARGUMENT].toUpperCase().contains(API)) {
				result = linkToAPI.toString();
				isAskingForManual = false;
				isSearchValid = false;
			}
			result = ERROR_NOT_ENOUGH_KEYWORDS;
			break;
		case MIDDLE_VALUE_ARGUMENTS:
			if (keywords[FIRST_ARGUMENT].toLowerCase().equals(SHOW)) {
				// If entered string is something like [name of tv series] then return URL for
				// that series
				searchValue = SINGLE_SEARCH + SEARCH + SHOWS + QUERY + keywords[SECOND_ARGUMENT];
				isSearchValid = true;
				isAskingForManual = false;
				isSearchingForShow = true;
				isSearchingForMultiple = false;
			} else if (keywords[FIRST_ARGUMENT].toLowerCase().contains(SHOWS)) {
				searchValue = SEARCH + SHOWS + QUERY + keywords[SECOND_ARGUMENT];
				isSearchValid = true;
				isAskingForManual = false;
				isSearchingForShows = true;
				isSearchingForMultiple = true;
			} else if (keywords[FIRST_ARGUMENT].toLowerCase().contains(PEOPLE)
					|| keywords[FIRST_ARGUMENT].toLowerCase().contains(PERSON)) {
				searchValue = SEARCH + PEOPLE + QUERY + keywords[SECOND_ARGUMENT];
				isSearchValid = true;
				isAskingForManual = false;
				isSearchingForMultiple = true;
			}
			break;
		case MAXIMUM_ARGUMENTS:
			// TODO När kommer den hit och hur ser det anropet ut?
			searchValue += keywords[FIRST_ARGUMENT].toLowerCase() + EMBED + keywords[SECOND_ARGUMENT];
			isSearchValid = true;
			isAskingForManual = false;
			break;
		default:
			result = TOO_MANY_PARAMETERS;
			isSearchValid = false;
			isAskingForManual = false;
			break;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void jsonResponse() {
		String json = linkToAPI + searchValue;
		JSONCon tvmazeAPI = new JSONCon();
		JSONArray responseObjects = tvmazeAPI.tryApi(json);

		if (responseObjects.isEmpty() || responseObjects == null) {
			isResultEmpty = true;
			return;
		} else {
			isResultEmpty = false;
			// Picks the first row from the api-response
			for (Object searchResults : responseObjects) {
				JSONArray resultsArr = null;
				JSONObject resultObj;
				Iterator serieItr;
				int nrOfResultsCounted = 0;

				if (isSearchingForMultiple) {
					resultsArr = (JSONArray) searchResults;
					serieItr = resultsArr.iterator();
				} else {
					resultObj = (JSONObject) searchResults;
					resultsArr = new JSONArray();
					resultsArr.add(resultObj);
					serieItr = resultsArr.iterator();
				}

				// Iterates through the map to get the information i want
				while (serieItr.hasNext()) {

					Object slide = serieItr.next();
					JSONObject serie = (JSONObject) slide;
					System.out.println(serie.toJSONString());
					if (isSearchingForShow) {
						// Visar serien och en beskrivninga för bästa träffen av sökresultatet
						result += serie.get(NAME) + ": " + serie.get(SUMMARY);
						result = result.replaceAll(REMOVE_HTML_REGEX, "");
						return;
					} else if (isSearchingForShows) {
						// Vill man ha flera träffar söker man med "tv shows [namn på serie]"
						if (!result.isEmpty())
							result += ", ";

						JSONObject nameOfShow = (JSONObject) serie.get(SHOW);
						result += nameOfShow.get(NAME);
						nrOfResultsCounted++;
						if (nrOfResultsCounted == MAXIMUM_NUMBER_OF_RESULTS)
							return;
						continue;
					} else if (isSearchingForPeople) {
						/*
						 * if (!result.isEmpty()) result += ", "; JSONObject nameOfPerson = (JSONObject)
						 * serie.get(PEOPLE); result += nameOfPerson.get(NAME); nrOfResultsCounted++; if
						 * (nrOfResultsCounted == MAXIMUM_NUMBER_OF_RESULTS) return; continue;
						 */
					} else {
						result = "Detta kommando stöds inte";
					}
				}
			}
		}
	}

	private void formatResult(String Sender) {
		if (isResultEmpty)
			result = "Hej " + Sender + "!" + EMPTY_RESULT;
		else
			result = "Hej " + Sender + "! Jag hittade " + result;
	}

	public String getAnswer() {
		return result;
	}
}