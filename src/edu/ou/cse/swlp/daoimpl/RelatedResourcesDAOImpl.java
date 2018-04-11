package edu.ou.cse.swlp.daoimpl;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import edu.ou.cse.swlp.beans.Media;
import edu.ou.cse.swlp.constants.DBConstants;
import edu.ou.cse.swlp.dao.RelatedResourcesDAO;
import edu.ou.cse.swlp.util.DBUtil;

/**
 * @author Rashmi Pethe
 *
 */
public class RelatedResourcesDAOImpl implements RelatedResourcesDAO{

	/* (non-Javadoc)
	 * @see edu.ou.cse.swlp.dao.RelatedResourcesDAO#fetchResources()
	 */
	@Override
	public Media fetchResources(String tags) {
		
		Media media = null;
			
			
		DBCollection collection = DBUtil.getConnection().getCollection(DBConstants.COLLECTION_NAME);
		//DBCollection results = collection.find({"tags:tags"});
		
		/*media = new Media();
		media.setID(obj.get(DBConstants.VIDEO_ID).toString());
		media.setTags(obj.get(DBConstants.TAGS).toString());
		media.setTitle(obj.get(DBConstants.TITLE).toString());
		media.setDescription(obj.get(DBConstants.DESCRIPTION).toString());
		media.setResourceURL(obj.get(DBConstants.RESOURCEURL).toString());*/
		
		DBUtil.closeConnection(); 
		return media;
	}
}