package net.playcabbage.app;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.playcabbage.app.db.CabbageConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GreenServlet extends HttpServlet {

	CabbageConnection cc;
	
	protected String MIN_X;
	protected String MAX_X;
	protected String MIN_Y;
	protected String MAX_Y;
	protected String MIN_Z;
	protected String MAX_Z;

	  public void init(ServletConfig servletConfig) throws ServletException{
	    this.MIN_X = servletConfig.getInitParameter("min_x");
	    this.MAX_X = servletConfig.getInitParameter("max_x");
	    this.MIN_Y = servletConfig.getInitParameter("min_y");
	    this.MAX_Y = servletConfig.getInitParameter("max_y");
	    this.MIN_Z = servletConfig.getInitParameter("min_z");
	    this.MAX_Z = servletConfig.getInitParameter("max_z");
	  }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		cc = new CabbageConnection();
		
		if (req.getParameter("superid") != null) {
			cc.checkin(req.getQueryString());
		}
		
		makeGoogleGraphPage(resp);

	}
	
	protected void makeGoogleGraphPage(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println(MIN_X + "\n");
		out.println(MAX_X + "\n");
		out.println(MIN_Y + "\n");
		out.println(MAX_Y + "\n");
		out.println(MIN_Z + "\n");
		out.println(MAX_Z + "\n");
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
				"Transitional//EN\">\n" +
				"<html>\n" +
				"<head><title>Hello WWW</title>\n" +
				"<link href='http://fonts.googleapis.com/css?family=Press+Start+2P' rel='stylesheet' type='text/css'>\n" +
				"<link href='http://fonts.googleapis.com/css?family=Alef' rel='stylesheet' type='text/css'>\n" +
				"<style>\n" +
				"body {font-family: 'Alef', sans-serif;font-size: 18px;text-align:left;}\n" +
				"h1 {font-family: 'Press Start 2P', cursive;font-size: 48px;text-align:center;}\n" +
				"</style>\n" +

"<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n" + 
"<script type=\"text/javascript\">\n" +
"google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});\n" +
"google.setOnLoadCallback(drawChart);\n" +
"function drawChart() {\n" +
"var data = google.visualization.arrayToDataTable([\n" +
				"['Location', 'X', 'Y', 'Z', 'ZZ'],\n");

		List<String> gList = null;
		try {
			gList = prepForGraph(cc.getLastCheckins());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String s : gList) {
			out.println(s);
		}

		out.println("]);\n" +

				        "var options = {\n" +
				        "title: 'checkins',\n" +
				        "hAxis: {baseline: 0, direction: 1, maxValue: 153, title: 'x axis'},\n" +
				        "vAxis: {baseline: 0, direction: -1, maxValue: 88, title: 'y axis'},\n" +
				        "legend: {position: 'top', textStyle: {color: 'blue', fontSize: 16}},\n" +
				        "sizeAxis: {minValue: 00,  minSize: 10, maxSize: 20, maxValue: 20,  minSize: 30,  maxSize: 40},\n" +
				        "series: {'Level 00': {color: 'yellow', visibleInLegend: true}, 'Level 10': {color: 'lime', visibleInLegend: true}, 'Level 20': {color: 'green', visibleInLegend: true}},\n" +
				        "};\n" +

				        "var chart = new google.visualization.BubbleChart(document.getElementById('chart_div'));\n" +
				        "chart.draw(data, options);\n" +
				        "}\n" +
				"</script>\n");

		out.println("</head>\n" +
				"<body>\n" +
				"<h1>Let there be cabbage!</h1>\n" +
				"<br>");

		out.println("<div id=\"chart_div\" style=\"width: 900px; height: 500px;\"></div>\n");

		try {
			List<String> sList = cc.getLastCheckins();

			for (String s : sList) {
				out.println(s + "<br>");
			}

		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		out.println("</body></html>");
		out.close();
	}


	protected List<String> prepForGraph(List<String> ls) {
		List<String> newList = new ArrayList<String>();

		int previndex = -1;
		for (String s : ls) {
			if (s.length() == 15) {

				if (previndex > -1) {
					String ps = newList.get(previndex);
					newList.set(previndex, ps + ",");
				}

				String target = s.substring(s.length()-7);

				String x = target.substring(0, 3);
				String y = target.substring(3, 5);
				String z = target.substring(5);

				String level = "Level " + z;

				String buildString = "['" + z.toString() + "'," + x + "," + y + ",'" + level + "'," + z + "]";
				newList.add(buildString);
				previndex ++;

			}

		}

		String ps = newList.get(previndex);
		newList.set(previndex, ps + ",");

		return newList;

	}

}
