/**
 * 
 */

  function pieChart(option,opValue,otherOp,flag) {
  	
  	var seriesOptions = "";
  	
  	function createPieChart(){
  		//alert(option+" "+opValue+" "+ otherOp+ " "+flag);
    	$('#pieChart'+flag).highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        credits:{
	              	enabled: false
	              },
	        title: {
	            text: '各地数据采集含量'
	        },
	        tooltip: {
	    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#000000',
	                    connectorColor: '#000000',
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '含量',
	            data: seriesOptions
	        }]
	    });
    };//createPieChart
    
    
	$.ajax
	   	({
	   		//请求类型
	   		type:"post",
            //需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
            url: "getGroup",
            //设置数据源
            data:
            {
            	option:option,
                opValue:opValue,
                otherOption:otherOp
            },
		  	//dataType: "json",
		  	success: 
			  function(response)
			  {
		  			//alert("pie: "+response);
              	
                   seriesOptions = eval(response);
                   
                   createPieChart();
              },
              
              //请求失败
              error:
              	  function()
	              {
	                  alert("请求失败！");
	                  //createPieChart();
	              }
              
          });//$.ajax
};	