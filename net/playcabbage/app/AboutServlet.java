package net.playcabbage.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.playcabbage.app.db.CabbageConnection;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AboutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("about cabbage\n");
		out.print("Listing of pages at playcabbage.net...\n");
		
		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:8080/rest/pagelist/get");
		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		
		JSONArray j = response.getEntity(JSONArray.class);
		for (int x = 0;x< j.length();x++){
			String current = null;
			try {
				current = j.getString(x);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(current + "\n");
		}
		
//		try {
//			Client client = Client.create();
//			WebResource webResource = client
//					.resource("http://localhost:8080/capi/rest/pagelist/get");
//			ClientResponse response = webResource.accept("application/json")
//					.get(ClientResponse.class);
//			if (response.getStatus() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ response.getStatus());
//			}
//			
//			JSONArray j = response.getEntity(JSONArray.class);
//			for (int x = 0;x< j.length();x++){
//				String current = j.getString(x);
//				out.print(current + "\n");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		
		
		
//		InputStream csv = 
//				   AboutServlet.class.getResourceAsStream("newData.txt");
//		CabbageConnection cc = new CabbageConnection();
//		cc.readFromFile(csv);

//		try {
//			Client client = Client.create();
//			WebResource webResource = client
//					.resource("http://localhost:8080/rest/mahstring/get");
//			ClientResponse response = webResource.accept("application/json")
//					.get(ClientResponse.class);
//			if (response.getStatus() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ response.getStatus());
//			}
//			String output = response.getEntity(String.class);
//			out.print("Output from Server .... \n");
//			out.print(output);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}

}
