define([ 'jquery', 'bootstrap', 'underscore', 'backbone', 'collection/IssueCollection', 'text!templates/Manager.html', 'text!templates/issue_table.html', 'text!templates/Manager_search.html', 'collection/CategoryCollection', 'model/IssueModel', 'collection/StatusCollection', 'text!templates/NotificationTemplate.html', 'model/CategoryModel', 'text!templates/ConfirmationTemplate.html', 'text!templates/EditIssueTemplate.html' ],
		function($, bootstrap, _, Backbone, IssueCollection, ManagerTemplate, IssueTableTemplate, ManagerSearchTemplate, CategoryCollection, IssueModel, StatusCollection, NotificationTemplate, CategoryModel, ConfirmationTemplate, EditIssueTemplate) {
			
			var that = null;
	
			var ManagerView = Backbone.View.extend({
				
				events: {
					'click #issue-filter  #filter-issue'			: 'issueFilter',
					'click #issue-filter  #reset-filter-issue'		: 'resetFilter',
					'change .category'								: 'quickChangeCategory',
					'change .status'								: 'quickChangeStatus',
					'click .table .btn.delete-issue'				: 'showRemoveConfirmation',
					'click .confirm'								: 'confirm',
					'click #add-category-link'						: 'showAddCategoryForm',
					'click #add-category'							: 'addCategory',
					'click .btn.view-on-map'						: 'viewOnMap',
					'mouseenter .issue-table > tbody > tr  ' 		: 'issueFocus',
					'mouseleave .issue-table > tbody > tr  '		: 'issueUnFocus',
					'click .edit-issue'								: 'showEditIssueForm',
					'click .editIssueConfirm' 						: 'editIssue',
					'click #left_admin_panel #manager_log_out'		: 'logOut'
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
					this.categories.fetch();
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
 					console.log('search rendered');
 					this.categoriesCollection.fetch({success: function(){
						that.$('#issue-filter').append(that.searchTemplate({"categories":that.categoriesCollection.toJSON()})); 
						}
					});
				},
				
				// render all components of manager page 
				render: function() {
					that = this;
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

				showRemoveConfirmation: function(e){
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

					//filter by category (it work when raido btn Cetegory checked)
					if ($('#issue-filter #category').prop("checked")) {
						var issuesFilterList = new IssueCollection();
						// it must be filtred with findWhere
						this.issues.each(function(issue){
							if(issue.get('categoryId') == $('#issue-filter #categories').val()){ 
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
						category: e.currentTarget.value
					} );
					this.issue.save();
				},
				
				quickChangeStatus: function(e) {
					this.issue.set( {
						id: e.currentTarget.id,
						status: e.currentTarget.value
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
				
				showEditIssueForm: function(e){
					// remove existing modal
					if($('#editIssueModal')) $('#editIssueModal').remove(); 
					
					//get issue from collection by ID for load fields in template
					issue=this.issues.get(e.currentTarget.id);//+
					console.log (issue.toJSON()); //+

					//for render
					//this.$el.append(this.editIssueTemplate({data: [ {issue: issue.toJSON()}, {categories: that.categories.toJSON()}, {statuses: that.statuses.toJSON()} ] });   // ?
					this.$el.append(this.editIssueTemplate(issue.toJSON()));   // ?

					$('#editIssueModal').modal();
					console.log ('--- --- data inserted from DB to fields ok'); //+
					
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

				logOut: function(){
					$.ajax('auth/logout');
					loginView.currentUser = null;
					router.navigate('', {trigger:true});
					if($('#notificationModal'))
						$('#notificationModal').remove();
					that.$el.append(that.notificationTemplate( { 'data': { 'message': "You have been successfully logged out!" }} ));
					$('#notificationModal').modal();
					loginView.buttonsManage();
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