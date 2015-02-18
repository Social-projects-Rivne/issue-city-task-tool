define([ 'jquery', 'underscore', 'backbone', 'text!templates/login.html', ],
		function($, _, Backbone, LoginTemplate) {
			var LoginView = Backbone.View.extend({
				
				loginTemplate: _.template(LoginTemplate),
				
				events: {
					'click #login': 'showLoginForm',
					'click .form-group #btn-close' : 'hideLoginForm',
				},
				
				initialize: function() {

				},

				showLoginForm: function() {
					this.render();
					router.navigate("login", {trigger: true});
				},
				
				hideLoginForm: function() {
					$(".login.modal").fadeOut();

					router.navigate("", {trigger: false});
				},
				
				render: function(){
					$('.login.modal').empty();
					$('.login.modal').append(this.loginTemplate);
					$(".login.modal").fadeIn();
				},
			});
			
			return LoginView;
		});
