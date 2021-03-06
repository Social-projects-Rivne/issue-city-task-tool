define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'model/IssueModel', 'model/CategoryModel',
        'collection/UserCollection','model/SendingNotificationModel','view/UserView', 'text!templates/Admin.html', 'text!templates/ConfirmationTemplate.html', 'text!templates/NotificationTemplate.html', 'text!templates/EditUserTemplate.html','text!templates/SendingNotification.html' ],
		function($, _, Backbone, UserModel, IssueModel, CategoryModel, UserCollection, SendingNotificationModel, UserView, AdminTemplate, ConfirmationTemplate, NotificationTemplate, EditUserTemplate, SendingNotificationTemplate) {
			
			var that = null;
			function l (x) {
				console.log(x);
			}

			return Backbone.View.extend({

				template: _.template(AdminTemplate),
				confirmationTemplate: _.template(ConfirmationTemplate),
				notificationTemplate: _.template(NotificationTemplate),
				editUserTemplate: _.template(EditUserTemplate),
				SendingNottificationTemplate: _.template(SendingNotificationTemplate),
				
				initialize : function() {

					this.model = new UserCollection();
					this.SNmodel = new SendingNotificationModel();

					this.model.fetch();
					this.model.on('remove', this.render, this);
					this.model.on('change', this.render, this);
					that = this;
			
				},

				render: function() {
					//that = this;
					//this.model.fetch();
					//this.model.on('remove', this.render(), this);
					//this.model.on('change', this.render(), this);
					this.$el.html(this.template);
					this.model.each(function(user) {
						var userView = new UserView( { model: user } );
						that.$el.find("table").append(userView.render().$el);
					});
				},
				
				events: {
					'click .btn.glyphicon-pencil': 'showEditForm',
					'click .editFormConfirm': 'editConfirm',
					'click .btn.glyphicon-remove': 'showRemoveConfirmation',
					'click .confirm': 'confirm',
					'click .btn.glyphicon-envelope': 'showSendingNottification',
					'submit #submitForm': 'submitSendingNottification'
				},


				submitSendingNottification: function(e){
					 var that = this;
            		 e.preventDefault();

		             this.SNmodel = this.getModel();
		             this.SNmodel.save( {}, {
		                 success: function(SNmodel, response) {
                             if($('#notificationModal')) $('#notificationModal').remove();
		                     $(".signUp.modal").modal("hide");
		                     that.$el.append(that.notificationTemplate( { 'data': response } ));
							 $('#notificationModal').modal();
		                 },
		                 error: function() {
                             if($('#notificationModal')) {
								 $('#notificationModal').remove();
		                     }
		                     that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							 $('#notificationModal').modal();
		                 }
		             } );
		             return false;
				},

				getRoleById : function(roleId){
					var role = "";
					switch (roleId){
						case USER_NOT_CONFIRMED :
							role = "User not confirmed";
							break;
						case USER :
							role = "User";
							break;
						case MANAGER :
							role = "Manager";
							break;
						case ADMIN :
							role = "Admin";
							break;
						case SUBSCRIBER :
							role = "Subscriber";
							break;
						case DELETED :
							role = "Deleted";
							break;
					}
					return role;
				},

				getModel: function(){
					var model = this.model.get($('#NottificationModal').data('userId'));
					var email = model.get('email');
					l(email);
					l($.extend({email: email}, $("#submitForm").serializeJSON()));
					return new SendingNotificationModel($.extend({email: email}, $("#submitForm").serializeJSON()));
				},
				
				showEditForm: function(e) {
                    if($('#editModal')) $('#editModal').remove();
					this.$el.append(this.editUserTemplate( { 'data': this.model.get(e.currentTarget.id) } ));
					$('#editModal').modal();
					//set user role on edit form
					$("#userRole").val( this.model.get(e.currentTarget.id).attributes.role_id);

					userName = $('#userName');
					userEmail = $('#userEmail');
					userLogin = $('#userLogin');
					
					userName.on('blur', function() {
						if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
							this.value = 'Wrong name!';
							this.style.color = 'red';
						}
					});
					
					userName.on('focus', function() {
						if (this.value == 'Wrong name!') this.value ='';
						this.style.color = 'black';
					});
					
					userEmail.on('blur', function() {
						if (!/^[a-z0-9_.]+\@[a-z0-9]+\.[a-z0-9]+$/.test(this.value)) {
							this.value = 'Wrong email!';
							this.style.color = 'red';
						}
					});
					
					userEmail.on('focus', function() {
						if (this.value == 'Wrong email!') this.value ='';
						this.style.color = 'black';
					});
					
					userLogin.on('blur', function() {
						if (!/^[A-Za-z0-9_.-]+$/.test(this.value)) {
							this.value = 'Wrong login!';
							this.style.color = 'red';
						}
					});
					
					userLogin.on('focus', function() {
						if (this.value == 'Wrong login!') this.value ='';
						this.style.color = 'black';
					});
				},
				
				editConfirm: function(e) {
					console.log();
					var isValid = true;
					console.log('UserListView: editConfirm');
					if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(userName.val())) {
						userName.val('Wrong name!').css('color', 'red');
						isValid = false;
					}
					
					if(isValid) {
                        if($('#confirmationModal')) $('#confirmationModal').remove();
						this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to edit this user?' }, { 'id': e.currentTarget.id }, { 'action': 'edit user' } ] } ));
						$('#confirmationModal').modal();
					}
				},
				
				showRemoveConfirmation: function(e) {
				if($('#confirmationModal')) $('#confirmationModal').remove();
				this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to delete this user?' }, { 'id': e.currentTarget.id }, { 'action': 'delete user' } ] } ));
				$('#confirmationModal').modal();
				},

				showSendingNottification: function(e) {
					$('#NottificationModal').remove();
					this.$el.append(this.SendingNottificationTemplate( { 
						'data': [] 
					}));

					$('#NottificationModal').data('userId', e.currentTarget.id);
					$('#NottificationModal').modal();
				},

				confirm: function(e) {
					$('#confirmationModal').modal('hide');
					$('#editModal').modal('hide');
					if(e.currentTarget.name == 'delete user') {
						this.model.get(e.currentTarget.id).destroy( {
							success: function(model, response) {
								if($('#notificationModal')) $('#notificationModal').remove();
								that.$el.append(that.notificationTemplate( { 'data': response } ));
								$('#notificationModal').modal();
								//check if user want to delete himself
								$.ajax({ contentType:'application/json',
									url: 'users/current',
									success: function(data){
										loginView.currentUser = new UserModel(data);
										adminView.render();
										if(_.isEqual(loginView.currentUser.get("roleId"), DELETED)){
											router.navigate("#logout", {trigger: true});
										}
									}
								});

							},
							error: function() {
								if($('#notificationModal')) $('#notificationModal').remove();
								that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
								$('#notificationModal').modal();
							}
						} );
					}
					else if(e.currentTarget.name == 'edit user') {
						this.model.get(e.currentTarget.id).set( {
							name: $('#userName').val(),
							email: $('#userEmail').val(),
							login: $('#userLogin').val(),
							roleId: $('#userRole').val(),
							role: this.getRoleById($('#userRole').val())
						} ).save( {}, {
							success: function(model, response) {
								if($('#notificationModal')) $('#notificationModal').remove();
								that.$el.append(that.notificationTemplate( { 'data': response } ));
								$('#notificationModal').modal();
								adminView.render();
							},
							error: function() {
								if($('#notificationModal')) $('#notificationModal').remove();
								that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
								$('#notificationModal').modal();
							}
						} );
					}
                    else if(e.currentTarget.name == 'profile edit user') {
                        loginView.currentUser.set( {
                            name: $('#user-edit-name').val()
                        } ).save( {}, {
                                success: function(model, response) {
                                    if($('#notificationModal')) $('#notificationModal').remove();
                                    that.$el.append(that.notificationTemplate( { 'data': response } ));
                                    $('#notificationModal').modal();
                                    viewUserProfile.render();
                                },
                                error: function() {
                                    if($('#notificationModal')) $('#notificationModal').remove();
                                    that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Couldn\'t change name!' } } ));
                                    $('#notificationModal').modal();
                                }
                            } );
                    }

					else if(e.currentTarget.name == 'delete issue') {
						$.ajax({
							url: '/issue/delete/' + e.currentTarget.id,
							type: 'POST',
							success: function(){
									managerView.resetFilter();
							}
						});
					}
					else if(e.currentTarget.name == 'edit issue') {
						console.log ("--- UserListView.js confirm if {name equal 'edit issue'}");
						$('#editIssueModal').modal('hide');
						mapView.model.get(e.currentTarget.id).set( {
						name: $('#edit-issue-form-name').val(),
						description: $('#edit-issue-form-description').val(),
						attachments: $('#edit-issue-form-attachments').val(),
						category: $('#edit-issue-form-category').val(),
						status: $('#edit-issue-form-status').val(),
						priorityId: $('#edit-issue-form-priority').val()
						
						} ).save( {}, {
							success: function (model, response) {
								if ($('#notificationModal')) $('#notificationModal').remove();
								that.$el.append(that.notificationTemplate({'data': response}));
								$('#notificationModal').modal();
								managerView.resetFilter();
							},
							error: function () {
								if ($('#notificationModal')) $('#notificationModal').remove();
								that.$el.append(that.notificationTemplate({'data': {'message': 'Error!'}}));
								$('#notificationModal').modal();
							}
						})
					}
					else if (e.currentTarget.name == 'edit category') {
						this.editCategory(e);
					}
					else if (e.currentTarget.name == 'delete category') {
						this.deleteCategory(e);
					}
					else if (e.currentTarget.name == 'add category') {
						this.addNewCategory(e);
					}
				},

				editCategory: function(e){
					var category = categoryManageView.categories.get(e.currentTarget.id);
					category.set({
						name: $('#edit-category-name').val()
					}).save( {}, {
						success: function(model, response) {
							if($('#notificationModal')) $('#notificationModal').remove();
							if($('#confirmationModal')) $('#confirmationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': response } ));
							$('#notificationModal').modal();
							categoryManageView.render();
						},
						error: function() {
							if($('#notificationModal')) $('#notificationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							$('#notificationModal').modal();
						}
					} );
					if($('#editCategoryModal')) $('#editCategoryModal').remove();
				},

				deleteCategory: function(e){
					var category = categoryManageView.categories.get(e.currentTarget.id);
					category.destroy({
						success: function(model, response) {
							if($('#notificationModal')) $('#notificationModal').remove();
							if($('#confirmationModal')) $('#confirmationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': response } ));
							$('#notificationModal').modal();
							categoryManageView.render();
						},
						error: function() {
							if($('#notificationModal')) $('#notificationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							$('#notificationModal').modal();
						}
					} );
				},

				addNewCategory: function(e){
					var newCategory = new CategoryModel();
					newCategory.set({
						name: $("#add-category-name").val()
					}).save( {}, {
						success: function(model, response) {
							if($('#notificationModal')) $('#notificationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': response } ));
							$('#notificationModal').modal();
							categoryManageView.render();
						},
						error: function() {
							if($('#notificationModal')) $('#notificationModal').remove();
							$("#container").append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							$('#notificationModal').modal();
						}
					} );
					if($('#addCategoryModal')) $('#addCategoryModal').remove();
				}
			});	

		});