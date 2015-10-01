package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.databean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class dataimport extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public dataimport() {
		super();
		System.out.println("dataimport>>>");
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		System.out.println("destroy......");
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("null")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet>>");
		response.setContentType("text/html");
        databean search=new databean();
        Connection conn = null;
        ResultSet rs=null;
        search.setDB("ndn");//set database name
        conn=search.getConn();//connect to database
        try{
	    	String searchSql="SELECT  location,COUNT(*) from picdata GROUP BY location";                         	
	    	rs=search.executeSQL(searchSql);   
	    	JSONArray jsonArray=new JSONArray();
	    	JSONObject jsonObject = new JSONObject();
	    	while(rs.next())
	    	{   
	    		String location=rs.getString(1);
	    		System.out.println("location: " + location);
	    		if(location.equalsIgnoreCase("北京邮电大学")) location="bjyddx";
	    		if(location.equalsIgnoreCase("北邮")) location="by";
	    		if(location.equalsIgnoreCase("对外经贸大学")) location="dwjm";
	    		if(location.equalsIgnoreCase("北京理工大学")) location="bl";
	    		if(location.equalsIgnoreCase("北京大学")) location="bd";
	    		if(location.equalsIgnoreCase("北方工业大学")) location="bgd";
	    		if(location.equalsIgnoreCase("农展馆")) location="nzg";
	    		if(location.equalsIgnoreCase("牡丹园")) location="mdy";
	    		if(location.equalsIgnoreCase("芳草地")) location="fcd";
	    		if(location.equalsIgnoreCase("中关村")) location="zgc";
	    		if(location.equalsIgnoreCase("奥林匹克森林公园")) location="as";
	    		if(location.equalsIgnoreCase("天坛")) location="tt";
	    		if(location.equalsIgnoreCase("车公庄")) location="cgz";
	    		if(location.equalsIgnoreCase("朝阳公园")) location="cygy";
	    		int count=rs.getInt(2);
	    		System.out.println("count: " + count);
	            jsonObject.put(location, count);
	        }
	    	
	    	JSONObject jsonObject2 = new JSONObject();
	    	jsonArray.add(jsonObject);
	    	rs=search.executeSQL(searchSql); 
	    	
	    	while(rs.next()){
	    		
		        String location=rs.getString(1);
		
		        
		        for(int i=5;i<10;i++){
			        int j=i+1;
			    	searchSql="SELECT  location,COUNT(*) from picdata where time>'2015:0"+i+":00 00:00:00' and time<'2015:0"+j+":00 00:00:00' AND location='"+location+"'";                         	
			    	ResultSet rs2 = search.executeSQL(searchSql);
			    	rs2.next();
			    	int count=rs2.getInt(2);
			    	jsonObject2.put("at"+i+"month", count);  	
		        }
				if(location.equalsIgnoreCase("北京邮电大学")) location="bjyddx";
				if(location.equalsIgnoreCase("北邮")) location="by";
				if(location.equalsIgnoreCase("对外经贸大学")) location="dwjm";
				if(location.equalsIgnoreCase("北京理工大学")) location="bl";
				if(location.equalsIgnoreCase("北京大学")) location="bd";
				if(location.equalsIgnoreCase("北方工业大学")) location="bgd";
				if(location.equalsIgnoreCase("农展馆")) location="nzg";
				if(location.equalsIgnoreCase("牡丹园")) location="mdy";
				if(location.equalsIgnoreCase("芳草地")) location="fcd";
				if(location.equalsIgnoreCase("中关村")) location="zgc";
				if(location.equalsIgnoreCase("奥林匹克森林公园")) location="as";
				if(location.equalsIgnoreCase("天坛")) location="tt";
				if(location.equalsIgnoreCase("车公庄")) location="cgz";
				if(location.equalsIgnoreCase("朝阳公园")) location="cygy";
		        jsonObject2.put("location", location);
		    	jsonArray.add(jsonObject2);
		    	
	    	}
	    	System.out.println("jsonArray: " + jsonArray.toString());
	    	
	    	PrintWriter out = response.getWriter();
	    	System.out.println("jsonObject:" + jsonObject);
	    	out.println(jsonArray.toString());
	    	rs.close();
	    	conn.close();
	    	out.flush();
	    	out.close();
        }
        catch(Exception e){
        	e.printStackTrace();
        } 
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		System.out.println("init......");
	}

}