define([ 'jquery', 'underscore', 'backbone', 'collection/IssueCollection', 'text!templates/Manager.html', 'text!templates/issue_table.html', 'text!templates/Manager_search.html', ],
		function($, _, Backbone, IssueCollection, ManagerTemplate, IssueTableTemplate, ManagerSearchTemplate) {
			var ManagerView = Backbone.View.extend({
				
				events: {
					
				},
				
				managerTemplate: _.template(ManagerTemplate),
				issueTableTemplate: _.template(IssueTableTemplate),
				searchTemplate: _.template(ManagerSearchTemplate),
				issues: new IssueCollection(),
				
				initialize: function() {
					this.issues = mapView.model;
				},
				
				// issue table on manager page
				issueTableRender: function() {
					
					that = this;
					this.issues.each( function(issue){
						that.$("#issue-table-body").append(that.$("#issue-table-body").
								append(that.issueTableTemplate(issue.toJSON()))
						);
					});
				},

				// render template for manager search
				searchRender: function(){
 					console.log('search rendered');
					this.$('#issue-filter').append(this.searchTemplate); 
				},
				
				render: function() {
					this.$el.html(this.managerTemplate);
					this.issueTableRender();
					this.searchRender();
				},

				
					
			});
			
			return ManagerView;
		})
