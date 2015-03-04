define([ 'jquery', 'underscore', 'backbone', 'text!templates/StatisticsTemplate.html' ],
		function($, _, Backbone, StatisticsTemplate) {
			var StatisticView = Backbone.View.extend({
				template: _.template(StatisticsTemplate),
				
				initialize: function() {
					var that = this;
					$('#stat').on('click', function(e) { e.preventDefault(); that.render(); });
				},
				
				render: function() {
					if($("#statisticsModal")) $("#statisticsModal").remove();
					this.$el.append(this.template);
					this.statisticByCategories();
					this.statisticByStatuses();
					this.statisticByComments();
					$("#statisticsModal").modal();
				},
				
				statDiagram: function(data) {
					var color = d3.scale.category10();
					
					var svg = d3.select(".modal-body").append("svg")
						.attr("width", 410)
						.attr("height", 410);
					
					var g = svg.append("g")
						.attr("transform", "translate(205, 205)");
					
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

