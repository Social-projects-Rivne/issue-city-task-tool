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
			$('.col-1-3').hide();
			issueDetailsView.render(id);
			// comments must rendering from issue details view
		},

		cryOut : function() {
			//router.navigate("/", {trigger: true}); 
			mapView.render();
			//router.navigate('cry-out', {trigger: false});
			addIssueView.render();
		},
		
		admin : function() {
			if(loginView.currentUser != null){
				adminView.render();
			} else{
				router.navigate('login', {trigger:true});

			}
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
