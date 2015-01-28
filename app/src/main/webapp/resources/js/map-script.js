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
	var map = L.map('map').setView([50.620679, 26.244523], 13);

	L.tileLayer('https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
			'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery В© <a href="http://mapbox.com">Mapbox</a>',
		id: 'examples.map-i875mjb7'
	}).addTo(map);


	//TEST MARKERS:
	//L.marker([50.620679, 26.244523]).addTo(map)
  //.bindPopup("<b>Hello world!</b><br />I am a popup.").openPopup();
	//L.marker([50.622222, 26.245444]).addTo(map)

	var popup = L.popup();

	function onMapClick(e) {
		popup
			.setLatLng(e.latlng)
			.setContent("You clicked the map at " + e.latlng.toString())
			.openOn(map);
	}

	map.on('click', onMapClick);



	var someMarker = L.AwesomeMarkers.icon({
		prefix:'fa',
		icon:'coffee',
		markercolor:'darkpuple'
	});
	// Creates a red marker with the coffee icon
	var redMarker = L.AwesomeMarkers.icon({
		prefix:'fa',
		icon: 'bed',
		markerColor: 'red',

	});

	// Creates black spinner marker
	var spinnerMarker = L.AwesomeMarkers.icon({
		prefix:'fa',
		icon:'spinner',
		markerColor:'black',
		spin: 'true'
	});

	// Creating instance of markers on map
	L.marker([50.636833,26.262323], {icon: redMarker}).addTo(map);
	L.marker([50.62224, 26.23415], {icon: spinnerMarker}).addTo(map);
	L.marker([50.62224, 26.26419], {icon: someMarker}).addTo(map);
}



