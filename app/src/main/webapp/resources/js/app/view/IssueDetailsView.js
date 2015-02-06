define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel', 'text!templates/IssueDetails.html' ],
		function($, _, Backbone, IssueModel, IssueDetailsTemplate) {
			var IssueDetaisView = Backbone.View.extend({
				template: _.template(IssueDetailsTemplate),
				
				initialize : function() {
					this.model = new IssueModel();
				},
				
				render : function(id) {
					this.model.set("id", id);
					this.model.fetch();
					
					that = this;
					
					setTimeout(issueDetailsRender, 1000);
					
					return this;
				}
			});	
			
			function issueDetailsRender() {
				that.$el.html(that.template(that.model.toJSON()));
			}
			
			return IssueDetaisView;
		})
