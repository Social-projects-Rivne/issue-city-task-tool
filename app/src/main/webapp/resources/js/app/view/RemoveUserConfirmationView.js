define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/remove_user_confirmation.html' ],
		function($, _, Backbone, UserModel, RemoveUserConfirmationTemplate) {
			var RemoveUserConfirmationView = Backbone.View.extend({
				
				template: _.template(RemoveUserConfirmationTemplate),
				
				render: function(id) {
					this.$el.append(this.template( { "id": id } ));
					
					return this;
				},
			});	
			
			return RemoveUserConfirmationView;
		})
