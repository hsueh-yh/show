<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
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
	
	<title>show</title>
	
</head>

<body>

	<script src="./js/lineChart.js"></script>
	<script src="./js/pieChart.js"></script>
	<script src="./js/options.js"></script>

	<br/>
    <div id="location" style="height: 30px" >
		地址：&nbsp;&nbsp;
	</div><br/>
	<div id="model" style="height: 30px" >
		型号：&nbsp;&nbsp;
	</div><br/>
	<div id="dpi" style="height: 30px" >
		型号：&nbsp;&nbsp;
	</div><br/>
	<div id="lineChart" style="height: 500px; width: 1000px; margin: 0 auto"></div><br/>
	<div style="height: 400px; margin: 0 auto">
		<div id="pieChart0" style="height: 500px; width: 460px; float: left"></div>
		<div id="pieChart1" style="height: 500px; width: 550px; float: right"></div>
	</div>
</body>
</html>