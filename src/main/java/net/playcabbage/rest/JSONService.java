package net.playcabbage.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 

 
@Path("/mahstring")
public class JSONService {
 
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
//	public Track getTrackInJSON() {
	public String getStringInJSON() {
 
//		Track track = new Track();
//		track.setTitle("Enter Sandman");
//		track.setSinger("Metallica");
// 
//		return track;
		
		String x = "Here's mah String!";
		
		return x;
 
	}
 
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createTrackInJSON(Track track) {
	public Response createTrackInJSON(String stringx) {
 
		String result = "Mah String is... : " + stringx;
		return Response.status(201).entity(result).build();
 
	}
 
}