define([ 'underscore', 'backbone','view/AdminView', 
        'view/ManagerView', 'view/UserRegistrationView'], function(_, Backbone, AdminView, ManagerView,
																   UserRegistrationView) {

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
			"filter": "filter",
			"profile":"profile",
			"viewprofile":"viewprofile",
      "user-reg": "userReg",
			"email-confirm/*link" : "emailConfirm"
		},
		
		initialize : function() {
			Backbone.history.start();
		},

		home : function() {
			mapView.render();
		},
		
		profile : function() {
			profileView.render();
		},

		viewprofile : function() {
			viewUserProfile.render();
		},

		login: function(){
			loginView.render();
		},

		issue : function(id) {
			$('.col-1-3').hide();
			issueDetailsView.render(id);
			historyView.render(id);
			// comments must rendering from issue details view
		},

		cryOut : function() {

			mapView.render();
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
		},

		userReg : function ()  {

			userRegView.render();
		},

		emailConfirm: function(link) {
			loginView.confirmEmail(link);
		}
	});
	return Router
});
