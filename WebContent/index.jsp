<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>picdatashow</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="./js/dataimport.js"></script>

  <body>
  数据采集统计：
  <div id='options'>
  	地&nbsp;&nbsp;点：&nbsp;&nbsp;<a href=''>北邮</a>
  					&nbsp;&nbsp;<a href=''>北京大学</a>
  	手机型号：
  	日&nbsp;&nbsp;期：
  </div>
<div id="container" style="height: 600px"></div>
  <div id="container2" style="height: 600px"></div>
  <script type="text/javascript">
  $.ajax({
	type:'get',
    url:'./servlet/dataimport',
    datatype:'html',
    success:function(data){
    	alert(data);
        var dataObj=$.parseJSON(data);
    	var msra=dataObj[0].MSRA;
    	var by=dataObj[0].bjyddx;
    	by+=dataObj[0].by;
    	var dwjm=dataObj[0].dwjm;
    	var bgd=dataObj[0].bgd;
    	var bl=dataObj[0].bl;
    	var cgz=dataObj[0].cgz;
    	var as=dataObj[0].as;
    	var nzg=dataObj[0].nzg;
    	var cygy=dataObj[0].cygy;
    	var fcd=dataObj[0].fcd;
    	var tt=dataObj[0].tt;
    	var mdy=dataObj[0].mdy;
    	var zgc=dataObj[0].zgc;
    	var bd=dataObj[0].bd;
    	var count=by+dwjm+bgd+bd+bl+cgz+as+nzg+msra+cygy+fcd+tt+mdy+zgc;
    	
    $('#container').highcharts({
        title: {
            text: '各地照片数据统计直方图'
        },
		chart:{
			type:'column'
			},
        subtitle: {
            text: '共计'+count
        },
        xAxis: {
            categories: ['北邮', '对外经贸', '北工大', '北大', '北理', '车公庄', '奥森', '农展馆', 'MSRA', '朝阳公园', '芳草地', '天坛','牡丹园','中关村']
        },
        series: [{
            name:'地点',
            data: [by, dwjm, bgd, bd, bl, cgz, as, nzg, msra, cygy, fcd, tt, mdy, zgc]
        }]
    });
   var options = {
        chart: {
            renderTo:'container2',
            type: 'line'
        },
        title: {
            text: '月图'
        },
        subtitle: {
            text: '各地照片走势'
        },
        xAxis: {
            title: {
                text: '月份'
            },
            categories:['五月','六月','七月']
        },
        yAxis: {
            title: {
                text: '张数'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        
        series: 
        [
        ]
    };
   
    for(var i=1;i<dataObj.length;i++){
    var seriesOptions = {
            name: dataObj[i].location,
            data: [dataObj[i].at5month,dataObj[i].at6month,dataObj[i].at7month]
        };
     options.series.push(seriesOptions);
     }
     var chart = new Highcharts.Chart(options);
    
    }
})
  </script>

  
  </body>
</html>