function initialize() {
	var map = L.map('map').setView([50.62, 26.25], 13);
	
	L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
	    maxZoom: 18
	}).addTo(map);
	
	var marker = L.marker([50.62, 26.25]).addTo(map);
	
	marker.bindPopup("<b>Hello world!</b><br>I am a popup.");
	
	function onMapClick(e) {
	    alert("You clicked the map at " + e.latlng);
	}

	map.on('click', onMapClick);
}



