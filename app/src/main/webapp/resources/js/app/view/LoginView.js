define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/login.html', ],
		function($, _, Backbone, UserModel, LoginTemplate) {
			var LoginView = Backbone.View.extend({
				
				loginTemplate: _.template(LoginTemplate),
				currentUser: null,
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
					that = this;
					var login = $("#j_username").val();
					var password = $(" #j_password").val();
					if(login != "" && password != ""){
						$.ajax({
							url: 'j_spring_security_check',
							type: 'POST',
							data: $("#loginForm").serialize(),
							success: function(){
								console.log();
								$.ajax({ 
									contentType:'applicetaion/json',
									url: 'currentuser',
									success: function(data){
										that.currentUser = new UserModel(data);

										if(that.currentUser.get('role_id') == 1){
											router.navigate('admin',{trigger:true});
										} else if(that.currentUser.get('role_id') == 2){
											router.navigate('manager',{trigger:true});
										} else {
											// some notification for user
										}
										that.hideLoginForm()
									}
								});
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
