window.onload = function() {
	initialize();
	validate();
	
	var map = document.getElementById('map');
	var issue = document.getElementById('add-issue-form');
	
	map.addEventListener('click', function() {
		issue.style.display = 'block';
		getIssue(1);
	}, false);
}