define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'view/AdminView', 
        'view/ManagerView','text!templates/login.html', 'text!templates/NotificationTemplate.html'],
		function($, _, Backbone, UserModel, AdminView, ManagerView, LoginTemplate, NotificationTemplate) {
			var that = null;
			var LoginView = Backbone.View.extend({
				
				loginTemplate: _.template(LoginTemplate),
				currentUser: null,
				notificationTemplate: _.template(NotificationTemplate),


				events: {
					'click #loginbox #btn-login': 'login',
					'click .navbar #login': 'showLoginForm',
					'click .form-group #btn-close' : 'hideLoginForm',
					'click .input-group-addon' : 'passwordToggle'

				},
				
				initialize: function() {
					this.getCurrentUser();
					$('.login.modal').empty();
					$('.login.modal').append(this.loginTemplate);
					
				},

				getCurrentUser: function(){
					that = this;
					$.ajax({ contentType:'applicetaion/json',
							 url: 'currentuser',
							 //when request done we create admin or manager view and rout user on his page
							 success: function(data){
								that.currentUser = new UserModel(data);
								if(that.currentUser.get('avatar')==""){
									that.currentUser.set({'avatar':'resources/img/avatar.png'});
								}
								that.buttonsManage();
							}
						});
				},

				login: function(){
					that = this;
					var login = $("#j_username").val();
					var password = $(" #j_password").val();
					if(login != "" && password != ""){
						//send user's login and password
						$.ajax({
							url: 'auth/login',
							type: 'POST',
							data: $("#loginForm").serialize(),
							//if request done
							success: function(){
								//get logined user model
								$.ajax({ 
									contentType:'applicetaion/json',
									url: 'currentuser',
									//when request done we create admin or manager view and rout user on his page
									success: function(data){
										that.currentUser = new UserModel(data);
										//routing by user's role

										//If user didn't validate his email
										if(that.currentUser.get('role_id') == USER_NOT_CONFIRMED){

											//TODO must be beter way to do it
											$.ajax('auth/logout');
											loginView.currentUser = null;
											router.navigate('', {trigger:true});

											if($('#notificationModal'))
												$('#notificationModal').remove();
											that.$el.append(that.notificationTemplate( { 'data': { 'message': "You should validate your email " }} ));
											$('#notificationModal').modal();


											return;
										}

										else if(that.currentUser.get('role_id') == 1){
											adminView = new AdminView( { el: "#container" } );
											managerView = new ManagerView({el:"#container"})
											router.navigate('admin',{trigger:true});
										} else if(that.currentUser.get('role_id') == 2){
											managerView = new ManagerView({el:"#container"})
											router.navigate('manager',{trigger:true});
										} else {
											// some notification for user
										}
										if(that.currentUser.get('avatar')==""){
											that.currentUser.set({'avatar':'resources/img/avatar.png'});
										}
										that.hideLoginForm();
										that.buttonsManage();
									}
								});
							}
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
					//router.navigate("", {trigger: false});
				},
				
				render: function(){
					$(".login.modal").modal();
				},

				buttonsManage: function(){
					if(this.currentUser == null || this.currentUser.get('login') ==""){
						$('.navbar  #login').show();
						$('.navbar  #admin').hide();
						$('.navbar  #manager').hide();
					} else {
						$('.navbar  #login').hide();
						if(this.currentUser.get('role_id') == 1){
							$('.navbar  #admin').show();
							$('.navbar  #manager').show();
						} else{ 
							if(this.currentUser.get('role_id') == 2 ){
								$('.navbar  #admin').hide();
								$('.navbar  #manager').show();
							} 
						}
					}
				},

				confirmEmail: function(link) {
					arrLink = link.split("&id=");
					encryptPass = arrLink[0];
					user_id = arrLink[1];
					var that = this;


					this.currentUser = new UserModel({
						id : user_id,
						password : encryptPass
					});

					that = this;

					$.ajax({
						url: "validate-user",
						type: "POST",
						data: JSON.stringify(this.currentUser),
						dataType: "json",
						contentType: "application/json; charset=utf-8",

						success: function(data) {
							if($('#notificationModal')) {
								$('#notificationModal').remove();
							}
							that.$el.append(that.notificationTemplate({'data': {'message': "Your email has validated. Have a nice day "}}));
							$('#notificationModal').modal();
						},
						error: function(data) {
							if($('#notificationModal')) {
								$('#notificationModal').remove();
							}
							that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							$('#notificationModal').modal();
						}
					});

				}
			});
			
			return LoginView;
		});