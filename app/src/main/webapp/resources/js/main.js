
require([
        'jquery',
        'underscore',
        'backbone',
        'router',
        'view/MapView',
        'view/AddIssueView',
        'view/ManagerView',
        'view/LoginView',
        'view/IssueFilterView',
        'view/StatisticView',
        'view/ProfileView',
	'view/ViewUserProfile',
        'view/ImageEditorView',
		"view/UserRegistrationView"

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
	ViewUserProfile,
	ImageEditorView,
	UserRegistrationView) {

	//var comments = null;
	router = null;
	adminView = null;
	managerView = null;
	userRegView = null;
	USER_NOT_CONFIRMED = -1;
	USER = 0;

	jQuery(document).ready(function($){
		// show login form on unauthorized response
		$.ajaxSetup({statusCode: {401: function(){router.navigate('login', {trigger: true}); } } });
		mapView = new MapView( { el: "body" } );
		mapView.render();
		issueFilterView = new IssueFilterView({el:"#container"});
		
		loginView = new LoginView({el:"body"});
		userRegView = new UserRegistrationView( {el: "body"});

		issueFilterView = new IssueFilterView({el:"#container"});
		
		statisticView = new StatisticView( { el: 'body' } );
		profileView = new ProfileView({el:"#container"});
		viewUserProfile = new ViewUserProfile({el:"#container"});
		imageEditorView = new ImageEditorView( { el: 'body' } );


		router = new Router();


	});
	

});
