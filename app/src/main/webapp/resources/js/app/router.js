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

			"user-reg": "userReg",
			"email-confirm/:link" : "emailConfirm"
		},
		
		initialize : function() {
			console.log('router initialazed');
			Backbone.history.start();
		},

		home : function() {
			mapView.render();
		},
		
		profile : function() {
			profileView.render();
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
			console.log('userreg function');

			userRegView.render();
		},

		emailConfirm: function(link) {
			loginView.confirmEmail(link);
		}
	});
	return Router
});
