define([ 'jquery', 'underscore', 'backbone', 'text!templates/login.html', ],
		function($, _, Backbone, LoginTemplate) {
			var LoginView = Backbone.View.extend({
				
				loginTemplate: _.template(LoginTemplate),
				
				events: {
					
				},
				
				initialize: function() {
					
				},
				
				render: function(){
					$('body').append(this.loginTemplate);
				},
			});
			
			return LoginView;
		});
