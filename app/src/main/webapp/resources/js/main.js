
require([
        'jquery',
        'underscore',
        'backbone',
        'router',
        /*'view/IssueView',
        'model/CommentModel',*/
        'view/MapView',
        'view/AddIssueView',
        'view/ManagerView',
        'view/LoginView',
        'view/IssueFilterView',
        /*'collection/CommentCollection',
        'model/IssueModel',
        'view/MapView',
        'map',
        'homeScript',*/
        'view/StatisticView'
        ]
, function($, _, Backbone, 
	Router,
	//IssueView,
	//CommentModel,
	//CommentView,
	//CommentCollection,
	//IssueModel, 
	MapView,
	AddIssueView,
	ManagerView,
	LoginView,
	IssueFilterView,
	StatisticView) {
	
	//var comments = null;
	router = null;
	adminView = null;
	managerView = null;
	
	jQuery(document).ready(function($){
		// show login form on unauthorized response
		$.ajaxSetup({statusCode: {401: function(){router.navigate('login', {trigger: true}); } } });
		
		mapView = new MapView( { el: "body" } );
		mapView.render();
		issueFilterView = new IssueFilterView({el:"#container"});
		
		loginView = new LoginView({el:"body"});
		
		issueFilterView = new IssueFilterView({el:"#container"});
		
		statisticView = new StatisticView( { el: 'body' } );
		
		//loginView.render();
		//managerView.render();
		//mapDraw();
		// for debug
		//issueModel = new IssueModel;
		//commentView = new CommentView;
		//commentCollection = new CommentCollection;
		//issueView = new IssueView();
		//very important to init marker! 
		//initialize new issue marker
		//mapPointer.value = e.latlng;
		//setTimeout(mapView.render(),500);
		//Backbone.history.start();
		//router.navigate("", {trigger: true});
		router = new Router();
	});
	
	/*function mapDraw() {
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
				
				
				issue = new IssueModel;
				issue.set({id : issueList[this.title - 1].id, 
					name : issueList[this.title - 1].name,
					description : issueList[this.title - 1].description,
					attachments : issueList[this.title - 1].attachments,});
				console.log('issue'+issue.toJSON());
					
				issueView = new IssueView({model : issue});
				
				issueView.setIssueId(issue.get('id'));
				
				console.log('start');
				issueView.issueDetailsForm();
				
			//	$('#issue_name').text(issueList[this.title - 1].name);
			//	$('#issue_description').text(issueList[this.title - 1].description);
				
				commentCollection.setID(issueList[this.title - 1].id);
				commentCollection.fetch(); 
				setTimeout(function(){
				//	alert('Please wait, comments are loading! '); 
					commentCollection.render();
					}, 500);

				console.log('done');
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
			url: 'get-issues',
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
	}*/
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
/*function sendAjax(comment) {

	$
			.ajax({
				url : "/Bawl/add-comment",
				type : 'POST',
				dataType : 'json',
				 data : '{"email":"' +
				 document.getElementsByName("email")[0].value + '","userName":"' + document.getElementsByName("userName")[0].value + '","comment": "' +document.getElementsByName("comment-text")[0].value + '","issueId":"1"}',
				//data: comment,
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
var mapView;
var commentCollection;
var commentView = null;
var issueView = null;
var issueModel;
var global;*/