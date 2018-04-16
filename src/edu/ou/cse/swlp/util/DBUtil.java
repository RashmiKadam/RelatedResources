/**
 * 
 */
package edu.ou.cse.swlp.util;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import edu.ou.cse.swlp.constants.DBConstants;

/**
 * @author Rashmi Pethe
 *
 */
public class DBUtil {
	private static MongoClient client;
	
	/**
	 * Method to create the Mongo client connection.
	 * @return
	 */
	public static DB getConnection(){
		DB dbObject = null;
		try {
			client = new MongoClient(DBConstants.MONGODB_URI);
			
			if(client != null){
				dbObject = client.getDB("medialibrary");
			}	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return dbObject;
		
	}
	
	/**
	 * Close the Mongo client connection.
	 */
	public static void closeConnection(){
		client.close();
	}

}
