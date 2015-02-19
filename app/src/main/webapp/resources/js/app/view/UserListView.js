define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 
        'collection/UserCollection', 'view/UserView', 'text!templates/Admin.html', 'text!templates/ConfirmationTemplate.html', 'text!templates/NotificationTemplate.html', 'text!templates/EditUserTemplate.html' ],
		function($, _, Backbone, UserModel, UserCollection, UserView, AdminTemplate, ConfirmationTemplate, NotificationTemplate, EditUserTemplate) {
			
			var that = null;
	
			var UserListView = Backbone.View.extend({
				
				initialize : function() {
					this.model = new UserCollection();
					this.model.fetch();
					this.model.on('remove', this.render, this);
					that = this;
						
				},
				
				events: {
					'click .btn.glyphicon-pencil': 'showEditForm',
					'click .editFormConfirm': 'editConfirm',
					'click .btn.glyphicon-remove': 'showRemoveConfirmation',
					'click .confirm': 'confirm',
				},
				
				template: _.template(AdminTemplate),
				confirmationTemplate: _.template(ConfirmationTemplate),
				notificationTemplate: _.template(NotificationTemplate),
				editUserTemplate: _.template(EditUserTemplate),
				
				render: function() {
					this.$el.html(this.template);
					this.model.each(function(user) {
						var userView = new UserView( { model: user } );
						that.$el.find("table").append(userView.render().$el);
					});
				},
				
				showEditForm: function(e) {
					this.$el.append(this.editUserTemplate( { 'data': this.model.get(e.currentTarget.id) } ));
					$('#editModal').modal();
				},
				
				editConfirm: function(e) {
					this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to edit this user?' }, { 'userId': e.currentTarget.id }, { 'action': 'edit' } ] } ));
					$('#confirmationModal').modal();
				},
				
				showRemoveConfirmation: function(e) {
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
					if(e.currentTarget.name == 'edit') {
						this.model.get(e.currentTarget.id).set( {
							name: $('#userName').val(),
							email: $('#userEmail').val(),
							login: $('#userLogin').val(),
							password: $('#userPassword').val(),
							avatar: $('#userAvatar').val()
						} ).save( { url: 'edit-user',
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
				}
			});	
			
			return UserListView;
		})
