define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 
        'collection/UserCollection', 'view/UserView', 'text!templates/Admin.html' ],
		function($, _, Backbone, UserModel, UserCollection, UserView, AdminTemplate) {
			var UserListView = Backbone.View.extend({
				initialize : function() {
					this.model = new UserCollection();
				},
				
				template: _.template(AdminTemplate),
				
				render : function() {
					var that = this;
					
					this.model.fetch( { success: function() {
						that.$el.html(that.template);
						that.model.each(function(user) {
							var userView = new UserView( { model: user } );
							that.$el.find("table").append(userView.render().$el);
						});
					} } );
				}
			});	
			
			return UserListView;
		})
