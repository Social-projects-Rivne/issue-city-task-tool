define([ 'jquery', 'underscore', 'backbone', 'collection/UserCollection', 'view/UserListView', 'text!templates/search.html', 'text!templates/AddUserTemplate.html', 'model/UserModel'],
		function($, _, Backbone, UserCollection, UserListView, SearchTemplate, AddUserTemplate, UserModel) {
			
			var that = null;
	
			var AdminView = Backbone.View.extend({
				
				events: {
					'click #search-user'	: 'search',
					'click #reset-filter'	: 'resetFilter',
					'click #add-user'		: 'showAddUserForm',
					'click #addFormConfirm'	: 'addUser',
				},
				
				template: _.template(SearchTemplate),
				addUserTemplate: _.template(AddUserTemplate),
						  

				//view of table
				userListView: null,
				//colection of finded users
				usersFilter: null,
				//origin user cillectio (without filters)
				usersList: null,

				initialize: function() {
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
				}
			});
			
			return AdminView;
		})
