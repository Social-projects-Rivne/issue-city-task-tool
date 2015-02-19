define([ 'jquery', 'underscore', 'backbone', 'collection/UserCollection', 'view/UserListView', 'text!templates/search.html', 'text!templates/AddUserTemplate.html', 'model/UserModel', 'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone, UserCollection, UserListView, SearchTemplate, AddUserTemplate, UserModel, NotificationTemplate) {
			
			var that = null;
	
			var AdminView = Backbone.View.extend({
				
				events: {
					'click #search-user'	: 'search',
					'click #reset-filter'	: 'resetFilter',
					'click #add-user'		: 'showAddUserForm',
					'click .addFormConfirm'	: 'addUser',
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
					userListView.render();
				},
				
				//function which will be search users by their name
				search: function(){
					router.navigate('admin/search/' + $('.form-control').val());
					usersFilter = userListView.model.findWhere({name: $('.form-control').val()});
					userListView.model = new UserCollection(usersFilter);
					userListView.render();
				},
				//it reset filters and render all users without filters
				resetFilter: function(){
					alert('we try to reset filter');
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
				},
				
				addUser: function(e) {
					$('#addModal').modal('hide');
					this.model.set( { 
						name: $('#add-user-form-name').val(),
						email: $('#add-user-form-email').val(),
						login: $('#add-user-form-login').val(),
						password: $('#add-user-form-password').val(),
						avatar: $('#add-user-form-avatar').val()
					} ).save( {}, {
						success: function(model, response) {
							if($('#notificationModal')) $('#notificationModal').remove();
							that.$el.append(that.notificationTemplate( { 'data': response } ));
							$('#notificationModal').modal();
						},
						error: function() {
							if($('#notificationModal')) $('#notificationModal').remove();
							that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							$('#notificationModal').modal();
						}
					} );
				}
			});
			
			return AdminView;
		})
