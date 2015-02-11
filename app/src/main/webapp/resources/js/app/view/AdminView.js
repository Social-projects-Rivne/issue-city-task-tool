define([ 'jquery', 'underscore', 'backbone', 'collection/UserCollection', 'view/UserListView', 'text!templates/search.html', ],
		function($, _, Backbone, UserCollection, UserListView, SearchTemplate) {
			var AdminView = Backbone.View.extend({
				
				events: {
					'click #search-user': 'search'
				},
				
				template: _.template(SearchTemplate),
				userListView: null,
				initialize: function() {
					userListView = new UserListView({el: "#container"});
					console.log('navigate to admin');
				},
				
				render: function() {
					
					userListView.render();
				},
				
				
				search: function(){

				},
				
			});	
			
			return AdminView;
		})
