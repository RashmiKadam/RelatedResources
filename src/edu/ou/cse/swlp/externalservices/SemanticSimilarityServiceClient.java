/**
 * 
 */
package edu.ou.cse.swlp.externalservices;

import java.io.UnsupportedEncodingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.ou.cse.swlp.util.ServiceUtil;

/**
 * @author Rashmi Pethe
 *
 */
public class SemanticSimilarityServiceClient {
	private String apiURL = "https://api.dandelion.eu/datatxt/";
	private String serviceName = "sim/v1/?";
	private String apiToken = "0b905b07c43f445badd9e0b5f542d1aa";

	/**
	 * @param dbPediConcept
	 * @param params
	 * @return
	 */
	public Double invokeSemSimilarityAPI(String dbPediConcept, String params){
		JSONParser jsonParser = new JSONParser();
		JSONObject responseObj;
		Double similarityValue = null;
		String response = "";
		try {
			params = params.concat(apiToken);
			response = ServiceUtil.invokeService(apiURL, serviceName, params);
			//System.out.println(response);
			responseObj =  (JSONObject) jsonParser.parse(response);
			similarityValue = (Double) responseObj.get("similarity");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return similarityValue;
	}
}
