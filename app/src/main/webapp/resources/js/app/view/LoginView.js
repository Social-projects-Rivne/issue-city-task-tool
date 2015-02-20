define([ 'jquery', 'underscore', 'backbone', 'text!templates/login.html', ],
		function($, _, Backbone, LoginTemplate) {
			var LoginView = Backbone.View.extend({
				
				loginTemplate: _.template(LoginTemplate),
				
				events: {

					'click #loginbox #btn-login': 'login',
					'click .navbar #login': 'showLoginForm',
					'click .form-group #btn-close' : 'hideLoginForm',
				},
				
				initialize: function() {
					$('.login.modal').empty();
					$('.login.modal').append(this.loginTemplate);
				},

				login: function(){					
					var login = $("#username").val();
					var password = $(" #password").val();
					if(login != "" && password != ""){
						console.log('Login: ' + login);
						console.log('Password: ' + password);

						$.ajax({
							url: 'login',
							type: 'POST',
							data: $("#loginForm").serialize(),
							success: function(data){
									console.log(data);
								},
						});	

					} else{
						console.log('Fields is empty');
					};
					
				},

				showLoginForm: function() {
					this.render();
					router.navigate("login", {trigger: true});
				},
				
				hideLoginForm: function() {
					$(".login.modal").modal('hide');

					router.navigate("", {trigger: false});
				},
				
				render: function(){
					$(".login.modal").modal();
				},
			});
			
			return LoginView;
		});
