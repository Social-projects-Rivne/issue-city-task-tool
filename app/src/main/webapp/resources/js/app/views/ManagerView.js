define([ 'jquery', 'bootstrap', 'underscore', 'backbone', 'collection/IssueCollection', 'text!templates/Manager.html', 'text!templates/issue_table.html', 'text!templates/Manager_search.html', 'collection/CategoryCollection', 'model/IssueModel', 'collection/StatusCollection', 'text!templates/NotificationTemplate.html', 'model/CategoryModel', 'text!templates/ConfirmationTemplate.html', 'text!templates/EditIssueTemplate.html' ],
		function($, bootstrap, _, Backbone, IssueCollection, ManagerTemplate, IssueTableTemplate, ManagerSearchTemplate, CategoryCollection, IssueModel, StatusCollection, NotificationTemplate, CategoryModel, ConfirmationTemplate, EditIssueTemplate) {
			
			var that = null;

			var ManagerView = Backbone.View.extend({

				events: {
					'click #issue-filter  #filter-issue': 'issueFilter',
					'click #issue-filter  #reset-filter-issue': 'resetFilter',
					'change .category': 'quickChangeCategory',
					'change .status': 'quickChangeStatus',
					'click .glyphicon-thumbs-up' : "quickChangeStatusOnApproved",
					'click .glyphicon-thumbs-down' : "quickChangeStatusOnDisapproved",
					'click .issue-table .btn.delete-issue': 'showRemoveIssueConfirmation',
					'click .btn.view-on-map': 'viewOnMap',
					'mouseenter .issue-table > tbody > tr  ' : 'issueFocus',
					'mouseleave .issue-table > tbody > tr  ' : 'issueUnFocus',
					'click .edit-issue'	: 'showEditIssueForm',
					'click .editIssueConfirm' : 'editIssue',
					'click #manager_log_out':'AppController.logout',
					'click #all_issues': 'allIssues',
					'click #newest_issues': 'newestIssues',
					'click #resolved_issues': 'resolvedIssues'
				},

				managerTemplate: _.template(ManagerTemplate),
				issueTableTemplate: _.template(IssueTableTemplate),
				searchTemplate: _.template(ManagerSearchTemplate),
				notificationTemplate: _.template(NotificationTemplate),
				confirmationTemplate: _.template(ConfirmationTemplate),
				editIssueTemplate: _.template(EditIssueTemplate),

				categoriesCollection: null,
				issues: null,
				issuesFilterList: null,
				categories: null,
				statuses: null,
				issue: null,

				initialize: function() {
					that = this;
					this.issues = mapView.model;
					this.issuesFilterList = new IssueCollection(this.issues);
					this.categories = new CategoryCollection();
					this.categories.fetch( { success: function() {
						that.categories = new CategoryCollection(that.categories.where({state: CATEGORY_NEW}));
					}})
					this.statuses = new StatusCollection();
					this.statuses.fetch();
					this.issue = new IssueModel();
					this.categoriesCollection = issueFilterView.categoryCollection;
				},

				// issue table on manager page
				issueTableRender: function() {
					this.$("#issue-table-body").empty();
					that = this;

					this.issues.each( function(issue){
						that.$("#issue-table-body").append(that.$("#issue-table-body").
								append(that.issueTableTemplate({data: [ {issue: issue.toJSON()}, {categories: that.categories.toJSON()}, {statuses: that.statuses.toJSON()} ] }))
						);
					});
					this.$("#issue-table-body").find('select').hide();
				},

				// render template for manager search
				searchRender: function(){
 					this.categoriesCollection.fetch({success: function(){
						that.categoriesCollection = new CategoryCollection(that.categoriesCollection.where( {state : CATEGORY_NEW}));
						that.$('#issue-filter').append(that.searchTemplate({"categories":that.categoriesCollection.toJSON()}));
						}
					});
				},

				// render all components of manager page
				render: function() {
					that = this;
					this.categories.fetch( { success: function() {
						that.categories = new CategoryCollection(that.categories.where({state: CATEGORY_NEW}));
					}})
					setTimeout(function(){
						that.$el.html(that.managerTemplate);
						that.issueTableRender();
						that.resetFilter();
						that.searchRender();
						$('#add-category-link').popover();
					}, 200)

				},

				//for issue table style
				issueFocus: function(e){
					var categoryCell = $(e.currentTarget).find('#category-cell');
					var statusCell = $(e.currentTarget).find('#status-cell');

					//hide div with category name
					$($(categoryCell).find('#name')).hide();
					//hide div with category status
					$($(statusCell).find('#name')).hide();

					//show buttons
					$(e.currentTarget.getElementsByClassName('btn-toolbar')[0]).fadeIn(50);

					//show all dropdun lists in row
					$(e.currentTarget).find('select').show();
					e.currentTarget.style.setProperty('background',"white");
					//e.currentTarget.style.setProperty("font-weight","bold");
				},

				//for issue table style
				issueUnFocus: function(e){

					var categoryCell = $(e.currentTarget).find('#category-cell');
					var statusCell = $(e.currentTarget).find('#status-cell');

					//hide buttons
					$(e.currentTarget.getElementsByClassName('btn-toolbar')[0]).fadeOut(0);

					//change category cell
					$($(categoryCell).find('#name')).empty();
					$($(categoryCell).find('#name')).append($(categoryCell).find('select').val());
					$($(categoryCell).find('#name')).show();

					//change status cell
					$($(statusCell).find('#name')).empty();
					$($(statusCell).find('#name')).append($(statusCell).find('select').val());
					$($(statusCell).find('#name')).show();

					//hide all dropdun lists in row
					$(e.currentTarget).find('select').hide();

					//change style of row
					e.currentTarget.style.setProperty('background',"");
					//e.currentTarget.style.setProperty("font-weight","");
				},

				showRemoveIssueConfirmation: function(e){
					if($('#confirmationModal')) $('#confirmationModal').remove();
					this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to delete this issue?' }, { 'id': e.currentTarget.id }, { 'action': 'delete issue' } ] } ));
					$('#confirmationModal').modal();
					return e;
				},

				viewOnMap: function(e){
					router.navigate('', {trigger: true});
					router.navigate('issue/' +  e.currentTarget.id, {trigger: true});
				},

				// filter (search)
				issueFilter: function(){
					this.issues = mapView.model;
					//checking filters
					//filter by status (it work when raido btn Status checked)
					if ($('#issue-filter #status').prop("checked")) {//!!!
						var issuesFilterList = new IssueCollection();
						this.issues.each(function(issue){
							if(issue.get('status') == $('#issue-filter #status-filter').val()){
								issuesFilterList.add(issue);
							}
						});
						this.issues = issuesFilterList;
						this.issueTableRender();
					};

					//search by name (it work when raido btn Name checked)
					if( $('#issue-filter #name').prop("checked")) {
						var issuesFilterList = new IssueCollection();
						this.issues.each(function(issue){
							if(issue.get('name') == $('#issue-filter #text-value-issue-filter').val()){
								issuesFilterList.add(issue);
							}
						});
						this.issues = issuesFilterList;
						this.issueTableRender();
					};

					//search by keyword (it work when raido btn Keyword checked)
					if( $('#issue-filter #keyword').prop("checked")) {
						var issuesFilterList = new IssueCollection();
						this.issues.each(function(issue){
							if((issue.get('name').match($('#issue-filter #text-value-issue-filter').val()) != null) ||
							 (issue.get('description').match($('#issue-filter #text-value-issue-filter').val()) != null)){
								issuesFilterList.add(issue);
							}
						});
						this.issues = issuesFilterList;
						this.issueTableRender();
					};

					//filter by category (it work when raido btn Cetegory checked)
					if ($('#issue-filter #category').prop("checked")) {
						var issuesFilterList = new IssueCollection();
						// it must be filtred with findWhere
						this.issues.each(function(issue){
							if(issue.get('categoryId') == $('#issue-filter #categories').val()){
								issuesFilterList.add(issue);
							}
						});
						this.issues = issuesFilterList;
						this.issueTableRender();
					};

					//filter by priority (it work when raido btn Priority checked)
					if ($('#issue-filter #priority').prop("checked")) {
						var issuesFilterList = new IssueCollection();
						this.issues.each(function(issue){
							if(issue.get('priorityId') == $('#issue-filter #priority-filter').val()){
								issuesFilterList.add(issue);
							}
						});
						this.issues = issuesFilterList;
						this.issueTableRender();
					};
				},

				//reset filter
				resetFilter: function(){
					var that = this;
					this.issues.fetch({ success: function(){
							that.issueTableRender();
						}
					});
				},

				quickChangeCategory: function(e) {
					this.issue = this.issues.get( e.currentTarget.id);
					this.issue.set( {
						category: e.currentTarget.value,
						categoryId :e.currentTarget.id});
					this.issue.save();
				},

				quickChangeStatus: function(e) {
					this.issue = this.issues.get( e.currentTarget.id);
					this.issue.set( {status: e.currentTarget.value});
					this.issue.save();
				},

				quickChangeStatusOnApproved: function(e) {
					var currentStatus = $("#issue-table-body #" + e.currentTarget.id + ".status").val();
					this.issue = this.issues.get( e.currentTarget.id);
					switch (currentStatus){
						case "NEW":
							this.issue.set( {status: "APPROVED"});
							break;
						case "TO_RESOLVE":
							this.issue.set( {status: "RESOLVED"});
							break;
					}
					this.issue.save();
					this.render();
				},

				quickChangeStatusOnDisapproved: function(e) {
					var currentStatus = $("#issue-table-body #" + e.currentTarget.id + ".status").val();
					this.issue = this.issues.get( e.currentTarget.id);
					switch (currentStatus){
						case "NEW":
							this.issue.set( {status: "DELETED"});
							break;
						case "TO_RESOLVE":
							this.issue.set( {status: "APPROVED"});
							break;
					}
					this.issue.save();
					this.render();
				},

				allIssues: function (e) {
					this.issues = mapView.model;
					this.issues.fetch({success: function(){
						that.issueTableRender();
					}});
				},

				newestIssues: function (e) {
					this.issues = mapView.model;
					this.issues.fetch({success: function(){
						var issuesFilterList = new IssueCollection();
						that.issues.each(function (issue) {
							if (issue.get('status') == "NEW") {
								issuesFilterList.add(issue);
							}
						});
						that.issues = issuesFilterList;
						that.issueTableRender();
					}});
				},

				resolvedIssues: function (e) {
					this.issuesResolvedList = new IssueCollection();
					this.issuesResolvedList.url = "issue/resolved";
					this.issuesResolvedList.fetch({success: function(){
						that.issues = that.issuesResolvedList;
						that.issueTableRender();
					}});
				},

				showEditIssueForm: function(e){
					// remove existing modal
					if($('#editIssueModal')) $('#editIssueModal').remove();

					//get issue from collection by ID for load fields in template
					var issue= this.issues.get(e.currentTarget.id);
					this.$el.append(this.editIssueTemplate({data: [ {issue: issue.toJSON()}, {categories: that.categories.toJSON()}, {statuses: that.statuses.toJSON()} ] }));

					$('#editIssueModal').modal();
					// assign jQuery selectors for variables for will use for validation below
					issueDescription = $('#edit-issue-form-description');
					issueAttachment = $('#edit-issue-form-attachments');
					issueCategory =	$('#edit-issue-form-category');
					issueStatus = $('#edit-issue-form-status');
					issuePriority =	$('#edit-issue-form-priority');

					// RegExp validate for fields
					issueDescription.on('blur', function() {
						if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
							this.value = 'Wrong name!';
							this.style.color = 'red';
							}
						});
					issueDescription.on('focus', function() {
						if (this.value == 'Wrong name!') this.value ='';
						this.style.color = 'black';
					});
					issueAttachment.on('blur', function() {
						//
					});
					issueAttachment.on('focus', function() {
						if (this.value == 'Wrong name!') this.value ='';
						this.style.color = 'black';
					});
					issueCategory.on('blur', function() {
						//
					});
					issueCategory.on('focus', function() {
						if (this.value == 'Wrong name!') this.value ='';
						this.style.color = 'black';
					});
					issueStatus.on('blur', function() {
						//
					});
					issueStatus.on('focus', function() {
						if (this.value == 'Wrong name!') this.value ='';
						this.style.color = 'black';
					});
					/*issuePriority.on('blur', function() {
						//
						if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
							this.value = 'Wrong name!';
							this.style.color = 'red';
							}
						});*/
					issuePriority.on('focus', function() {
						if (this.value == 'Wrong name!') this.value ='';
						this.style.color = 'black';
					});

				},

				editIssue: function(e) {
					var isValid = true;
					if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issueDescription.val())) {
						issueDescription.val('Wrong value!').css('color', 'red');
						isValid = false;
					}
					/*if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issueAttachment.val())) {
						issueAttachment.val('Wrong value!').css('color', 'red');
						isValid = false;
					}if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issueCategory.val())) {
						issueCategory.val('Wrong value!').css('color', 'red');
						isValid = false;*/
					/*if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issueStatus.val())) {
						issueStatus.val('Wrong value!').css('color', 'red');
						isValid = false;
					}if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issuePriority.val())) {
						issuePriority.val('Wrong value!').css('color', 'red');
						isValid = false;
					}*/

					if(isValid) {
						//call confirmation for edit issue
						if($('#confirmationModal')) $('#confirmationModal').remove();
						this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to edit this issue?' }, { 'id': e.currentTarget.id }, { 'action': 'edit issue' } ] } ));
						$('#confirmationModal').modal();
					}
				}

			});
			
			return ManagerView;
		})