define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 
        'collection/UserCollection', 'view/UserView', 'text!templates/Admin.html', 'view/RemoveUserConfirmationView', 'view/EditUserConfirmationView', 'text!templates/ConfirmationTemplate.html', 'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone, UserModel, UserCollection, UserView, AdminTemplate, RemoveUserConfirmationView, EditUserConfirmationView, ConfirmationTemplate, NotificationTemplate) {
			
			var that = null;
	
			var UserListView = Backbone.View.extend({
				
				
				
				initialize : function() {
					this.model = new UserCollection();
					that = this;
					this.model.fetch();
						
				},
				
				events: {
					'click .btn.glyphicon-remove': 'removeConfirmation',
					'click .confirm': 'confirm',
					'click .reject': 'reject'
				},
				
				template: _.template(AdminTemplate),
				confirmationTemplate: _.template(ConfirmationTemplate),
				notificationTemplate: _.template(NotificationTemplate),
				
				render : function() {
					
					removeUserConfirmationView = new RemoveUserConfirmationView( { el: "#container" } );
					editUserConfirmationView = new EditUserConfirmationView( { el: "#container" } );
					
					//this.model.fetch( { success: function() {
						that.$el.html(that.template);
						that.model.each(function(user) {
							var userView = new UserView( { model: user } );
							that.$el.find("table").append(userView.render().$el);
						});
				//	} } );
				},
				
				removeConfirmation: function(e) {
					this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to delete this user?' }, { 'userId': e.currentTarget.id }, { 'action': 'delete' } ] } ));
					$('#confirmationModal').modal();
				},
				
				confirm: function(e) {
					$('#confirmationModal').modal('hide');
					var user = new UserModel();
					user.set('id', e.currentTarget.id);
					if(e.currentTarget.name == 'delete') {
						user.destroy( { url: 'remove-user/' + user.get('id'),
							success: function(data) {
								that.$el.append(that.notificationTemplate( { 'data': data } ));
								$('#notificationModal').modal(); console.log(data);
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
