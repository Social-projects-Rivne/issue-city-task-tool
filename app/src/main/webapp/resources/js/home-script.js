window.onload = function() {
	initialize();
	validate();
	
	var cryOut = document.getElementById('cry-out');
	var addIssue = document.getElementById('add-issue');
	var issueDetails = document.getElementById('issue-details');
	var map = document.getElementById('map');
	
	cryOut.addEventListener('click', function(event) {
		event.preventDefault();
		addIssue.style.display = 'block';
	}, false);
	
	map.addEventListener('click', function(event) {
		event.preventDefault();
		issueDetails.style.display = 'block';
	}, false);
}