package control;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.databean;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

public class optionsAction extends ActionSupport
						 implements  ServletResponseAware {

	private HttpServletResponse response;
	private String searchType;
	

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}
	
	
	//定义处理用户请求的execute方法
	public String execute() throws Exception
	{
		return ERROR;
	}

	//get locations from database
	@SuppressWarnings("null")
	public String getOptions() throws Exception
	{
		String search = null;
    	response.setCharacterEncoding("utf-8");
    	response.setContentType("text/xml;charset=utf-8");
		
		System.out.println("connect db...... ");
		databean db = new databean();
		Connection conn = null;
        ResultSet rs=null;
        db.setDB("ndn");//set database name
        conn=db.getConn();//connect to database
        System.out.println("connected");
        try{
        	System.out.println("select...... " + searchType);
	    	String searchSql="select distinct " + searchType + " from picdata;"; 
	    	rs=db.executeSQL(searchSql); 
	    	System.out.println("select ok ");
	    	
	    	List<String> list = new ArrayList<String>();
	    	JsonGenerator jsonGenerator = null;
            ObjectMapper mapper = new ObjectMapper(); 
	    	
	    	String location;
	    	System.out.println("analysis......");
	    	
	    	while( rs.next() )
	    	{
	    		location = rs.getString( 1 );
	    		list.add(location);
	    		System.out.println(location);
	    	}
	    	String jsonfromList = mapper.writeValueAsString(list);
	    	System.out.println("jsonfromList: " + jsonfromList);
	    	PrintWriter out = response.getWriter();
	    	out.println(jsonfromList.toString());
	    	//out.println("<a href='www.baidu.com'>baidu</a>");
	    	rs.close();
	    	conn.close();
	    	out.flush();
	    	out.close();
	    	System.out.println("done!");
        }
        catch(Exception e){
        	e.printStackTrace();
        } 
        
        
		return SUCCESS;
	}

}
