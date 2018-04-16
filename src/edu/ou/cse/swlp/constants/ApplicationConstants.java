/**
 * 
 */
package edu.ou.cse.swlp.constants;

/**
 * @author Rashmi Pethe
 * This class contains all the constants used in application.
 */
public class ApplicationConstants {

	//API Key used for the service
	public static String APPLICATION_REQUEST_KEY = "csi5510swlp";
	//The request type supported
	public static String REQUEST_METHOD_GET = "GET";
	//The response format supported
	public static String APPLICATION_JSON = "application/json";
	
	//Search URI for the external services
	public static String SEARCH_URI = "search/";
	
	//YouTube Data API specific constants
	public static String API_URL = "https://www.googleapis.com/youtube/v3/";
	public static String API_KEY ="AIzaSyD5HjaJoac_LUQg8E81O5Qv2eJkJHPtRkU";
	
	public static String API_KEY_PARAM ="key";
	public static String MAXRESULTS_PARAM = "maxResults";
	public static String PART_PARAM = "part";
	public static String TYPE_PARAM = "type";
	public static String SEARCH_QUERY_PARAM = "q";
	
	public static String MAXRESULTS = "10";
	public static String PART = "snippet";
	public static String TYPE = "video";
	//Constant for Part of the video URL 
	public static String YOUTUBE_URL = "https://www.youtube.com/watch?v=";
	
	
}
