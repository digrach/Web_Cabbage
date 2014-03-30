package net.playcabbage.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.playcabbage.app.db.CabbageConnection;

import org.codehaus.jettison.json.JSONArray;

@Path("/pagelist")
public class PageList {
 
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getPageList() throws NamingException, SQLException {
		CabbageConnection cc = new CabbageConnection();
		JSONArray j = new JSONArray(cc.getPagePathList());
		return j;
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(String stringx) {
		String result = "Mah String is... : " + stringx;
		return Response.status(201).entity(result).build();
	}
 
}
