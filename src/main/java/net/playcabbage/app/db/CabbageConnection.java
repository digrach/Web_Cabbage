package net.playcabbage.app.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class CabbageConnection {

	public CabbageConnection() {

	}

	public void checkin(String urlString) throws ServletException {
		try {
			Connection connection = getConnection();
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("insert into checkin (urlstring) values (?)");
				preparedStatement.setString(1, urlString);
				preparedStatement.execute();
			} finally {
				connection.close();
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public List<String> getLastCheckins() throws NamingException, SQLException {
		Connection connection = getConnection();
		List<String> rList = null;
		PreparedStatement preparedStatement = connection.prepareStatement("select * from checkin");
		if (preparedStatement.execute()) {
			ResultSet rs = preparedStatement.getResultSet();
			rList = new ArrayList<String>();
			while (rs.next() != false){
				rList.add(rs.getString("urlstring"));
				// Get the timestamp column in the resultset.
				Timestamp timestamp = rs.getTimestamp("mytimestamp");
				// Declare the Locale (of the mysql instance).
				Locale remoteLocal = new Locale("en_US");
				// Declare the TimeZone (of the mysql instance);
				TimeZone remoteTimeZone = TimeZone.getTimeZone("EST");
				// Get a Calendar object which is set to the above.
				Calendar remoteCal = Calendar.getInstance(remoteTimeZone, remoteLocal);
				// Set Calendar time using the timestamp.
				remoteCal.setTime(timestamp);
				// Add 10 from Calendar hours (EST to Australia/Melbourne); 
				remoteCal.add(Calendar.HOUR, +10);
				rList.add(remoteCal.getTime().toString());
			}
		}
		connection.close();
		return rList;
	}

	public List<String> getPageList() throws NamingException {
		List<String> l = new ArrayList<String>();
		int colCount;
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pages INNER JOIN pagecategory ON pages.pagecategoryID=pagecategory.pagecategoryID");
			if (preparedStatement.execute()) {
				ResultSet rs = preparedStatement.getResultSet();
				ResultSetMetaData md = rs.getMetaData();
				colCount = md.getColumnCount();
				while(rs.next() != false) {
					String current = "Result...";
					for (int x = 1; x <= colCount; x ++) {
						current += md.getColumnName(x) + ": " + rs.getString(x) + " ";
					}
					l.add(current);
				}
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getPagePathList() throws NamingException {
		List<String> l = new ArrayList<String>();
		int colCount;
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT pages.pagename as pagename, pagecategory.pagecategoryname as catname FROM pages INNER JOIN pagecategory ON pages.pagecategoryID=pagecategory.pagecategoryID");
			if (preparedStatement.execute()) {
				ResultSet rs = preparedStatement.getResultSet();
				ResultSetMetaData md = rs.getMetaData();
				colCount = md.getColumnCount();
				while(rs.next() != false) {
					String current = "/";
					current += rs.getString("catname") + "/" + rs.getString("pagename");
					l.add(current);
				}
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public JSONArray getMasterGridListJSON() throws NamingException, SQLException, JSONException {
		JSONArray jArray = new JSONArray();
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from gridcellmaster limit 100");

		if (preparedStatement.execute()) {
			ResultSet rs = preparedStatement.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();

				for (int i=1; i<numColumns+1; i++) {
					String column_name = rsmd.getColumnName(i);

					if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
						obj.put(column_name, rs.getArray(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
						obj.put(column_name, rs.getInt(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
						obj.put(column_name, rs.getBoolean(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
						obj.put(column_name, rs.getBlob(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
						obj.put(column_name, rs.getDouble(column_name)); 
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
						obj.put(column_name, rs.getFloat(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
						obj.put(column_name, rs.getInt(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
						obj.put(column_name, rs.getNString(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
						obj.put(column_name, rs.getString(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
						obj.put(column_name, rs.getInt(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
						obj.put(column_name, rs.getInt(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
						obj.put(column_name, rs.getDate(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
						obj.put(column_name, rs.getTimestamp(column_name));   
					}
					else{
						obj.put(column_name, rs.getObject(column_name));
					}
				}

				jArray.put(obj);
			}

		}

		connection.close();



		return jArray;
	}

	public List<String> getMasterGridList() throws NamingException, SQLException {
		List<String> l = new ArrayList<String>();
		int colCount = 0;

		try {

			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from gridcellmaster limit 100");

			if (preparedStatement.execute()) {
				ResultSet rs = preparedStatement.getResultSet();
				ResultSetMetaData md = rs.getMetaData();
				colCount = md.getColumnCount();


				while(rs.next() !=false) {
					String current = "";
					int start = 0;
					for (int x = 1; x <= colCount; x ++) {
						if (start > 0) {
							current += ",";
						}
						start ++;
						current += rs.getString(x);
					}
					l.add(current);
				}

			}

			connection.close();

		} catch (SQLException e) {
			throw e;
		}


		return l;
	}

	public String getMasterGridListXML() throws NamingException, SQLException, ParserConfigurationException, TransformerException {		

		List<String> l = new ArrayList<String>();
		int colCount = 0;
		ResultSet rs = null;
		Connection connection = null;
		StreamResult sr = null;
		try {

			connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from gridcellmaster limit 100");

			if (preparedStatement.execute()) {
				rs = preparedStatement.getResultSet();
				ResultSetMetaData md = rs.getMetaData();
				colCount = md.getColumnCount();

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.newDocument();

				Element results = doc.createElement("Results");  
				doc.appendChild(results); 

				while (rs.next())  
				{  
					Element row = doc.createElement("Cell");  
					results.appendChild(row);  

					for (int i = 1; i <= colCount; i++)  
					{  
						String columnName = md.getColumnName(i);  
						Object value      = rs.getObject(i);  

						Element node = doc.createElement(columnName);  
						node.appendChild(doc.createTextNode(value.toString()));  
						row.appendChild(node);  
					}  
				} 

				DOMSource domSource = new DOMSource(doc);
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
				transformer.setOutputProperty
				("{http://xml.apache.org/xslt}indent-amount", "4");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				java.io.StringWriter sw = new java.io.StringWriter();
				sr = new StreamResult(sw);
				transformer.transform(domSource, sr);

			}

		} catch (SQLException e) {
			throw e;
		}

		connection.close();
		return sr.getWriter().toString();
	}

	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/cabbagedb");
		Connection connection = ds.getConnection();
		return connection;
	}

	//	public void readFromFile(InputStream inputstream) throws ServletException {
	//
	//		Scanner sc = null;
	//		sc = new Scanner(inputstream);
	//		//sc = new Scanner("newData.txt");
	//
	//		while (sc.hasNext()) {
	//
	//			String tokenString = sc.next();
	//			String splitArray[] = tokenString.split(",");
	//
	//			int z = Integer.parseInt(splitArray[0]);
	//			int y = Integer.parseInt(splitArray[1]);
	//			int startX = Integer.parseInt(splitArray[2]);
	//			int endX = Integer.parseInt(splitArray[3]);
	//			String cellCategory = splitArray[4];
	//			
	//			int cellID = 0;
	//			if (cellCategory.equals("p")) {
	//				cellID = 1;
	//			}
	//			if (cellCategory.equals("b")) {
	//				cellID = 2;
	//			}
	//			if (cellCategory.equals("c")) {
	//				cellID = 3;
	//			}
	//			if (cellCategory.equals("r")) {
	//				cellID = 4;
	//			}
	//			if (cellCategory.equals("n")) {
	//				cellID = 5;
	//			}
	//
	////			for (int xCounter = startX; xCounter < endX + 1; xCounter ++ ) {
	////				insertIntoMasterGrid(z,y,startX,endX,cellID);
	////			}
	//			
	//			insertIntoMasterGrid(z,y,startX,endX,cellID);
	//
	//		}
	//
	//	}
	//
	//	public void insertIntoMasterGrid(int z,int y,int startx,int endx, int cellCatID) throws ServletException {
	//		try {
	//			Connection connection = getConnection();
	//			try {
	//				PreparedStatement preparedStatement = connection.prepareStatement("insert into gridcellmaster (z,y,startx,endx,cellcategoryID) values (?,?,?,?,?)");
	//				preparedStatement.setInt(1, z);
	//				preparedStatement.setInt(2, y);
	//				preparedStatement.setInt(3, startx);
	//				preparedStatement.setInt(4, endx);
	//				preparedStatement.setInt(5, cellCatID);
	//				preparedStatement.execute();
	//			} finally {
	//				connection.close();
	//			}
	//		} catch (Exception e) {
	//			throw new ServletException(e);
	//		}
	//	}


}
