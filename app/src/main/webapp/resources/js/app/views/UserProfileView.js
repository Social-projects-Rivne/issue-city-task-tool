define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/ViewUserProfile.html', 'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone,  UserModel, ViewUserProfileTemplate, NotificationTemplate) {
			
			return Backbone.View.extend({
				
				events: {
					'click #profile' : 'editProfile',
					'click #admin_log_out'	: 'logOut',
				},
				
				viewUserProfileTemplate: _.template(ViewUserProfileTemplate),
				

				initialize: function() {
				
				},
				
				render: function(id) {
					this.model = loginView.currentUser;
					this.$el.html(this.viewUserProfileTemplate(this.model.toJSON()));
					console.log(this.model);
				},

				editProfile: function() {
					router.navigate('#profile', {trigger: true});
				},
				logOut: function(){
					$.ajax('auth/logout');
					loginView.currentUser = null;
					router.navigate('', {trigger:true});
					if($('#notificationModal'))
						$('#notificationModal').remove();
					this.$el.append(that.notificationTemplate( { 'data': { 'message': "You have been successfully logged out!" }} ));
					$('#notificationModal').modal();
					loginView.buttonsManage();
				}



			});
})
