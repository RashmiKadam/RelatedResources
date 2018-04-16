/**
 * 
 */
package edu.ou.cse.swlp.constants;

/**
 * @author Rashmi Pethe
 * This class contains all the constants specific to Database Layer.
 */
public class DBConstants {
	
	//Constant variables for document attributes
	public static String VIDEO_ID = "videoId";
	public static String TAGS = "tags";
	public static String TITLE = "title";
	public static String DESCRIPTION = "description";
	public static String RESOURCEURL = "resourceURL";
	
	//Constant variable for database connection
	public static String COLLECTION_NAME = "media";
	//public static String HOST_NAME = "localhost";
	//public static Integer PORT = 27017;
	//URI for MongoDB connection
	public static String MONGODB_URI ="mongodb://swlp:Unruptured2018@ds121015.mlab.com:21015/resources-service-db";
}
