define([ 'jquery', 'underscore', 'backbone' ],
		function($, _, Backbone) {
			var StatisticView = Backbone.View.extend({
				
				initialize: function() {
					console.log('Stat init');
				},
				
				render: function() {
					console.log('Stat render');
				},
				
				statDiagram: function(data) {
					var color = d3.scale.category10();
					
					var svg = d3.select("body").append("svg")
						.attr("width", 1000)
						.attr("height", 1000);
					
					var g = svg.append("g")
						.attr("transform", "translate(300, 300)");
					
					var arc = d3.svg.arc()
						.innerRadius(0)
						.outerRadius(200);
					
					var pie = d3.layout.pie()
						.value(function(d) { return d.value; });
					
					var arcs = g.selectAll(".arc")
						.data(pie(data))
						.enter()
						.append("g")
						.attr("class", "arc");
					
					arcs.append("path")
						.attr("d", arc)
						.attr("fill", function(d, i) { return color(i); });
					
					arcs.append("text")
						.attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
						.attr("text-anchor", "middle")
						.text(function(d, i) { return data[i].value != "0"?data[i].label:""; });
				},
				
				statisticByCategories: function() {
					var that = this;
					$.ajax( {
						type: 'POST',
						url: 'statistic-by-categories',
						dataType: 'json',
						success: function(data) {
							console.log(data);
							that.statDiagram(data);
						},
						error: function() {
							console.log('Error!');
						}
					} );
				},
				
				statisticByStatuses: function() {
					var that = this;
					$.ajax( {
						type: 'POST',
						url: 'statistic-by-statuses',
						dataType: 'json',
						success: function(data) {
							console.log(data);
							that.statDiagram(data);
						},
						error: function() {
							console.log('Error!');
						}
					} );
				},
				
				statisticByComments: function() {
					var that = this;
					$.ajax( {
						type: 'POST',
						url: 'statistic-by-comments',
						dataType: 'json',
						success: function(data) {
							console.log(data);
							that.statDiagram(data);
						},
						error: function() {
							console.log('Error!');
						}
					} );
				}
				
			});	
			
			return StatisticView;
		})

