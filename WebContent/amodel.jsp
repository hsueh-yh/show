<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<!--  
<script src="../jQuery/jquery-1.11.3.min.js"></script>
-->
<title>amodel</title>

<script>
//function sql(param){ alert(param);}
	$.ajax({
		  url: "getOp",
		  data: {
			  searchType: 'location'
		  },
		  dataType: "json",
		  success: 
			  function(response,status,xhr)
			  {
			  	$.each(response,
			  			function( i, respon)
			  			{
			  				//alert(respon);
			  				$('#location').append("<a href='#'>"+respon+"</a>");
			  				$('#location').append("&nbsp;&nbsp;&nbsp;&nbsp;");
			  			}
			  	);
			  	
			  }
	});
		  
		$.ajax({
			  url: "getOp",
			  data: {
				  searchType: 'model'
			  },
			  dataType: "json",
			  success: 
				  function(response,status,xhr)
				  {
				  	$.each(response,
				  			function( i, respon)
				  			{
				  				//alert(respon);
				  				$('#model').append("<a href='#'>"+respon+"</a>");
				  				$('#model').append("&nbsp;&nbsp;&nbsp;&nbsp;");
				  			}
				  	);
				  	
				  }
		});
	</script>
	
</head>
<body>


	<div>
		<form action="getOp" method="get" name="form2">
			search：<s:textfield name="searchType"/>
			<s:submit value="submit"/>
		</form>
	 </div>
	 
	<div id="options" style="height: 30px" >
		<p>地点：</p><a id='location'></a>
		<br/><br/>
		<p>手机型号：</p><a id='model'></a>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("[href='#']").click(function(){
			  $("p").show();
		});
		    //alert("123");
		});
	</script>
	
</body>
</html>
