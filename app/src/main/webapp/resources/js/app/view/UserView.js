define([ 'jquery', 'underscore', 'backbone', 'model/UserModel' ],
		function($, _, Backbone, UserModel) {
			var UserView = Backbone.View.extend({
				tagName : "tr",
				
				initialize : function() {
					this.model = new UserModel();
				},
				
				render : function() {
					this.$el.html("<td>" + this.model.get("login") + "</td>" +
							"<td>" + this.model.get("email") + "</td>" +
							"<td>" + this.model.get("name") + "</td>");
					
					return this;
				}
			});	
			
			return UserView;
		})
