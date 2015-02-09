define([ 'jquery', 'underscore', 'backbone', 'collection/IssueCollection',
         'view/IssueDetailsView', 'view/CommentListView' ],
		function($, _, Backbone, IssueCollection, IssueDetailsView, CommentListView) {
			var MapView = Backbone.View.extend({
				initialize : function() {
					this.model = new IssueCollection();
				},
				
				render : function() {
					map = L.map('map').setView([50.62, 26.25], 13);
					marker = null;
					issueDetailsView = new IssueDetailsView( { el: "#form-container" } );
					commentListView = new CommentListView( { el: "#form-container" } );
					var that = this;
					
					L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
					    maxZoom: 18
					}).addTo(map);
					
					map.on('click', onMapClick);
					
					this.model.fetch( { success: function() {
						that.model.each(function(issue) {
							L.marker(issue.get("mapPointer").substr(7, issue.get("mapPointer").length - 1)
									.split(', ')).addTo(map).on('click', onMarkerClick).title = issue.get("id");
						});
					} } );
					
					return this;
				}
			});
			
			function onMarkerClick(e) {
				issueDetailsView.render(this.title);
				commentListView.render(this.title);
			}
			
			function onMapClick(e) {
				if(!marker)
					marker = L.marker(e.latlng).addTo(map);
				else
					marker.setLatLng(e.latlng);
			}
			
			return MapView;
		})