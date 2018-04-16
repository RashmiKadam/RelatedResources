/**
 * 
 */
package edu.ou.cse.swlp.externalservices;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.ou.cse.swlp.beans.Media;
import edu.ou.cse.swlp.constants.ApplicationConstants;
import edu.ou.cse.swlp.util.ServiceUtil;

/**
 * @author Rashmi Pethe
 *
 */
public class YouTubeDataServiceClient {
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public ArrayList<Media> getVideos(String keyword) throws UnsupportedEncodingException{
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
		Media media;
		ArrayList<Media> mediaList = new ArrayList<Media>();
		String response = "";
		
		String params = ServiceUtil.prepareVideoRequest().concat(ServiceUtil.encodeParams(keyword));
		response = ServiceUtil.invokeService(ApplicationConstants.API_URL, ApplicationConstants.SEARCH_URI, params);
		
		System.out.println(response);
		try {
			String videoId = null;
			jsonObj = (JSONObject) jsonParser.parse(response);
			JSONArray jsonArray = (JSONArray) jsonObj.get("items");
			if(jsonArray != null && !jsonArray.isEmpty()){
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject videoObj = (JSONObject) jsonArray.get(i);
					media = new Media();
					JSONObject idObj = (JSONObject) videoObj.get("id");
					
					videoId = idObj.get("videoId").toString();
					//System.out.println("VideoId"+videoId);
					media.setID(videoId);
					
					JSONObject snippetObj = (JSONObject) videoObj.get("snippet");
					media.setTitle(snippetObj.get("title").toString());
					//System.out.println("Video Title"+snippetObj.get("title"));
					
					media.setDescription(snippetObj.get("description").toString());
					media.setMediaType("Video");
					media.setResourceURL(ApplicationConstants.YOUTUBE_URL.concat(videoId));
					mediaList.add(media);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mediaList;
	}
	
	/**
	 * @param keyword
	 * @param dbPediaConcept
	 * @return
	 */
	public Collection getRelatedVideos(String keyword, String dbPediaConcept){
		ArrayList<Media> videoLst = new ArrayList<>();
		try {
			videoLst = getVideos(keyword);
			
			if(dbPediaConcept.equalsIgnoreCase("Skip")) {
				return videoLst;
			} else {
				videoLst = updateVideoSimilarityRank(videoLst, dbPediaConcept);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return videoLst;
	}
	
	/**
	 * @param videoLst
	 * @param dbPediaConcept
	 * @return
	 */
	private ArrayList<Media> updateVideoSimilarityRank(ArrayList<Media> videoLst, String dbPediaConcept){
		String text1;
		String params;
			
		SemanticSimilarityServiceClient semClient = new SemanticSimilarityServiceClient();
		try {
			for (Media media : videoLst) {
				text1 = ServiceUtil.encodeParams(dbPediaConcept);
				params = "text1=".concat(text1).concat("&").concat("url2=").concat(media.getResourceURL()).concat("&").concat("token=");
				
				Double similarityRank = semClient.invokeSemSimilarityAPI(dbPediaConcept, params);
				//System.out.println(similarityRank);
				if(similarityRank != null){
					media.setSimilarityValue(similarityRank);;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return videoLst;
	}
}
