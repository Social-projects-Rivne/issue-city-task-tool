define([ 'jquery', 'underscore', 'backbone', 'collection/UserCollection', 'view/UserListView', 'text!templates/search.html', 'text!templates/Add_user.html', ],
		function($, _, Backbone, UserCollection, UserListView, SearchTemplate, AddUserTemplate) {
			var AdminView = Backbone.View.extend({
				
				events: {
					'click #search-user': 'search',
					'click #reset-filter': 'resetFilter',
					'click #add-user': 'addUser',
					
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
					var name = '';
					if(name == ''){
						name = $('.form-control').val();
					}
					router.navigate('admin/search/' + name);
					usersFilter = userListView.model.findWhere({name: name});
					
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
					this.$el.html(this.addUserTemplate);
					console.log ("function run");
				}
					
				
			});	
			
			return AdminView;
		})
