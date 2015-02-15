define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 
        'collection/UserCollection', 'view/UserView', 'text!templates/Admin.html', 'view/RemoveUserConfirmationView', 'view/EditUserConfirmationView' ],
		function($, _, Backbone, UserModel, UserCollection, UserView, AdminTemplate, RemoveUserConfirmationView, EditUserConfirmationView) {
			
			var that = null;
	
			var UserListView = Backbone.View.extend({
				
				
				
				initialize : function() {
					this.model = new UserCollection();
					that = this;
					this.model.fetch();
						
				},
				
				events: {
					'click .glyphicon-remove': 'removeConfirmation',
					'click .glyphicon-pencil': 'editConfirmation',
					'click #confirm-remove': 'confirmRemove',
					'click #reject-remove': 'rejectRemove'
				},
				
				template: _.template(AdminTemplate),
				
				render : function() {
					
					removeUserConfirmationView = new RemoveUserConfirmationView( { el: "#container" } );
					editUserConfirmationView = new EditUserConfirmationView( { el: "#container" } );
					
					//this.model.fetch( { success: function() {
						that.$el.html(that.template);
						that.model.each(function(user) {
							var userView = new UserView( { model: user } );
							that.$el.find("table").append(userView.render().$el);
						});
				//	} } );
				},
				
				removeConfirmation: function(e) {
					removeUserConfirmationView.render(e.currentTarget.parentNode.id);
				},
				
				editConfirmation: function(e) {
					editUserConfirmationView.render(e.currentTarget.parentNode.id);
				},
				
				confirmRemove: function(e) {
					var user = new UserModel();
					user.set('id', e.currentTarget.name);
					user.destroy( { url: 'remove-user/' + user.get('id') } );
					that.render();
				}
			});	
			
			return UserListView;
		})
