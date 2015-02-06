define([ 'jquery', 'underscore', 'backbone', 'leaflet', 'collection/IssueCollection' ],
		function($, _, Backbone, L, IssueCollection) {
			var MapView = Backbone.View.extend({
				initialize : function() {
					this.model = new IssueCollection();
					this.model.fetch();
				},
				
				render : function() {
					var map = L.map('map').setView([50.62, 26.25], 13);
					
					L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
					    maxZoom: 18
					}).addTo(map);
					
					this.model.each(function(issue) {
						var tempMarker = L.marker(issue.get("mapPointer").substr(7, element.mapPointer.length - 1)
								.split(', ')).addTo(map);
					});
				}
			});	
			
			return MapView;
		})