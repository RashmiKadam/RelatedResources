/**
 * 
 */
package edu.ou.cse.swlp.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.ou.cse.swlp.beans.Media;
import edu.ou.cse.swlp.constants.ApplicationConstants;
import edu.ou.cse.swlp.externalservices.DBPediaLookupServiceClient;
import edu.ou.cse.swlp.externalservices.IteBooksServiceClient;
import edu.ou.cse.swlp.externalservices.YouTubeDataServiceClient;
import edu.ou.cse.swlp.util.ServiceUtil;

/**
 * @author Rashmi Pethe
 *
 */

public class RelatedResourcesService {

	/**
	 * @param tags
	 * @param limit
	 * @return
	 */
	public List<Media> getRelatedResources(String tags, int limit){
		List<Media> mediaLst = new ArrayList<Media>();
		
		/*RelatedResourcesDAO rrDao = new RelatedResourcesDAOImpl();
		Media media = rrDao.fetchResources(tags);
		mediaLst.add(media);
		mediaLst.clear();*/
		String concept = "";
		if(mediaLst.isEmpty()){
			DBPediaLookupServiceClient dbPediaClient = new DBPediaLookupServiceClient();
			concept = dbPediaClient.lookUpDBPediaConcept(tags);
			
			IteBooksServiceClient bookClient = new IteBooksServiceClient();
			mediaLst.addAll(bookClient.getRelatedBooks(tags, concept));
			
			YouTubeDataServiceClient videoClient = new YouTubeDataServiceClient();
			mediaLst.addAll(videoClient.getRelatedVideos(tags, concept));
		}
		if(mediaLst == null || mediaLst.isEmpty()){
			System.out.println("No related resources");
		}else{
			if(concept != null && "Skip".equalsIgnoreCase(concept)){
				Collections.shuffle(mediaLst);
			}else {
				ServiceUtil.rankBasedSorting(mediaLst);
			}
			if(mediaLst.size() >= limit){
				mediaLst =  mediaLst.subList(0, limit);
			} else {
				return mediaLst;
			}
		}
		
		return mediaLst;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public boolean isValidRequest(String key){
		boolean valid = false;
		if(ApplicationConstants.APPLICATION_REQUEST_KEY.equals(key)){
			valid = true;
		}
		return valid;
	}
}