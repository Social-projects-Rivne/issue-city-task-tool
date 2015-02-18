define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 
        'collection/UserCollection', 'view/UserView', 'text!templates/Admin.html', 'view/RemoveUserConfirmationView', 'view/EditUserConfirmationView', 'text!templates/ConfirmationTemplate.html', 'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone, UserModel, UserCollection, UserView, AdminTemplate, RemoveUserConfirmationView, EditUserConfirmationView, ConfirmationTemplate, NotificationTemplate) {
			
			var that = null;
	
			var UserListView = Backbone.View.extend({
				
				initialize : function() {
					this.model = new UserCollection();
					this.model.fetch();
					this.model.on('remove', this.render, this);
					that = this;
						
				},
				
				events: {
					'click .btn.glyphicon-remove': 'removeConfirmation',
					'click .confirm': 'confirm',
					'click .reject': 'reject'
				},
				
				template: _.template(AdminTemplate),
				confirmationTemplate: _.template(ConfirmationTemplate),
				notificationTemplate: _.template(NotificationTemplate),
				
				render: function() {
					this.$el.html(this.template);
					this.model.each(function(user) {
						var userView = new UserView( { model: user } );
						that.$el.find("table").append(userView.render().$el);
					});
				},
				
				removeConfirmation: function(e) {
					this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to delete this user?' }, { 'userId': e.currentTarget.id }, { 'action': 'delete' } ] } ));
					$('#confirmationModal').modal();
				},
				
				confirm: function(e) {
					$('#confirmationModal').modal('hide');
					if(e.currentTarget.name == 'delete') {
						this.model.get(e.currentTarget.id).destroy( { url: 'remove-user/' + e.currentTarget.id,
							success: function(model, response) {
								that.$el.append(that.notificationTemplate( { 'data': response } ));
								$('#notificationModal').modal();
							},
							error: function() {
								that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
								$('#notificationModal').modal();
							}
						} );
					}
				},
				
				reject: function() {
					$('#confirmationModal').modal('hide');
				}
			});	
			
			return UserListView;
		})
