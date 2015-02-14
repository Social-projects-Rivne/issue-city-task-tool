define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/edit_user_confirmation.html' ],
		function($, _, Backbone, UserModel, EditUserConfirmationTemplate) {
			var EditUserConfirmationView = Backbone.View.extend({
				
				template: _.template(EditUserConfirmationTemplate),
				
				render: function(id) {
					this.$el.append(this.template( { "id": id } ));
					
					return this;
				}
			});	
			
			return EditUserConfirmationView;
		})
