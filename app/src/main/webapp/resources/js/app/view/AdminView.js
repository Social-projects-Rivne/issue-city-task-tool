define([ 'jquery', 'underscore', 'backbone', 'collection/UserCollection', 'view/UserListView', 'text!templates/search.html', 'text!templates/AddUserTemplate.html', 'model/UserModel', 'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone, UserCollection, UserListView, SearchTemplate, AddUserTemplate, UserModel, NotificationTemplate) {
			
			var that = null;
	
			var AdminView = Backbone.View.extend({
				
				events: {
					'click #search-user'	: 'search',
					'click #reset-filter'	: 'resetFilter',
					'click #add-user'		: 'showAddUserForm',
					'click .addFormConfirm'	: 'addUser',
					'click #left_admin_panel #admin_log_out'	: 'logOut',
				},
				
				template: _.template(SearchTemplate),
				addUserTemplate: _.template(AddUserTemplate),
				notificationTemplate: _.template(NotificationTemplate),		  

				//view of table
				userListView: null,
				//colection of finded users
				usersFilter: null,
				//origin user cillectio (without filters)
				usersList: null,

				initialize: function() {
					this.model = new UserModel();
					
					userListView = new UserListView({el: "#container"});
					usersFilter = userListView.model;
					usersList = new  UserCollection(usersFilter);
					that = this;
				},
				
				render: function() {
					userListView.model.fetch({ success: function(){
							userListView.render();
						}
			
					});
				},
				
				//function which will be search users by their name
				search: function(){
					router.navigate('admin/search/' + $('.search .form-control').val());
					usersFilter = userListView.model.findWhere({name: $('.search .form-control').val()});
					userListView.model = new UserCollection(usersFilter);
					userListView.render();
				},
				//it reset filters and render all users without filters
				resetFilter: function(){
					userListView.model.fetch({success: function(){
							router.navigate('admin');
							userListView.render();
						}
					});
				},
				
				showAddUserForm: function(e) {
					if($('#addModal')) $('#addModal').remove();
					this.$el.append(this.addUserTemplate);
					$('#addModal').modal();
					
					userName = $('#add-user-form-name');
					userEmail = $('#add-user-form-email');
					userLogin = $('#add-user-form-login');
					userPassword = $('#add-user-form-password');
					userAvatar = $('#add-user-form-avatar');
					
					userName.on('blur', function() {
						if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
							this.value = 'Wrong name!';
							this.style.color = 'red';
						}
					});
					
					userName.on('focus', function() {
						if (this.value == 'Wrong name!') this.value ='';
						this.style.color = 'black';
					});
					
					userEmail.on('blur', function() {
						if (!/^[a-z0-9_.]+\@[a-z0-9]+\.[a-z0-9]+$/.test(this.value)) {
							this.value = 'Wrong email!';
							this.style.color = 'red';
						}
					});
					
					userEmail.on('focus', function() {
						if (this.value == 'Wrong email!') this.value ='';
						this.style.color = 'black';
					});
					
					userLogin.on('blur', function() {
						if (!/^[A-Za-z0-9_.-]+$/.test(this.value)) {
							this.value = 'Wrong login!';
							this.style.color = 'red';
						}
					});
					
					userLogin.on('focus', function() {
						if (this.value == 'Wrong login!') this.value ='';
						this.style.color = 'black';
					});
					
					userPassword.on('blur', function() {
						if (!/^[A-Za-z0-9_.-]+$/.test(this.value)) {
							$(this).attr('type', 'text');
							this.value = 'Wrong password!';
							this.style.color = 'red';
						}
					});
					
					userPassword.on('focus', function() {
						if (this.value == 'Wrong password!') {
							this.value ='';
							$(this).attr('type', 'password');
							this.style.color = 'black';
						}
					});
				},
				
				logOut: function(){
					$.ajax('auth/logout');
					loginView.currentUser = null;
					router.navigate('', {trigger:true});
					if($('#notificationModal'))
						$('#notificationModal').remove();
					that.$el.append(that.notificationTemplate( { 'data': { 'message': "You have been successfully logged out!" }} ));
					$('#notificationModal').modal();
					loginView.buttonsManage();
				},

				addUser: function(e) {
					var isValid = true;
					
					if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(userName.val())) {
						userName.val('Wrong name!').css('color', 'red');
						isValid = false;
					}
					// old regexp for email: ^[a-z0-9_.]+\@[a-z0-9]+\.[a-z0-9]+$  (don't matched xx@xx.xx.xx)
					if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(userEmail.val())) {
						userEmail.val('Wrong email!').css('color', 'red');
						isValid = false;
					}
					
					if (!/^[A-Za-z0-9_.-]+$/.test(userLogin.val())) {
						userLogin.val('Wrong login!').css('color', 'red');
						isValid = false;
					}
					
					if (!/^[A-Za-z0-9_.-]+$/.test(userPassword.val())) {
						userPassword.val('Wrong password!').css('color', 'red').attr('type', 'text');
						isValid = false;
					}
					
					if(isValid) {
						$('#addModal').modal('hide');
						this.model.set( { 
							name: $('#add-user-form-name').val(),
							email: $('#add-user-form-email').val(),
							login: $('#add-user-form-login').val(),
							password: $('#add-user-form-password').val(),
							avatar: $('#add-user-form-avatar').val(),
							role_id: $('#userRole').val(),
						} ).save( {}, {
							success: function(model, response) {
								userListView.model.fetch( { success: function() {
									that.render();
									if($('#notificationModal')) $('#notificationModal').remove();
									that.$el.append(that.notificationTemplate( { 'data': response } ));
									$('#notificationModal').modal();
								} } );
							},
							error: function() {
								if($('#notificationModal')) $('#notificationModal').remove();
								that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
								$('#notificationModal').modal();
							}
						} );
					}
					
				}

			});
			
			return AdminView;
		})
