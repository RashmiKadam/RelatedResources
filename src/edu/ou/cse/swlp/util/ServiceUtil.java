package edu.ou.cse.swlp.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.ou.cse.swlp.beans.Media;
import edu.ou.cse.swlp.constants.ApplicationConstants;

public class ServiceUtil {
	private static URL url;
	private static HttpURLConnection con;
	
	/**
	 * @param serviceName
	 * @return
	 */
	public static String invokeService(String apiURL, String serviceName, String params){
		String response = "";
		try {
			url = new URL(apiURL.concat(serviceName).concat(params));
			//System.out.println(url);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(ApplicationConstants.REQUEST_METHOD_GET); 
			con.setRequestProperty("Accept", "application/json");
			
			con.connect();
			//System.out.println(con.getContentType());
			if (con.getResponseCode() != 200) {
				System.out.println("Failed : HTTP error code : "
						+ con.getResponseCode());
				System.out.println("Connection failed...Server down");
			}

			//System.out.println("Output from Server .... \n");
			Scanner scanner = new Scanner(url.openStream());
			while (scanner.hasNext()) {
				response += scanner.nextLine();
			}
			//System.out.println(response);
			scanner.close();
			con.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return response;
	}
	
	/**
	 * @return
	 */
	public static String prepareVideoRequest(){
		String part = "";
		String maxResults = "";
		String searchQuery = "";
		String type = "";
		String apiKey = "";
		
		apiKey = apiKey.concat(ApplicationConstants.API_KEY_PARAM).concat("=").concat(ApplicationConstants.API_KEY);
		part = part.concat(ApplicationConstants.PART_PARAM).concat("=").concat(ApplicationConstants.PART);
		type = type.concat(ApplicationConstants.TYPE_PARAM).concat("=").concat(ApplicationConstants.TYPE);
		maxResults = maxResults.concat(ApplicationConstants.MAXRESULTS_PARAM).concat("=").concat(ApplicationConstants.MAXRESULTS);
		searchQuery = searchQuery.concat(ApplicationConstants.SEARCH_QUERY_PARAM).concat("=");
		//System.out.println("apiKey"+apiKey);
		//System.out.println("part"+part);
		
		String videoReq = "?";
		videoReq = videoReq.concat(apiKey).concat("&").concat(part).concat("&").concat(type).concat("&")
				.concat(maxResults).concat("&").concat(searchQuery);
		return videoReq;
	}
	
	/**
	 * @param param
	 * @throws UnsupportedEncodingException
	 * @return
	 */
	public static String encodeParams(String param) throws UnsupportedEncodingException{
		String encodedParam = "";
		if(param != null){
			encodedParam = URLEncoder.encode(param, "UTF-8");
		}
		return encodedParam;
	}
	
	/**
	 * @param videoLst
	 */
	public static void rankBasedSorting(List<Media> videoLst){
		//System.out.println("Before sort"+videoLst);
		Collections.sort(videoLst, new Comparator(){
			public int compare(Object o1, Object o2){
				if(((Media) o2).getSimilarityValue() != null && ((Media) o1).getSimilarityValue() != null){
					return (((Media) o2).getSimilarityValue()).compareTo(((Media) o1).getSimilarityValue());
				} else {
					return -1;
				}
			}
		});
	}
}