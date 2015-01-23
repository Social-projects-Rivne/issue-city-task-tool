var gpos, map;
var obMarkers = {};
var setMarker = function (obMap, obPos, icon)
{
	if (!obMap || !obPos) return -1;
	if (!icon)
		return new google.maps.Marker({
			  position: obPos,
			  map: obMap
			});
	else
		return new google.maps.Marker({
			  position: obPos,
			  map: obMap,
			  icon: icon
			});
}
function initialize() {
	// set user position
	gpos = new google.maps.LatLng(50.620679, 26.244523);
  
	// set default map options
	var mapOptions = {
	    zoom: 12,
	    center: gpos
	    //mapTypeId: google.maps.MapTypeId.ROADMAP
	}
	// initialise map object to #map_canvas
	map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	
	// creating OSM Layer object
	var openStreet = new google.maps.ImageMapType({
		getTileUrl: function(ll, z) {
			var X = ll.x % (1 << z);  // wrap
			return "http://tile.openstreetmap.org/" + z + "/" + X + "/" + ll.y + ".png";
		},
		tileSize: new google.maps.Size(256, 256),
		isPng: true,
		maxZoom: 18,
		name: "OpenStreetMap",
		alt: "Слой с Open Streetmap"
	}); 
 
	// adding OSM map type with self layer 
	map.mapTypes.set('osm', openStreet);
	// init map type with created OSM Layer
	map.setMapTypeId('osm');
 
	/*map.setOptions({
		mapTypeControlOptions: {
			mapTypeIds: [
			             'osm',
						  //google.maps.MapTypeId.ROADMAP,
						  //google.maps.MapTypeId.TERRAIN,
						  //google.maps.MapTypeId.SATELLITE,
						  //google.maps.MapTypeId.HYBRID
			             ],
            //style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
			}
	});*/
	google.maps.event.addListenerOnce(map, 'idle', function() {
		obMarkers['one'] = setMarker(map, gpos);
	});
}



