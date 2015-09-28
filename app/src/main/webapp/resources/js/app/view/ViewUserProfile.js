define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/ViewUserProfile.html', 'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone,  UserModel, ViewUserProfileTemplate, NotificationTemplate) {
			
			var ViewUserProfile = Backbone.View.extend({
				
				events: {
					'click #profile' : 'editProfile',
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
				}

			
			});
			
			return ViewUserProfile;
		})
