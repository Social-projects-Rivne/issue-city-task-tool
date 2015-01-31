require(['validation'], 
	function(validation){

		window.onload = function() {
		cryOut = document.getElementById('cry-out');
		addIssue = document.getElementById('add-issue');
		issueDetails = document.getElementById('issue-details');
		mapPointer = document.getElementById('map-pointer');
		
		var tab1Title = document.getElementById('tab1-title');
		var tab2Title = document.getElementById('tab2-title');
		var tab3Title = document.getElementById('tab3-title');
		var tab1 = document.getElementById('tab1');
		var tab2 = document.getElementById('tab2');
		var tab3 = document.getElementById('tab3');
		
		var nextToDescription = document.getElementById('next-to-description');
		var nextToPhoto = document.getElementById('next-to-photo');
		
		cryOut.addEventListener('click', function(event) {
			event.preventDefault();
			addIssue.style.display = 'block';
		}, false);
		
		nextToDescription.addEventListener('click', function(event) {
			event.preventDefault();
			tab1Title.className = '';
			tab2Title.className = 'active';
			tab1.className = 'tab-pane';
			tab2.className = 'tab-pane fade active in';
		}, false);
		
		nextToPhoto.addEventListener('click', function(event) {
			event.preventDefault();
			tab2Title.className = '';
			tab3Title.className = 'active';
			tab2.className = 'tab-pane fade';
			tab3.className = 'tab-pane fade active in';
		}, false);
		
	//	mapDraw();
		validate();
	}
});