define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var Router = Backbone.Router.extend({

		routes : {
<<<<<<< HEAD
			"" 						: 	"home"		,
			"admin/search/:name" 	: 	"search"	,
			"admin/add-user" 		: 	"addUser"	,
			"manager" 				: 	"manager"	,
			"cry-out" 				: 	"cryOut"	,
			"admin" 				: 	"admin"		,
			"issue/:id" 			: 	"issue"		, 	// #issue/1
			"admin/edit-user"		:	"editUser"	,	 	
=======
			"" : "home",
			//routs for admin
			"admin" : "admin",
			"admin/search/:name" : "search",
			"admin/add-user" : "addUser",
			//manager's routs
			"manager" : "manager",
			//routs for map 
			"cry-out" : "cryOut",
			"issue/:id" : "issue", // #issue/1
>>>>>>> 9163023a164c69549da9700d4685a971fc1d0322
		},
		
		initialize : function() {
			console.log('router initialazed');
			Backbone.history.start();
		},

		home : function() {
			mapView.render();
		},

		issue : function(id) {
			console.log('route to issue with id ' + id);
			$('.col-1-3').hide();
			issueDetailsView.render(id);
			// remove it when comments will be rendering from issue details view
			// fom
			commentListView.render(id);
		},

		cryOut : function() {
			router.navigate("/", {trigger: true}); 
			router.navigate('cry-out', {trigger: false});
			addIssueView.render();
		},
		
		admin : function() {
			userListView.model.fetch({ success: function(){
					adminView.render();
				}
			
			});
		},
		
		manager : function(){
			managerView.render();
		},
		
		addUser: function() {
			adminView.addUser();
		},
		
		search : function(name) {
			//alert('you serch ' + name);
			//adminView.search(name);
		},
		
		editUser: function() {
			//adminView.addUser();
			// must be function from ??file??
		},

	});
	return Router
});
