define([ 'jquery', 'underscore', 'backbone', 'collection/IssueCollection', 'text!templates/Manager.html', 'text!templates/issue_table.html', 'text!templates/Manager_search.html', 'collection/CategoryCollection', 'model/IssueModel' ],
		function($, _, Backbone, IssueCollection, ManagerTemplate, IssueTableTemplate, ManagerSearchTemplate, CategoryCollection, IssueModel) {
			var ManagerView = Backbone.View.extend({
				
				events: {
					'click #issue-filter  #filter-issue': 'issueFilter',
					'click #issue-filter  #reset-filter-issue': 'resetFilter',
					'change .category': 'quickChangeCategory',
				},
				
				managerTemplate: _.template(ManagerTemplate),
				issueTableTemplate: _.template(IssueTableTemplate),
				searchTemplate: _.template(ManagerSearchTemplate),
				
				issues: null,
				issuesFilterList: null,
				categories: null,
				issue: null,
				
				initialize: function() {
					this.issues = mapView.model;
					this.issuesFilterList = new IssueCollection(this.issues);
					this.categories = new CategoryCollection();
					this.categories.fetch();
					this.issue = new IssueModel();
				},
				
				// issue table on manager page
				issueTableRender: function() {
					this.$("#issue-table-body").empty();
					that = this;
					this.issues.each( function(issue){
						that.$("#issue-table-body").append(that.$("#issue-table-body").
								append(that.issueTableTemplate({data: [ {issue: issue.toJSON()}, {categories: that.categories.toJSON()} ] }))
						);
					});
				},

				// render template for manager search
				searchRender: function(){
 					console.log('search rendered');
					this.$('#issue-filter').append(this.searchTemplate); 
				},
				
				// render all components of manager page 
				render: function() {
					this.$el.html(this.managerTemplate);
					this.issueTableRender();
					this.searchRender();
				},
				
				// filter (search)
				issueFilter: function(){
					//checking filters
					console.log("Name is " + $('#issue-filter #name').prop("checked"));
					console.log("Keyword is " + $('#issue-filter #keyword').prop("checked"));
					console.log("Status is " + $('#issue-filter #status').prop("checked") + ' id = ' + $('#issue-filter #status-filter').val());
					console.log("Category is " + $('#issue-filter #category').prop("checked"));
					console.log("Priority is " + $('#issue-filter #priority').prop("checked") + ' id = ' + $('#issue-filter #priority-filter').val());
					
					//filter by status (it work when raido btn Status checked)
					if ($('#issue-filter #status').prop("checked")) {
						var issuesFilterList = new IssueCollection();
						this.issues.each(function(issue){
							if(issue.get('statusId') == $('#issue-filter #status-filter').val()){ 
								console.log(issue);
								issuesFilterList.add(issue);
							}
						});
						//issuesFilterList = this.issues.findWhere({statusId: '0'});
						console.log(issuesFilterList);
						this.issues = issuesFilterList;
						console.log(this.issues);
						this.issueTableRender();
					};
					
					//search by name (it work when raido btn Name checked)
					if( $('#issue-filter #name').prop("checked")) {
						console.log("where Name is = " + $('#issue-filter #text-value-issue-filter').val());
						var issuesFilterList = new IssueCollection();
						this.issues.each(function(issue){
							if(issue.get('name') == $('#issue-filter #text-value-issue-filter').val()){ 
								console.log(issue);
								issuesFilterList.add(issue);
							}
						});		
						console.log(issuesFilterList);
						this.issues = issuesFilterList;
						console.log(this.issues);
						this.issueTableRender();			
					};

					//search by keyword (it work when raido btn Keyword checked)
					if( $('#issue-filter #keyword').prop("checked")) {
						console.log("where Keyword is = " + $('#issue-filter #text-value-issue-filter').val());
						var issuesFilterList = new IssueCollection();
						this.issues.each(function(issue){
							if((issue.get('name').match($('#issue-filter #text-value-issue-filter').val()) != null) ||
							 (issue.get('description').match($('#issue-filter #text-value-issue-filter').val()) != null)){ 
								console.log(issue);
								issuesFilterList.add(issue);
							}
						});		
						console.log(issuesFilterList);
						this.issues = issuesFilterList;
						console.log(this.issues);
						this.issueTableRender();			
					};

					//filter by priority (it work when raido btn Priority checked)
					if ($('#issue-filter #priority').prop("checked")) {
						var issuesFilterList = new IssueCollection();
						this.issues.each(function(issue){
							if(issue.get('priorityId') == $('#issue-filter #priority-filter').val()){ 
								console.log(issue);
								issuesFilterList.add(issue);
							}
						});
						console.log(issuesFilterList);
						this.issues = issuesFilterList;
						console.log(this.issues);
						this.issueTableRender();
					};
				},
				
				//reset filter
				resetFilter: function(){
					$('#issue-filter #keyword').prop("checked", "checked");
					this.issues = mapView.model;
					this.issueTableRender();
				},
				
				quickChangeCategory: function(e) {
					this.issue.set( {
						id: e.currentTarget.id,
						category: e.currentTarget.value,
					} );
					this.issue.save();
				}
					
			});
			
			return ManagerView;
		})
