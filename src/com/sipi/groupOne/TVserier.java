/*
 * Source: https://www.tvmaze.com/api
 * Author: Marcus Laitala
 * Date: 2019-01-17 
 */

// Example cases: tv psych
import java.net.*;
class TVserier {
	
	private URL api; //Link to the API
	private URL serviceAPI;
	private String command = "";
	
	TVserier() {
		try {
			api = new URL("https://www.tvmaze.com/api");
			serviceAPI = new URL("/search/shows?q=");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String search (String[] keywords) {
		/*Switch on the following
		 * Show
		 * People (Person?)
		 * Seasons
		 * Episodes
		 */
		return "";
	}	
}