<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<!--  <script type="text/javascript" src="./jQuery/jquery-1.11.3.min.js"></script> -->
<script src="./js/json2.js"></script>
<script src="./js/highstock.js"></script>
<script src="./js/exporting.js"></script>
<script src="./js/highcharts-more.js"></script>
<script src="./js/highcharts-3d.js"></script>
<script src="./js/solid-gauge.js"></script>
<script src="./js/data.js"></script>
<script src="./js/drilldown.js"></script>
<script src="./js/funnel.js"></script>
<title>test</title>
</head>
<body>

	<script type="text/javascript">

    /*****************折线图******************/
	
	$(function () {
		
	    var seriesOptions = [],
	        seriesCounter = 0,
	        names = ['北邮'],
	        
        createChart = function () {
            $('#achart').highcharts('StockChart', {

           		global: {
           			useUTC: false//中文区时间格式
           		},
           		// 中文时间显示字符串替换
           			
           		lang: {
           	        shortMonths: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
           	            weekdays: ['周日','周一','周二','周三','周四','周五','周六'],
           	            rangeSelectorFrom: '起始',
           	            rangeSelectorTo: '终止',
           	            rangeSelectorZoom: '缩放'
           	    },
           	
                rangeSelector: {
                    selected: 4
                },
                credits:{
                	enabled: false
                },
                title: {
                    text: '北京市污染物含量趋势图'
                },
                xAxis: {
                	///*
                    labels: {
                        rotation : -50,//旋转-50度，解决SVG字太拥挤的问题
                        y : 50
                    },
                   // */
                    dateTimeLabelFormats: {
                        millisecond: '%Y-%m-%d %H:%M:%S',
                        second: '%Y-%m-%d %H:%M:%S',
                        minute: '%Y-%m-%d %H:%M',
                        hour: '%Y-%m-%d %H:%M',
                        day: '%Y-%m-%d',
                        week: '%Y-%m-%d',
                        month: '%Y-%m',
                        year: '%Y'
                    }
                },//xAxis
                
                yAxis: {
                    labels: {
                        formatter: function () {
                            //return (this.value > 0 ? ' + ' : '') + this.value + '%';
                            return this.value;
                        }
                    },
                    plotLines: [{
                        value: 70,
                        color: 'green',
                        dashStyle : 'shortdash',
                        width: 2,
                        //color: 'silver'
                        label:{
                        	text:'良好'
                        }
                    },
                    {
                        value: 100,
                        color: 'red',
                        dashStyle : 'shortdash',
                        width: 2,
                        //color: 'silver'
                        label:{
                        	text:'严重污染'
                        }
                    }
                    ]
                },// yAxis
                
                legend: {
                	enabled: true,
                	floating: true
                },
                
                allowPointSelect: {
                	enabled : true
                },
                animation: {
                	enabled : true
                },
/*
                plotOptions: {
                    series: {
                        compare: 'percent'
                    }
                },
*/
                tooltip: {
                    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> <br/>',
                    valueDecimals: 2
                },
				
				scrollbar:{
					enabled : true
				},
                
                spline: {
                	enabled : true
                },
                
                series: seriesOptions
            
            });//$('#container1').highcharts
        };//createChart = function ()

        
	    $.each(names, 
	    		
	    	   	function (i, name) {
	    	   	
	    	   		alert(name);
	    	
			    	$.ajax
			    	({
			    		//请求类型
			    		type:"post",
		                //需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
		                url: "getDay",
		                //设置数据源
		                data:
		                {
		                    location:name
		                },
					  	dataType: "json",
					  	success: 
							  function(response)
							  {
					  			alert(response);
		                	
			                    seriesOptions[i] = {
						                name: name,
						                data: eval(response)
						        };
						            
					            seriesCounter += 1;
				
					            if (seriesCounter === names.length) {
					            	alert("createChart...");
					                createChart();
					                //chart.setData
					         	}
				            
		                	},
		                
		                //请求失败
		                error:function()
		                {
		                    alert("系统异常，请稍后重试！");
		                }
		                
		            });//$.ajax
				        
	    		}//function (i, name)
	    		
	    );// $.each
	    
	});
	
    
    
    </script>

    <div id="achart" style="height: 600px; width: 1000px; margin: 0 auto"></div>
    <br/><br/> 
</body>
</html>