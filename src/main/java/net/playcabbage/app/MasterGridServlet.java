package net.playcabbage.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import net.playcabbage.model.GridCellBean;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MasterGridServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("Master grid\n");
		out.print("Listing...\n");

		try {
			getAsJSON(out);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private void getAsJSON(PrintWriter out) throws JSONException {
		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:8080/rest/mastergrid/getjson");
		ClientResponse response = webResource.accept("application/json")
				.get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		JSONArray j = response.getEntity(JSONArray.class);

		List<GridCellBean> bl = new ArrayList<GridCellBean>();
		for (int x = 0; x < j.length();x++){
			JSONObject obj = j.getJSONObject(x);
			String json = obj.toString();
			Gson gson = new Gson();
			GridCellBean bean = gson.fromJson(json,GridCellBean.class);
			bl.add(bean);
		}

		for (int i = 0; i< j.length();i++){
			out.println("getGridcellmasterID: " + bl.get(i).getGridcellmasterID());
			out.println("getZ: " + bl.get(i).getZ());
			out.println("getY: " + bl.get(i).getY());
			out.println("getStartx: " + bl.get(i).getStartx());
			out.println("getEndx: " + bl.get(i).getEndx());
			out.println("getCellcategoryID: " + bl.get(i).getCellcategoryID());
			out.println();
		}

	}
	
	
	private void getAsXML(PrintWriter out) throws SAXException, IOException, ParserConfigurationException, TransformerException {

		try {
			Client client = Client.create();
			WebResource webResource = client
					.resource("http://localhost:8080/rest/mastergrid/getxml");
			ClientResponse response = webResource.accept("application/xml")
					.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String is = response.getEntity(String.class);
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			Document doc = docBuilder.parse(new InputSource(new StringReader(is)));

			NodeList nodeList = doc.getElementsByTagName("*");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					// do something with the current element
					System.out.println(node.getNodeName());
				}
			}

		} catch (RuntimeException e) {
			throw e;
		}

	}


}










//InputStream csv = 
//AboutServlet.class.getResourceAsStream("newData.txt");
//CabbageConnection cc = new CabbageConnection();
//cc.readFromFile(csv);
