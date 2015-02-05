define([ 'jquery', 'underscore', 'backbone', 'collection/IssueCollection', 'view/IssueDetailsView' ],
		function($, _, Backbone, IssueCollection, IssueDetailsView) {
			var MapView = Backbone.View.extend({
				initialize : function() {
					this.model = new IssueCollection();
					this.model.fetch();
				},
				
				render : function() {
					map = L.map('map').setView([50.62, 26.25], 13);
					marker = null;
					issueDetailsView = new IssueDetailsView;
					
					L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
					    maxZoom: 18
					}).addTo(map);
					
					map.on('click', onMapClick);
					
					this.model.each(function(issue) {
						L.marker(issue.get("mapPointer").substr(7, issue.get("mapPointer").length - 1)
								.split(', ')).addTo(map).on('click', onMarkerClick).title = issue.get("id");
					});
					
					return this;
				}
			});
			
			function onMarkerClick(e) {
				issueDetailsView.render(this.title);
			}
			
			function onMapClick(e) {
				if(!marker)
					marker = L.marker(e.latlng).addTo(map);
				else
					marker.setLatLng(e.latlng);
			}
			
			return MapView;
		})