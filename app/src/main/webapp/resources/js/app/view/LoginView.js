define([ 'jquery', 'underscore', 'backbone', 'text!templates/login.html', ],
		function($, _, Backbone, LoginTemplate) {
			var LoginView = Backbone.View.extend({
				
				loginTemplate: _.template(LoginTemplate),
				
				events: {
					//'click li #login': 'loginForm',
				},
				
				initialize: function() {
					
				},
				
				render: function(){

				
					$('.login.modal').append(this.loginTemplate);
					$(".login.modal").show()
				},
			});
			
			return LoginView;
		});
