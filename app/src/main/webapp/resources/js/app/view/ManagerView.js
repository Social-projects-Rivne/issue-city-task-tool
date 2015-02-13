define([ 'jquery', 'underscore', 'backbone', 'collection/IssueCollection', 'text!templates/Manager.html', 'text!templates/issue_table.html', 'text!templates/Manager_search.html', ],
		function($, _, Backbone, IssueCollection, ManagerTemplate, IssueTableTemplate, ManagerSearchTemplate) {
			var ManagerView = Backbone.View.extend({
				
				events: {
					'click #issue-filter  #filter-issue': 'issueFilter',
					'click #issue-filter  #reset-filter-issue': 'resetFilter',
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
					this.$("#issue-table-body").empty();
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

				issueFilter: function(){
					console.log("Name is " + $('#issue-filter #name').prop("checked"));
					console.log("Keyword is " + $('#issue-filter #keyword').prop("checked"));
					console.log("Status is " + $('#issue-filter #status').prop("checked") + ' id = ' + $('#issue-filter #status-filter').val());
					console.log("Category is " + $('#issue-filter #category').prop("checked"));
				},

				resetFilter: function(){
					$('#issue-filter #keyword').prop("checked", "checked");
					this.issues = mapView.model;
					this.issueTableRender();
				}
					
			});
			
			return ManagerView;
		})
