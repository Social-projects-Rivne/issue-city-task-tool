define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/remove_user_confirmation.html' ],
		function($, _, Backbone, UserModel, RemoveUserConfirmationTemplate) {
			var RemoveUserConfirmationView = Backbone.View.extend({
				initialize: function() {
					this.model = new UserModel();
				},
				
				template: _.template(RemoveUserConfirmationTemplate),
				
				events: {
					'click #confirm-remove': 'confirmRemove',
					'click #reject-remove': 'rejectRemove'
				},
				
				render: function(id) {
					this.$el.append(this.template( { "id": id } ));
					
					return this;
				},
				
				confirmRemove: function(e) {
					this.model.set('id', e.currentTarget.name);
					this.model.destroy( { url: 'remove-user/' + this.model.get('id') } );
				},
			});	
			
			return RemoveUserConfirmationView;
		})
