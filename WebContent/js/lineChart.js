/**
 * 
 */
/*****************折线图******************/
	
	function lineChart(option,opValue) {
		
	    var seriesOptions = [],
	        seriesCounter = 0,
	        names = new Array();
	        names.push(opValue);

	       // alert("flag="+flag);
        createLineChart = function () {
        
            $('#lineChart').highcharts('StockChart', {

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
                        //rotation : -50,//旋转-50度，解决SVG字太拥挤的问题
                        y : 30
                    },
                   // */
                    dateTimeLabelFormats: {
                        millisecond: '%Y-%m-%d %H:%M:%S',
                        second: '%Y-%m-%d %H:%M:%S',
                        minute: '%Y-%m-%d %H:%M',
                        hour: '%Y-%m-%d %H:%M',
                        day: '%m月%d日',
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
                    /*
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
                    */
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
	    	   	
	    	   		//alert(name);
	    	
			    	$.ajax
			    	({
			    		//请求类型
			    		type:"post",
		                //需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
		                url: "getDay",
		                //设置数据源
		                data:
		                {
		                	option:option,
		                    opValue:name
		                },
					  	dataType: "json",
					  	success: 
							  function(response)
							  {
					  			//alert(response);
		                	
			                    seriesOptions[i] = {
						                name: name,
						                data: eval(response)
						        };
						            
					            seriesCounter += 1;
				
					            if (seriesCounter === names.length) {
					            	//alert("createChart...");
					                createLineChart();
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
	    
	};