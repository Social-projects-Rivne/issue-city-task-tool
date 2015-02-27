define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel', 'text!templates/issue_filter.html', ],
		function($, _, Backbone, IssueModel, IssueFilterTemplate) {
			var IssueFilterView = Backbone.View.extend({
				
				template: IssueFilterTemplate,
				
				initialize: function() {
					
				},
				
				render: function(id) {
					this.$el.html(that.template);
				},
				
				events: {

				},			
			
			return IssueDetaisView;
		})
