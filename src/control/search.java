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

//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.JsonFactory; 
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

public class search  extends ActionSupport {

	private HttpServletResponse response; 
	
	private String option;
	private String opValue;
	private String otherOption;


	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getOpValue() {
		return opValue;
	}

	public void setOpValue(String opValue) {
		this.opValue = opValue;
	}

	
	public String getOtherOption() {
		return otherOption;
	}

	public void setOtherOption(String otherOption) {
		this.otherOption = otherOption;
	}

	public void byDay()
			throws ServletException, IOException {
		
		response  = ServletActionContext.getResponse();
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	System.out.println("option: " + getOption());
    	System.out.println("opValue: " + getOpValue());
		System.out.println("\n[byDay]\n");
        databean search=new databean();
        Connection conn = null;
        ResultSet rs=null;
        search.setDB("ndn");//set database name
        conn=search.getConn();//connect to database
        String condition = "where ";//model = ";

        if( getOpValue()==null )
        	condition = " ";
        else
        	condition += getOption()+"='"+getOpValue()+"'";
        
        String sqlStr = "select "
        						+ "DATE_FORMAT(time,'%Y-%m-%d') as time,"
        						+ "count(*) as count "
        						+ "from picdata "
        						+ condition 
        						+" group by DATE_FORMAT(time,'%Y-%m-%d')"
        						+ " order by time asc;";
        System.out.println("sqlByDay:"+sqlStr);
        try{
	    	System.out.println("\nSearching database...\n");
	    	
	    	rs=search.executeSQL(sqlStr);   //querying the database
            
	    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	Date date;
	    	Long newDate, oldDate, step;
	    	step = new Long((long)(24*60*60*1000));
	    	oldDate = new Long(0);
	    	String outStr = "[";
	    	
	    	System.out.println("\nAnalysis...\n");
	    	
	    	if( rs.next())
	    	{
	    		String time=rs.getString("time");
	    		//System.out.println("time: " + time);
	    		int count = rs.getInt("count");
	    		//System.out.println("count: " + count);
	    		date=simpleDateFormat.parse(time);
	    	    newDate = date.getTime();
	    	    outStr+="["+newDate+",";
	    		outStr+=count+"]";
	    	    oldDate = newDate;
	    	}
	    	while(rs.next())
	    	{
	    		String time=rs.getString("time");
	    		//System.out.println("time: " + time);
	    		int count = rs.getInt("count");
	    		//System.out.println("count: " + count);
	    		
	    		date=simpleDateFormat.parse(time);
	    	    newDate = date.getTime();
	    	    //add date between oldDate and newDate to outStr
	    	    while( oldDate + step < newDate)
	    	    {
	    	    //	System.out.println("oldDate:"+simpleDateFormat.format(new Date(oldDate)));
	    	    	
	    	    	oldDate += step;
	    	    	outStr+=",["+oldDate+",";
		    		outStr+=0+"]";
		    		
	    	    }
	    	//    System.out.println("newDate:"+simpleDateFormat.format(new Date(newDate)));
	    	    //add newDate to outStr
	    	    outStr+=",["+newDate+",";
	    		outStr+=count+"]";
	    		//list.add(new byDay(timeStemp,count));
	    	    
	    		oldDate = newDate;
	        }
	    	outStr+=']';
	    	System.out.println("outStr: " + outStr);
	    	//ObjectMapper mapper = new ObjectMapper(); 
           // String jsonfromList = mapper.writeValueAsString(list);
            //System.out.println("jsonfromList: " + jsonfromList);
	    	out.println(outStr);
	    	rs.close();
	    	conn.close();
	    	out.flush();
	    	out.close();
	    	System.out.println("Finish successfully");
        }
        catch(Exception e){
        	e.printStackTrace();
        } 
	}

	public void byGroup() throws ServletException, IOException 
	{
		response  = ServletActionContext.getResponse();
    	response.setContentType("text/html");
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	System.out.println("option: " + getOption());
    	System.out.println("opValue: " + getOpValue());
		System.out.println("\n[byGroup]\n");
        databean search=new databean();
        Connection conn = null;
        ResultSet rs=null;
        search.setDB("ndn");//set database name
        conn=search.getConn();//connect to database
        
        String condition = "where ";//model = ";
        
        if( getOpValue()==null )
        	condition = " ";
        else
        	condition += getOption()+"='"+getOpValue()+"'";
        
        String sqlStr = "select "
        						+getOtherOption()
        						+",count(*) as count "
        						+ "from picdata "
    							+ condition 
        						+" group by "
        						+getOtherOption()
        						+ ";";
        System.out.println("sqlByGroup:"+sqlStr);
        try{
	    	System.out.println("\nSearching database...\n");
	    	
	    	rs=search.executeSQL(sqlStr);   //querying the database
            
	    	String outStr = "[";
	    	
	    	System.out.println("\nAnalysis...\n");
	    	
	    	while(rs.next())
	    	{
	    		if(outStr.length()!=1)
	    			outStr+=",";
	    		
	    		String option=rs.getString(1);
	    		//System.out.println("time: " + time);
	    		int count = rs.getInt(2);
	    		//System.out.println("count: " + count);
	    		
	    		outStr+="['"+option+"',";
		    	outStr+=count+"]";
	        }
	    	outStr+=']';
	    	
	    	out.println(outStr);
	    	System.out.println("outStr: " + outStr);
	    	rs.close();
	    	conn.close();
	    	out.flush();
	    	out.close();
	    	System.out.println("byGroup finish successfully");
        }
        catch(Exception e){
        	e.printStackTrace();
        } 
	}
}
