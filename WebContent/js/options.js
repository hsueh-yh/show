/**
 * 
 */
function searchOption(option,opValue)
{
	//alert("option: " + option);
	//alert("opValue: " + opValue);
	
	lineChart(option,opValue);
	
	var otherOp = ["location","model","dpi"];
	var key = ["0","1"];
	var k = 0;
	$.each(otherOp,
			
		function(i , op)
		{
			if(op!=option)
			{
				//alert("key="+key[k]);
				pieChart(option,opValue,op,key[k]);
				k++;
			}
		}
	);
	//pieChart(option,opValue);
}
	
$(function getOptions(){
	
	var options = ["location","model","dpi"];
	
	$.each(options, 
	
		function (i, option) {
		
			$.ajax({
				  url: "getOp",
				  data: {
					  searchType: option
				  },
				  dataType: "json",
				  success: 
					  function(response,status,xhr)
					  {
					  	$.each(response,
				  			function( i, respon)
				  			{
				  				//alert(respon);
				  				
				  				$("#"+option).append("<a href='#' onclick=\"searchOption('"
				  										+option+"',"+"'"+respon+"')\">"+respon+"</a>");
				  				$("#"+option).append("&nbsp;&nbsp;&nbsp;&nbsp;");
				  			}
					  	);
					  	
					  }
			});
		
		});
});