define([ 'jquery', 'underscore', 'backbone','text!templates/Manager.html', ],
		function($, _, Backbone, ManagerTemplate) {
			var ManagerView = Backbone.View.extend({
				
				events: {
					
				},
				
				template: _.template(ManagerTemplate),
				
				initialize: function() {
					
				},
				
				render: function() {
					this.$el.html(this.template);
					console.log(this.template);
				},
					
			});
			
			return ManagerView;
		})
