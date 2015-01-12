var gpos;

function initialize() {
	gpos = new google.maps.LatLng(50.620679, 26.244523);
  var mapOptions = {
    zoom: 12,
    center: gpos
    //mapTypeId: google.maps.MapTypeId.ROADMAP
  }
  var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
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
 
			//adding OpenStreetMap layer
 
		map.mapTypes.set('osm', openStreet);
	map.setMapTypeId('osm');
 
		map.setOptions({
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
	});

	var gMarker = new google.maps.Marker({
	  position: gpos,
	  map: map
	  //icon: 'img_src.png';
	});

}

window.onload = initialize;