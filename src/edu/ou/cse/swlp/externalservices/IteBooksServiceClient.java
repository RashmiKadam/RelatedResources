/**
 * 
 */
package edu.ou.cse.swlp.externalservices;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
public class IteBooksServiceClient {
	private String apiURL = "http://it-ebooks-api.info/v1/";
	
	
	/**
	 * @param keyword
	 * @return
	 */
	public ArrayList<Media> searchBooks(String keyword){
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		String response;
		Media media;
		ArrayList<Media> mediaList = new ArrayList<Media>();
		response = ServiceUtil.invokeService(apiURL, ApplicationConstants.SEARCH_URI, keyword);
		System.out.println(response);
		try {
			jsonObj = (JSONObject) jsonParser.parse(response);
			JSONArray jsonArray = (JSONArray) jsonObj.get("Books");
			if(jsonArray != null && !jsonArray.isEmpty()){
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject bookObj = (JSONObject) jsonArray.get(i);
					media = new Media();
					media.setID(bookObj.get("ID").toString());
					media.setTitle(bookObj.get("Title").toString());
					media.setDescription(bookObj.get("Description").toString());
					media.setImage(bookObj.get("Image").toString());
					media.setISBN(bookObj.get("isbn").toString());
					media.setMediaType("Text");
					mediaList.add(media);
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mediaList;
	}
	
	/**
	 * 
	 */
	public void getBooksDetails(){
		
	}
	
	/**
	 * @param keyword
	 * @param dbPediaConcept
	 * @return
	 */
	public ArrayList<Media> getRelatedBooks(String keyword, String dbPediaConcept) {
		ArrayList<Media> mediaLst = new ArrayList<>();

		mediaLst = searchBooks(keyword);
		
		if(dbPediaConcept.equalsIgnoreCase("Skip")) {
			return mediaLst;
		} else {
			mediaLst = updateBookSimilarityRank(mediaLst, dbPediaConcept);
			return mediaLst;
		}
	}
	
	/**
	 * @param mediaLst
	 * @param dbPediaConcept
	 * @return
	 */
	private ArrayList<Media> updateBookSimilarityRank(ArrayList<Media> mediaLst, String dbPediaConcept){
		String text1, text2;
		String params;
		SemanticSimilarityServiceClient semClient = new SemanticSimilarityServiceClient();
		try {
			for (Media media : mediaLst) {
				String description = media.getDescription();
				text1 = ServiceUtil.encodeParams(dbPediaConcept);
				text2 = ServiceUtil.encodeParams(description);
				params = "text1=".concat(text1).concat("&").concat("text2=").concat(text2).concat("&").concat("token=");
				
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
		return mediaLst;
	}
}