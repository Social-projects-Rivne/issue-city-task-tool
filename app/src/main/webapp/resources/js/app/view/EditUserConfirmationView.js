//RequireJS define options
define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/edit_user_confirmation.html', 'text!templates/Add_user.html', 'text!templates/Update_user.html', ],
		function($, _, Backbone, UserModel, EditUserConfirmationTemplate, AddUserTemplate, UpdateUserTemplate) {
			var EditUserConfirmationView = Backbone.View.extend({
				
				events: {
					'click #confirm-edit'	: 'confirmEdit',  //button "Yes" into modale window: editUserConfirmationTemplate
					'click #reject-reject'	: 'rejectEdit',   //button "No" into modale window: editUserConfirmationTemplate
					'click #update-user'	: 'updateUser'    //button "Update" into window: updateUserTemplate
				},
				
				// templates into this View
				editUserConfirmationTemplate: _.template(EditUserConfirmationTemplate),
				updateUserTemplate: _.template(UpdateUserTemplate),
				
				// render for modale window: editUserConfirmationTemplate
				// called from editConfirmation: function(e)
				render: function(id) {
					this.$el.append(this.editUserConfirmationTemplate( { "id": id } ));
					//console.log('Render EditUser id user' + id);
					return this;
				},
				//Bind confirmEdit button for call updateUserTemplate
				confirmEdit: function(){
					//router.navigate('admin/add-user');
					//alert ("function run");
					this.$el.html(this.updateUserTemplate);
					
				}
				
			});	
						
			return EditUserConfirmationView;
		})

		
		
		