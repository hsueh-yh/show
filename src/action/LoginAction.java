package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.*;

import javax.servlet.http.*;

import java.util.Map;

public class LoginAction extends ActionSupport
							implements  ServletResponseAware {

	private String username;
	private String password;
	private String tip;
	private HttpServletResponse response;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	//实现ServletResponseAware接口必须实现的方法
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}
	
	//定义处理用户请求的execute方法
	public String execute() throws Exception
	{
		/*
		this.counter();
		//当username为qwe，password为123时即登录成功
		if (getUsername().equals("qwe")
			&& getPassword().equals("123") )
		{
			ActionContext.getContext().getSession()
				.put("username" , getUsername());
			Cookie cookie = new Cookie("username",getUsername());
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
			setTip("您已成功登陆！");
			return SUCCESS;
		}
		else
		{
			setTip("登录失败！");
			*/
			return ERROR;
		//}
	}

	//记录访问量
	public void counter()
	{
		ActionContext ctx = ActionContext.getContext();
		Integer count = (Integer)ctx.getApplication().get("counter");
		if( count == null )
		{
			count = 1;
		}
		else
		{
			count = count + 1;
		}
		
		ctx.getApplication().put("counter", count);
		ctx.getSession().put(username, getUsername());
	}
	
	public String test() throws Exception
	{
		this.counter();
		//当username为qwe，password为123时即登录成功
		if (getUsername().equals("qwe")
			&& getPassword().equals("123") )
		{
			ActionContext.getContext().getSession()
				.put("username" , getUsername());
			Cookie cookie = new Cookie("username",getUsername());
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
			setTip("您已成功登陆！");
			return SUCCESS;
		}
		else
		{
			setTip("登录失败！");
			return ERROR;
		}
	}
}
