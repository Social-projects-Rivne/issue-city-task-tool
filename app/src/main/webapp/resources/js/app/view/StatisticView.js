define([ 'jquery', 'underscore', 'backbone' ],
		function($, _, Backbone) {
			var StatisticView = Backbone.View.extend({
				
				initialize: function() {
					console.log('Stat init');
				},
				
				render: function() {
					console.log('Stat render');
				},
				
				statisticByCategories: function() {
					$.ajax( {
						type: 'POST',
						url: 'statistic-by-category',
						dataType: 'json',
						success: function(data) {
							console.log(data);
						},
						error: function() {
							console.log('Error!');
						}
					} );
				},
				
				statisticByStatuses: function() {
					$.ajax( {
						type: 'POST',
						url: 'statistic-by-status',
						dataType: 'json',
						success: function(data) {
							console.log(data);
						},
						error: function() {
							console.log('Error!');
						}
					} );
				},
				
				statisticByComments: function() {
					$.ajax( {
						type: 'POST',
						url: 'statistic-by-comments',
						dataType: 'json',
						success: function(data) {
							console.log(data);
						},
						error: function() {
							console.log('Error!');
						}
					} );
				}
				
			});	
			
			return StatisticView;
		})

