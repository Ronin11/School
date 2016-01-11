$(document).ready(function () {

	$.get("https://raw.githubusercontent.com/Ronin11/AI/master/data/data.json?callback=?", function(data){
		data = JSON.parse(data);
		// Create the chart
		$('#demo').highcharts('StockChart', {

			rangeSelector : {
				selected : 1
			},

			title : {
				text : 'AI Account Value'
			},

			series : [{
				name : 'Value',
				data : data,
				tooltip: {
					valueDecimals: 2
				}
			}]
		});
	});
});