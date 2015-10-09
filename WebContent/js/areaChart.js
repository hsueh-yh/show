/**
 * 
 */		
function areaChart() {
    $('#container4').highcharts({
        chart: {
            type: 'area'
        },
        credits:{
        	enabled: false
        },
        title: {
            text: '各时期污染物含量'
        },
        /*
        subtitle: {
            text: 'Source: Wikipedia.org'
        },
        */
        xAxis: {
            categories: ['1月', '2月', '3月', '4月', '5月', '6月', '7月',
                         '8月', '9月', '10月', '11月', '12月'],
            tickmarkPlacement: 'on',
            title: {
                enabled: false
            }
        },
        yAxis: {
            title: {
                text: 'Percent'
            }
        },
        tooltip: {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b> ({point.y:,.0f} millions)<br/>',
            shared: true
        },
        plotOptions: {
            area: {
                stacking: 'percent',
                lineColor: '#ffffff',
                lineWidth: 1,
                marker: {
                    lineWidth: 1,
                    lineColor: '#ffffff'
                }
            }
        },
        series: [{
            name: 'PM2.5',
            data: [502, 635, 809, 947, 1402, 3634, 5268]
        }, {
            name: 'PM10',
            data: [106, 107, 111, 133, 221, 767, 1766]
        }, {
            name: 'else',
            data: [163, 203, 276, 408, 547, 729, 628]
        }]
    });
}