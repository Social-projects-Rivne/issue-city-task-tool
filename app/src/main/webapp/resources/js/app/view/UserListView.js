define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 
        'collection/UserCollection', 'view/UserView', 'text!templates/Admin.html', 'view/RemoveUserConfirmationView' ],
		function($, _, Backbone, UserModel, UserCollection, UserView, AdminTemplate, RemoveUserConfirmationView) {
			var UserListView = Backbone.View.extend({
				initialize : function() {
					this.model = new UserCollection();
				},
				
				events: {
					'click .glyphicon-remove': 'confirmation'
				},
				
				template: _.template(AdminTemplate),
				
				render : function() {
					var that = this;
					removeUserConfirmationView = new RemoveUserConfirmationView( { el: "#container" } );
					
					this.model.fetch( { success: function() {
						that.$el.html(that.template);
						that.model.each(function(user) {
							var userView = new UserView( { model: user } );
							that.$el.find("table").append(userView.render().$el);
						});
					} } );
				},
				
				confirmation: function() {
					removeUserConfirmationView.render();
				}
			});	
			
			return UserListView;
		})
