window.onload = function() {
	initialize();
	//validate();
	
	var cryOut = document.getElementById('cry-out');
	var issue = document.getElementById('grid-right');
	var map = document.getElementById('map');
	
	cryOut.addEventListener('click', function(event) {
		event.preventDefault();
		issue.style.display = 'block';
	}, false);
	
	map.addEventListener('click', function(event) {
		event.preventDefault();
		issue.style.display = 'none';
	}, false);
}