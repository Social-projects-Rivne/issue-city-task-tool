define([ 'jquery', 'underscore', 'backbone', 'leaflet', 'collection/IssueCollection' ],
		function($, _, Backbone, L, IssueCollection) {
			var MapView = Backbone.View.extend({
				initialize : function() {
					console.log('start');
					this.model = new IssueCollection();

					console.log('start fetch');
					this.model.fetch();
					setTimeout(console.log(this.model.toJSON()),1000);
				},
				
				render : function() {
					var map = L.map('map').setView([50.62, 26.25], 13);
					
					L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
					    maxZoom: 18
					}).addTo(map);
					
					this.model.each(function(issue) {
						var tempMarker = L.marker(issue.get("mapPointer").substr(7, issue.get("mapPointer").length - 1)
								.split(', ')).addTo(map);
					});
				}
			});	
			
			return MapView;
		})