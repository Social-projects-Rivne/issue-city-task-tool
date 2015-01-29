function mapDraw() {
	map = L.map('map').setView([50.62, 26.25], 13);
	tempMarker = null;
	
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
				tempMarker = L.marker(e.latlng).addTo(map);
			else
				tempMarker.setLatLng(e.latlng);
		}
	}

	map.on('click', onMapClick);
	
	$.ajax({
		url: 'get-markers',
		type: 'GET',
		contentType: 'application/json',
		mimeType: 'application/json',
		dataType: 'json',
		success: function(data) {
			data.forEach(function(element, index, array) {
				L.marker(element.mapPointer.substr(7, element.mapPointer.length - 1).split(', ')).addTo(map).on('click', onMarkerClick);
			});
		}
	});
}



