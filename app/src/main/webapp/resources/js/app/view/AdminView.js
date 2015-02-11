define([ 'jquery', 'underscore', 'backbone', 'collection/UserCollection', 'view/UserListView', 'text!templates/search.html', ],
		function($, _, Backbone, UserCollection, UserListView, SearchTemplate) {
			var AdminView = Backbone.View.extend({
				
				events: {
					'click #search-user': 'search',
					'click #reset-filter': 'resetFilter',
				},
				
				template: _.template(SearchTemplate),

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
				search: function(userName){
					var name = userName;
					if(userName){
						name = $('.form-control').val();
					}
					//router.navigate('admin/search/' + name);
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
				
			});	
			
			return AdminView;
		})
