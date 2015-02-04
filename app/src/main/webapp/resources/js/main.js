require([
        'jquery',
        'underscore',
        'backbone',
        'view/IssueView',
        'model/CommentModel',
        'view/CommentView',
        'collection/CommentCollection',
        'model/IssueModel',
        'view/IssueView',
        'map',
        'homeScript',
        ]
, function($, _, Backbone, 
	IssueView,
	CommentModel,
	CommentView,
	CommentCollection,
	IssueModel,
	IssueView) {
	
	var comments = null;
	
	jQuery(document).ready(function($) {
		 mapDraw();
		console.log('comments initialization');
		comments = new CommentCollection({'issueId':'1'});
		comments.initialize();
		console.log('comments  init done');
		comments.fetch();
		console.log('comments fetched');
		console.log('comments render');
		global = comments; //FOR DEBUG
		console.log('comments render done');
		IssueModel = new IssueModel;
		
		//very important to init marker! 
		//initialize new issue marker
		//mapPointer.value = e.latlng;
		
	});
	
	function mapDraw() {
		map = L.map('map').setView([50.62, 26.25], 13);
		tempMarker = null;
		issueList = null;
		L.AwesomeMarkers.Icon.prototype.options.prefix = 'ion';
		
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
		    maxZoom: 18
		}).addTo(map);
		
		//click on issue marker
		function onMarkerClick(e) {
				var issue; 
			/*	issue = new IssueModel;
				issue.set({id : issueList[this.title - 1].id, 
					name : issueList[this.title - 1].name,
					description : issueList[this.title - 1].description,
					attachments : issueList[this.title - 1].attachments,});
				*/
				var issueView = new IssueView({model : issue});
				
				console.log(issueList[this.title - 1].id);
				
				comments = new CommentCollection({'issueId': issueList[this.title - 1].id}); 
				comments.fetch();
				comments.render();
				
				issueView.issueDetailsForm();
				$('#issue_name').text(issueList[this.title - 1].name);
				$('#issue_description').text(issueList[this.title - 1].description);
				
		}
		
		//marker for adding new issues 
		function onMapClick(e) {
			
				mapPointer.value = e.latlng;
				if(!tempMarker)
					tempMarker = L.marker(e.latlng).addTo(map);
				else
					tempMarker.setLatLng(e.latlng);
		}

		map.on('click', onMapClick);
		
		$.ajax({
			url: 'get-markers',
			type: 'GET',
			contentType: 'application/json',
			mimeType: 'application/json',
			dataType: 'json',
			success: function(data) {
				issueList = data;
				data.forEach(function(element, index, array) {
					var greenMarker = L.AwesomeMarkers.icon({
					    icon: 'home',
					    markerColor: 'green'
					  });
					
					var tmp = L.marker(element.mapPointer.substr(7, element.mapPointer.length - 1)
							.split(', '), {icon: greenMarker}).addTo(map).on('click', onMarkerClick);
					tmp.title = element.id;
				});
			}
		});
	}
	//replace it in comment view
	/*document.getElementById('add_comment_button').addEventListener('click', function(event) {
		event.preventDefault();
		sendNewComment();
	}, false);
	*/
	
	//add this 
	
	// var comments = new CommentCollection;
});

// separate this function to another file
function sendAjax(comment) {

	$
			.ajax({
				url : "/Bawl/add-comment",
				type : 'POST',
				dataType : 'json',
				// data : '{"email":"' +
				// document.getElementsByName("email")[0].value +
				// '","userName":"' +
				// document.getElementsByName("userName")[0].value + '",
				// "comment": "' +
				// document.getElementsByName("comment-text")[0].value + '",
				// "issueId":"1"}',
				data: comment,
				contentType : 'application/json',
				mimeType : 'application/json',
				success : function(data) {
					alert(data.email + " " + data.issueId);
					console.log(comment);
				},
				error : function(data, status, er) {
					console.log(comment);	
					alert("error: " + data + " status: " + status + " er:" + er);
				}
			});
}
var IssueModel;
var global;