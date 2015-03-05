define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/login.html', ],
		function($, _, Backbone, UserModel, LoginTemplate) {
			var LoginView = Backbone.View.extend({
				
				loginTemplate: _.template(LoginTemplate),
				
				events: {

					'click #loginbox #btn-login': 'login',
					'click .navbar #login': 'showLoginForm',
					'click .form-group #btn-close' : 'hideLoginForm',
					'click .input-group-addon' : 'passwordToggle',

				},
				
				initialize: function() {
					$('.login.modal').empty();
					$('.login.modal').append(this.loginTemplate);
				},

				login: function(){
					var user = null;
					var login = $("#j_username").val();
					var password = $(" #j_password").val();
					if(login != "" && password != ""){
						console.log('Login: ' + login);
						console.log('Password: ' + password);

						$.ajax({
							url: 'j_spring_security_check',
							type: 'POST',
							data: $("#loginForm").serialize(),
							success: function(data){
									console.log(data);
									user = new UserModel(data);
									console.log(user.toJSON());
									$(".login.modal").modal('hide');
								},
						});	
						
					} else{
						console.log('Fields is empty');
					};
					
				},

				passwordToggle: function(){
					if ($("#loginbox #password").attr('type') == "password"){
						$("#loginbox #password").attr('type','text');
						//change icom
						$(" .glyphicon-eye-open").attr('class','glyphicon icon-eye-close glyphicon-eye-close');
					} else {
						$("#loginbox #password").attr('type','password');
						$("#loginbox #password");
						//change icom
						$(" .glyphicon-eye-close").attr('class','glyphicon icon-eye-open glyphicon-eye-open');
					}
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
