define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var Router = Backbone.Router.extend({

		routes : {
			"admin/search/:name" : "search",
			"admin/add-user" : "addUser",
			"" : "home",
			"cry-out" : "cryOut",
			"admin" : "admin",
			"issue/:id" : "issue", // #issue/1
		},
		
		initialize : function() {
			console.log('router initialazed');
			Backbone.history.start();
		},

		home : function() {
			alert('welcome home');
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
			addIssueView.render();
		},
		
		admin : function() {
			userListView.model.fetch({ success: function(){
					adminView.render();
				}
			
			});
		},
		addUser: function() {
			adminView.addUser();
		},
		
		search : function(name) {
			//alert('you serch ' + name);
			//adminView.search(name);
		},

	});
	return Router
});
