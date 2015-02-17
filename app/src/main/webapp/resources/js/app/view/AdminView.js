define([ 'jquery', 'underscore', 'backbone', 'collection/UserCollection', 'view/UserListView', 'text!templates/search.html', 'text!templates/Add_user.html', 'model/UserModel'],
		function($, _, Backbone, UserCollection, UserListView, SearchTemplate, AddUserTemplate, UserModel) {
			var AdminView = Backbone.View.extend({
				
				events: {
					'click #search-user'	: 'search',
					'click #reset-filter'	: 'resetFilter',
					'click #add-user'		: 'addUser',
					'click #add-new-user'	: 'addNewUser',
					//'click #edit-user'	: 'editUser',
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
				// function which adding users 
				addUser: function(){
					//router.navigate('admin/add-user');
					this.$el.html(this.addUserTemplate);
					console.log ("AdminView - addUser: function run");
				},
				
				// function which confirm add user on addUserTemplate
				// must be in outer file
				addNewUser: function(){
					userModel = new UserModel({
						 "name": $(document.getElementsByName('name')[0]).val(),
						 "email": $(document.getElementsByName('email')[0]).val(),
						 "login":  $(document.getElementsByName('login')[0]).val(),
						 "password":  $(document.getElementsByName('password')[0]).val(),
						 "avatar":  $(document.getElementsByName('avatar')[0]).val()
					});
					userModel.save();
					console.log (userModel);
				},	
				
				// function which editing users
				// must be in outer file
				//				editUser: function(){
				//					var editUser = new UserModel({id:id})	
				//										
				//					//console.log ("function run");
				//				}
					
			});
			
			return AdminView;
		})
