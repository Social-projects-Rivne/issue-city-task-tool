define([ 'jquery', 'underscore', 'backbone', 'collection/IssueCollection', 'text!templates/Manager.html', 'text!templates/issue_table.html', ],
		function($, _, Backbone, IssueCollection, ManagerTemplate, IssueTableTemplate) {
			var ManagerView = Backbone.View.extend({
				
				events: {
					
				},
				
				managerTemplate: _.template(ManagerTemplate),
				issueTableTemplate: _.template(IssueTableTemplate),
				
				issues: new IssueCollection(),
				
				initialize: function() {
					this.issues.fetch();
					console.log(this.issues)
				},
				
				issueTableRender: function() {
					
					that = this;
					this.issues.each( function(issue){
						that.$("#issue-table-body").append(that.$("#issue-table-body").append(that.issueTableTemplate(issue.toJSON()))
						);
						console.log(issue.toJSON());
					});
				},
				
				render: function() {
					this.$el.html(this.managerTemplate);
					this.issueTableRender();
					
				},

				
					
			});
			
			return ManagerView;
		})
