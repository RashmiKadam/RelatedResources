package edu.ou.cse.swlp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import edu.ou.cse.swlp.beans.Media;
import edu.ou.cse.swlp.services.RelatedResourcesService;
/**
 * @author Rashmi Pethe
 *
 */

@Path("/relatedresources")
public class RelatedResourcesController {
	
	/*@GET
	@Path("{tags}/{limit}/{key}")
	@Produces("application/json")
	public List<Media> getRelatedResources(@PathParam("tags") String tags, @PathParam("limit") int limit, @PathParam("key") String key){
		RelatedResourcesService service = new RelatedResourcesService();
		List<Media> mediaList = new ArrayList<Media>();
		if(service.isValidRequest(key)){
			mediaList = service.getRelatedResources(tags, limit);
		}else {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		return mediaList;
	}*/
	
	@GET
	@Path("/get")
	@Produces("application/json")
	public List<Media> getRelatedResources(@HeaderParam("tags") String tags, @HeaderParam("limit") int limit, @HeaderParam("key") String key){
		RelatedResourcesService service = new RelatedResourcesService();
		List<Media> mediaList = new ArrayList<Media>();
		if(service.isValidRequest(key)){
			mediaList = service.getRelatedResources(tags, limit);
		}else {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		return mediaList;
	}
}
