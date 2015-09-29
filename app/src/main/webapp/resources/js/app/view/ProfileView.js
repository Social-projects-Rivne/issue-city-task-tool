define([ 'jquery', 'underscore', 'backbone', 'model/UserModel', 'text!templates/UserProfile.html', 'text!templates/NotificationTemplate.html' ],
		function($, _, Backbone,  UserModel, UserProfileTemplate, NotificationTemplate) {
			
			var ProfileView = Backbone.View.extend({
				
				events: {

				},
				
				userProfileTemplate: _.template(UserProfileTemplate),
				

				initialize: function() {
				
				},
				
				render: function(id) {
					this.model = loginView.currentUser;
					this.$el.html(this.userProfileTemplate(this.model.toJSON()));
				},
			
			});
			
			return ProfileView;
		})
