define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel', 'text!templates/AddIssue.html' ],
		function($, _, Backbone, IssueModel, AddIssueTemplate) {
			var AddIssueView = Backbone.View.extend({
				template: _.template(AddIssueTemplate),
				
				initialize: function() {
					this.model = new IssueModel();
				},
				
				events: {
					'click': 'addIssue'
				},
				
				render: function() {
					this.$el.html(this.template);
					
					return this;
				}
			});
			
			function addIssue() {
				
			}
			
			return AddIssueView;
		})
