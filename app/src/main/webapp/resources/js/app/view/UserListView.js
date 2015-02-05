define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'collection/UserCollection' ],
		function($, _, Backbone, UserModel, UserCollection) {
			var UserListView = Backbone.View.extend({
				initialize : function() {
					this.model = new UserCollection();
					this.model.fetch();
				},
				
				render : function() {
					var that = this;
					this.model.each(function(user) {
						var userView = new UserView({model: user});
						that.$el.append(userView.render().$el);
					});
				}
			});	
			
			return UserListView;
		})
