define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var Router = Backbone.Router.extend({

		routes : {
			"" : "home",
			"login" : "login",
			//routs for admin
			"admin" : "admin",
			"admin/search/:name" : "search",
			//manager's routs
			"manager" : "manager",
			//routs for map 
			"cry-out" : "cryOut",
			"issue/:id" : "issue", // #issue/1
			"filter": "filter"
		},
		
		initialize : function() {
			console.log('router initialazed');
			Backbone.history.start();
		},

		home : function() {
			mapView.render();
		},

		login: function(){
			loginView.render();
		},

		issue : function(id) {
			console.log('route to issue with id ' + id);
			$('.col-1-3').hide();
			issueDetailsView.render(id);
			// remove it when comments will be rendering from issue details view
			// fom
		},

		cryOut : function() {
			//router.navigate("/", {trigger: true}); 
			mapView.render();
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

		filter:function(){
			issueFilterView.render();
		},

		search : function(name) {
			//alert('you serch ' + name);
			//adminView.search(name);
		}
	});
	return Router
});
