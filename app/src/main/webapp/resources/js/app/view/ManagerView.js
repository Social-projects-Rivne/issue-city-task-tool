define([ 'jquery', 'underscore', 'backbone', 'text!templates/Manager.html', 'text!templates/issue_table.html', ],
		function($, _, Backbone, ManagerTemplate, IssueTableTemplate) {
			var ManagerView = Backbone.View.extend({
				
				events: {
					
				},
				
				managerTemplate: _.template(ManagerTemplate),
				issueTableTemplate: _.template(IssueTableTemplate),
				
				initialize: function() {
					
				},
				
				issueTableRender: function() {
					this.$("#issue-table-body").append();
				},
				
				render: function() {
					this.$el.html(this.managerTemplate);
					console.log("Manager page");
					this.issueTableRender();
					
				},

				
					
			});
			
			return ManagerView;
		})
