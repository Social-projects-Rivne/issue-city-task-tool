define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/edit_user_confirmation.html' ],
		function($, _, Backbone, UserModel, EditUserConfirmationTemplate) {
			var EditUserConfirmationView = Backbone.View.extend({
				initialize: function() {
					this.model = new UserModel();
				},
				
				template: _.template(EditUserConfirmationTemplate),
				
				events: {
					'click #confirm-edit': 'confirmEdit',
					'click #reject-edit': 'rejectEdit'
				},
				
				render: function(id) {
					this.$el.append(this.template( { "id": id } ));
					
					return this;
				},
				
				rejectEdit: function(e) {
					// will define function body
				},
				
				confirmEdit: function(e) {
					// will define function body
				},
			});	
			
			return EditUserConfirmationView;
		})
