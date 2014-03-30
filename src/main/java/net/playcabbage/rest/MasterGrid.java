package net.playcabbage.rest;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import net.playcabbage.app.db.CabbageConnection;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

@Path("/mastergrid")
public class MasterGrid {
	
	@GET
	@Path("/getjson")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getMasterGridList_JSON() throws NamingException, SQLException, JSONException {
		CabbageConnection cc = new CabbageConnection();
		return cc.getMasterGridListJSON();
	}
	
	@GET
	@Path("/getxml")
	@Produces(MediaType.APPLICATION_XML)
	public String getMasterGridList_XML() throws NamingException, SQLException, ParserConfigurationException, TransformerException {
		CabbageConnection cc = new CabbageConnection();
		return cc.getMasterGridListXML();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(String stringx) {
		String result = "Mah String is... : " + stringx;
		return Response.status(201).entity(result).build();
	}
 
}
