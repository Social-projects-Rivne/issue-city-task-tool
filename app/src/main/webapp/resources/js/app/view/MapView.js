define([ 'jquery', 'underscore', 'backbone', 'collection/IssueCollection', 'view/IssueDetailsView',
         'view/CommentListView', 'view/AddIssueView', 'view/UserView', 'text!templates/map.html','gmaps'],
	//TODO add GoogleGeocode js and add to the function with alias
		function($, _, Backbone, IssueCollection, IssueDetailsView, CommentListView,
				AddIssueView, UserView, MapTemplate) {

	var that = null;

	var MapView = Backbone.View.extend({
				initialize : function() {
					this.model = new IssueCollection();
				},
				
				mapTemplate: _.template(MapTemplate),

				markers: [ L.AwesomeMarkers.icon( { icon: 'flame', markerColor: 'red', prefix: 'ion' } ),
				           L.AwesomeMarkers.icon( { icon: 'waterdrop', markerColor: 'blue', prefix: 'ion' } ),
				           L.AwesomeMarkers.icon( { icon: 'model-s', markerColor: 'orange', prefix: 'ion' } ),
				           L.AwesomeMarkers.icon( { icon: 'leaf', markerColor: 'green', prefix: 'ion' } ),
				           L.AwesomeMarkers.icon( { icon: 'flash', markerColor: 'cadetblue', prefix: 'ion' } ) ],
				
				//function for delete all marcers from the map
				cleanMap: function(){
					$(".leaflet-marker-pane").empty();
					$(".leaflet-shadow-pane").empty();
				},

				render : function() {
					$("#container").empty(),
					$("#container").append(this.mapTemplate);
					map = L.map('map').setView([50.62, 26.25], 13);
					marker = null;
					addIssueView = new AddIssueView( { el: "#form-container" } );
					issueDetailsView = new IssueDetailsView( { el: "#form-container" } );
					commentListView = new CommentListView( { el: ".comments" } );
					
					
					that = this;
					
					L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
					    maxZoom: 18
					}).addTo(map);
					
					map.on('click', onMapClick);
					
					this.model.fetch( { success: function() {
						that.model.each(function(issue) {
							if((issue.get('statusId')==2) || (issue.get('statusId')==5))
								L.marker(issue.get("mapPointer").substr(7, issue.get("mapPointer").length - 1)
									.split(', '), { icon: that.markers[Math.floor(Math.random() * 5)] }).addTo(map).on('click', onMarkerClick).title = issue.get("id");
						});
					} } );
					
					return this;
				}
			});
			
			function onMarkerClick(e) {
				//issueDetailsView.render(this.title);
				//TODO:replace comment list in issue details view
				Backbone.history.navigate("issue/"+this.title,true);
			}
			
			function onMapClick(e) {
				if (!marker)
					marker = L.marker(e.latlng).addTo(map);
				else
					marker.setLatLng(e.latlng)
				$("#map-pointer").val(e.latlng);

				//Fill adress field
				var geocoder = new google.maps.Geocoder();
				var latlng = {lat: e.latlng.lat, lng: e.latlng.lng};
				geocoder.geocode({'location': latlng}, function(results, status) {
					if (status === google.maps.GeocoderStatus.OK) {
						if (results[0]) {
							$("#map-adress").text(results[0].address_components[2].short_name + ", " + results[0].address_components[1].short_name + ", " + results[0].address_components[0].short_name  );
						} else {
							console.log('No results found');
						}
					} else {
						console.log('Geocoder failed due to: ' + status);
					}
					//--------------------------------
				});

			}
			
			return MapView;
		})