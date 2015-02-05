define([ 'jquery', 'underscore', 'backbone', 'model/UserModel' ],
		function($, _, Backbone, UserModel) {
			var UserView = Backbone.View.extend({
				initialize : function() {
					this.model = new UserModel();
				},
				
				render : function() {
					this.$el.html();
				}
			});	
			
			return UserView;
		})
