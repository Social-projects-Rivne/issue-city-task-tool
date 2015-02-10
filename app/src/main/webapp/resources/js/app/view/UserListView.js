define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 
        'collection/UserCollection', 'view/UserView' ],
		function($, _, Backbone, UserModel, UserCollection, UserView) {
			var UserListView = Backbone.View.extend({
				initialize : function() {
					this.model = new UserCollection();
				},
				
				render : function() {
					var that = this;
					
					this.model.fetch( { success: function() {
						that.model.each(function(user) {
							var userView = new UserView( { model: user } );
							that.$el.append(userView.render().$el);
						});
					} } );
				}
			});	
			
			return UserListView;
		})
