
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
        'view/StatisticView',
        'view/ProfileView',
        'view/ImageEditorView',
		'gmaps'

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
	StatisticView,
	ProfileView,
	ImageEditorView) {
	
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
		profileView = new ProfileView({el:"#container"});
		imageEditorView = new ImageEditorView( { el: 'body' } );


		router = new Router();


	});
	

});
