define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel',
         'text!templates/IssueDetails.html' ],
		function($, _, Backbone, IssueModel) {
			var IssueDetaisView = Backbone.View.extend({
				initialize : function() {
					this.model = new IssueModel();
				},
				
				render : function(id) {
					this.model.set("id", id);
					this.model.fetch();
					this.$el.html(this.model.toJSON());
					
					return this;
				}
			});	
			
			return IssueDetaisView;
		})
