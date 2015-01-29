function mapDraw() {
	map = L.map('map').setView([50.62, 26.25], 13);
	
	L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
	    maxZoom: 18
	}).addTo(map);
	
	function onMarkerClick(e) {
		if(!issueDetails.style.display)
			issueDetails.style.display = 'block';
		else
			issueDetails.style.display = '';
	}
	
	function onMapClick(e) {
		if(addIssue.style.display == 'block') {
			mapPointer.value = e.latlng;
			if(!tempMarker)
				tempMarker = L.marker(e.latlng).addTo(map).on('click', onMarkerClick);
			else
				tempMarker.setLatLng(e.latlng);
		}
	}

	map.on('click', onMapClick);
}



