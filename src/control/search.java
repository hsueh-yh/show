package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import model.byDay;
import model.databean;

import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonFactory; 
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

public class search  extends ActionSupport {

	private HttpServletResponse response; 
	
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void byDay()
			throws ServletException, IOException {
		
		response  = ServletActionContext.getResponse();
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	System.out.println("location: " + getLocation());
		System.out.println("\n[byDay]\n");
        databean search=new databean();
        Connection conn = null;
        ResultSet rs=null;
        search.setDB("ndn");//set database name
        conn=search.getConn();//connect to database
        String condition = "where location = ";
        if( location==null )
        	condition = " ";
        else
        	condition += "'"+location+"'";
        String countDayNum = "select DATE_FORMAT(time,'%Y-%m-%d') as time,count(*) as count "
        						+ "from picdata "+ condition +" group by DATE_FORMAT(time,'%Y-%m-%d');";
        System.out.println("\ncountDayNum: "+countDayNum);
        try{
	    	String searchSql=countDayNum;    
	    	System.out.println("\nSearching database...\n");
	    	rs=search.executeSQL(searchSql);   
            
            //JSONArray jsonArray=new JSONArray();
        	//JSONObject jsonObject = new JSONObject();
	    	JsonGenerator jsonGenerator = null;
	    	List<byDay> list = new ArrayList<byDay>();
	    	
	    	System.out.println("\nAnalysis...\n");
	    	
	    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	Date date;
	    	String outStr = "[";
	    	while(rs.next())
	    	{
	    		if(outStr.length() != 1)
	    			outStr+=',';
	    		String time=rs.getString("time");
	    		System.out.println("time: " + time);
	    		
	    		date=simpleDateFormat.parse(time);
	    	    Long timeStemp = date.getTime();
	    		
	    		int count = rs.getInt("count");
	    		System.out.println("count: " + count);
	    		
	    		list.add(new byDay(timeStemp,count));
	    		outStr+="["+timeStemp+",";
	    		outStr+=count+"]";
	        }
	    	outStr+=']';
	    	System.out.println("outStr: " + outStr);
	    	ObjectMapper mapper = new ObjectMapper(); 
            String jsonfromList = mapper.writeValueAsString(list);
            //System.out.println("jsonfromList: " + jsonfromList);
	    	out.println(outStr);
	    	rs.close();
	    	conn.close();
	    	out.flush();
	    	out.close();
        }
        catch(Exception e){
        	e.printStackTrace();
        } 
	}

}
