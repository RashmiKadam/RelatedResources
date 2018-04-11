package edu.ou.cse.swlp.dao;

import edu.ou.cse.swlp.beans.Media;

/**
 * @author Rashmi Pethe
 *
 */
public interface RelatedResourcesDAO {
	
	/**
	 * @return Media
	 */
	public Media fetchResources(String tags);

}
