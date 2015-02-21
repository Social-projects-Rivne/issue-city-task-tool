define([ 'jquery', 'bootstrap', 'underscore', 'backbone', 'collection/IssueCollection', 'text!templates/Manager.html', 'text!templates/issue_table.html', 'text!templates/Manager_search.html', 'collection/CategoryCollection', 'model/IssueModel', 'collection/StatusCollection', 'text!templates/NotificationTemplate.html', 'model/CategoryModel', 'text!templates/ConfirmationTemplate.html', 'text!templates/EditIssueTemplate.html' ],
		function($, bootstrap, _, Backbone, IssueCollection, ManagerTemplate, IssueTableTemplate, ManagerSearchTemplate, CategoryCollection, IssueModel, StatusCollection, NotificationTemplate, CategoryModel, ConfirmationTemplate, EditIssueTemplate) {
			
			var that = null;
	
			var ManagerView = Backbone.View.extend({
				
				events: {
					'click #issue-filter  #filter-issue'		: 'issueFilter',
					'click #issue-filter  #reset-filter-issue'	: 'resetFilter',
					'change .category'							: 'quickChangeCategory',
					'change .status'							: 'quickChangeStatus',
					'click .table .btn.delete-issue'			: 'showRemoveConfirmation',
					'click .confirm'							: 'confirm',
					'click #add-category-link'					: 'showAddCategoryForm',
					'click #add-category'						: 'addCategory',
					'click .btn.view-on-map'					: 'viewOnMap',
					'click .edit-issue'							: 'showEditIssueForm',
					'click .editFormConfirm'					: 'editIssue',
				},
				
				managerTemplate			: _.template(ManagerTemplate),
				issueTableTemplate		: _.template(IssueTableTemplate),
				searchTemplate			: _.template(ManagerSearchTemplate),
				notificationTemplate	: _.template(NotificationTemplate),
				confirmationTemplate	: _.template(ConfirmationTemplate),
				editIssueTemplate		: _.template(EditIssueTemplate),
				
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
					this.categories.fetch();
					this.statuses = new StatusCollection();
					this.statuses.fetch();
					this.issue = new IssueModel();
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
					this.resetFilter();
					this.searchRender();
					$('#add-category-link').popover();
					this.
				},
				
				showRemoveConfirmation: function(e){
					if($('#confirmationModal')) $('#confirmationModal').remove();
					this.$el.append(this.confirmationTemplate( { 'data': [ { 'message': 'Do you really want to delete this issue?' }, { 'id': e.currentTarget.id }, { 'action': 'delete issue' } ] } ));
					$('#confirmationModal').modal();
				},

				viewOnMap: function(e){
					router.navigate('', {trigger: true});
					router.navigate('issues/' +  e.currentTarget.id, {trigger: true});
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
					var that = this;
					$('#issue-filter #keyword').prop("checked", "checked");
					this.issues.fetch({ success: function(){
							that.issueTableRender();
						}
					});
				},
				
				quickChangeCategory: function(e) {
					this.issue.set( {
						id: e.currentTarget.id,
						category: e.currentTarget.value,
					} );
					this.issue.save();
				},
				
				quickChangeStatus: function(e) {
					this.issue.set( {
						id: e.currentTarget.id,
						status: e.currentTarget.value,
					} );
					this.issue.save();
				},
				
				addCategory: function() {
					var newCategory = new CategoryModel( { 'name': $('#category-name').val() } );
					newCategory.save( {}, { 
						success: function(model, response) {
							$('#add-category-link').popover('hide');
							that.$el.append(that.notificationTemplate( { 'data': response } ));
							$('#notificationModal').modal();
						},
						error: function() {
							$('#add-category-link').popover('hide');
							that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
							$('#notificationModal').modal();
						} 
					} );
				},
								
				showAddCategoryForm: function(e) {
					e.preventDefault();
				},
				
				showEditIssueForm: function(){
					// remove existing modal, call Template, call modal
					if($('#addModal')) $('#addModal').remove();
					this.$el.append(this.editIssueTemplate);
					$('#addModal').modal();
					// assign jQuery selectors for variables
					issueDescription	= 	$('#edit-issue-form-description');
					issueAttachment	 	= 	$('#edit-issue-form-attachment');
					issueCategory		=	$('#edit-issue-form-category');
					issueStatus			=	$('#edit-issue-form-status');
					issuePriority		=	$('#edit-issue-form-priority');
					// RegExp validate for fields
					issueDescription.on('blur', function() {
						if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
							this.value = 'Wrong name!';
							this.style.color = 'red';
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
					issuePriority.on('blur', function() {
						//
						if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
							this.value = 'Wrong name!';
							this.style.color = 'red';
					});
					issuePriority.on('focus', function() {
						if (this.value == 'Wrong name!') this.value ='';
						this.style.color = 'black';
					});
					
				},
				
				editIssue: function(){
					
				}
									
			});
			
			return ManagerView;
		})