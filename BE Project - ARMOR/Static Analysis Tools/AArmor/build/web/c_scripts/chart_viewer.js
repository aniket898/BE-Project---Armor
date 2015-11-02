google.load('visualization', '1', {packages:['orgchart']});
      google.setOnLoadCallback(drawChart);
	  var chart;
	  var data1,data2;
      function drawChart() {
	  
        data1 = new google.visualization.DataTable();
        data1.addColumn('string', 'Name');
        data1.addColumn('string', 'Manager');
        data1.addColumn('string', 'ToolTip');
        data1.addRows([
          [{v:'Mike', f:'Mike<div style="color:red; font-style:italic">President</div>'}, '', 'The President'],
          [{v:'Jim', f:'Jim<div style="color:red; font-style:italic">Vice President</div>'}, 'Mike', 'VP'],
          ['Alice', 'Mike', ''],
          ['Bob', 'Jim', 'Bob Sponge'],
          ['Carol', 'Bob', '']
        ]);
		data2 = new google.visualization.DataTable();
        data2.addColumn('string', 'Name');
        data2.addColumn('string', 'Manager');
        data2.addColumn('string', 'ToolTip');
        data2.addRows([
          [{v:'Hike', f:'Hike<div style="color:red; font-style:italic">President</div>'}, '', 'The President'],
          [{v:'Jim', f:'Jim<div style="color:red; font-style:italic">Vice President</div>'}, 'Hike', 'VP'],
          ['Alice', 'Hike', ''],
          ['Bob', 'Jim', 'Bob Sponge'],
          ['Carol', 'Bob', '']
        ]);
        chart = new google.visualization.OrgChart(document.getElementById('chart_div'));
		  chart.draw(data1, {allowHtml:true});
      // chart.draw(data, {allowHtml:true});
		var chart2 = new google.visualization.OrgChart(document.getElementById('chart_div1'));
        chart2.draw(data, {allowHtml:true});
      }