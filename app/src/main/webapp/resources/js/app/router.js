define([ 'underscore', 'backbone','view/AdminView', 
        'view/ManagerView',], function(_, Backbone, AdminView, ManagerView) {

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
			setTimeout(function(){
				$('.col-1-3').hide();
				issueDetailsView.render(id);
			}, 400);
			// comments must rendering from issue details view
		},

		cryOut : function() {
			//router.navigate("/", {trigger: true}); 
			mapView.render();
			//router.navigate('cry-out', {trigger: false});
			addIssueView.render();
		},
		
		admin : function() {
			if((loginView.currentUser != null)&&(adminView != null)){
				adminView.render();
			} else{
				if (adminView == null) {
					adminView = new AdminView( { el: "#container" } );
					managerView = new ManagerView({el:"#container"})
					adminView.render();
					this.navigate('admin', {trigger:false});
				} else {
					router.navigate('login', {trigger:true});
				}
			}
		},
		
		manager : function(){
			if((loginView.currentUser != null)&&(managerView != null)){
				managerView.render();
			} else{
				if (managerView == null) {
					managerView = new ManagerView( { el: "#container" } );
					managerView.render();
					this.navigate('manager', {trigger:false});
				} else {
					router.navigate('login', {trigger:true});
				}
			}
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
