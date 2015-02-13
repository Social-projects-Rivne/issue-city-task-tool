define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel', 'text!templates/AddIssue.html',
         'model/CategoryModel', 'collection/CategoryCollection' ],
		function($, _, Backbone, IssueModel, AddIssueTemplate, CategoryModel, CategoryCollection) {
			var AddIssueView = Backbone.View.extend({
				template: _.template(AddIssueTemplate),
				
				initialize: function() {
					this.model = new IssueModel();
					categoryCollection = new CategoryCollection();
				},
				
				events: {
					'click #next-to-description': 'nextToDescription',
					'click #next-to-photo': 'nextToPhoto',
					'click #tab1-title > a': 'tabChanger',
					'click #tab2-title > a': 'tabChanger',
					'click #tab3-title > a': 'tabChanger',
					'click #add-issue-button': 'addIssue'
				},
				
				render: function() {
					var that = this;
					
					categoryCollection.fetch( { success: function() {
						that.$el.html(that.template( { "categories": categoryCollection.toJSON() } ));
						
						issueName = $('#issue-name');
						issueCategory = $('#issue-category');
						issueDescription = $('#issue-description');
						error = $('#error');
						
						issueName.on('blur', function() {
							if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
								this.value = 'Wrong name!';
								this.style.color = 'red';
							}
						});

						issueName.on('focus', function() {
							if (this.value == 'Wrong name!') this.value ='';
							this.style.color = 'black';
						});
						
						issueCategory.on('blur', function() {
							if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
								this.value = 'Wrong category!';
								this.style.color = 'red';
							}
						});

						issueCategory.on('focus', function() {
							if (this.value == 'Wrong category!') this.value ='';
							this.style.color = 'black';
						});
						
						issueDescription.on('blur', function() {
							if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
								this.value = 'Wrong description!';
								this.style.color = 'red';
							}
						});

						issueDescription.on('focus', function() {
							if (this.value == 'Wrong description!') this.value ='';
							this.style.color = 'black';
						});
					} } ); 
					
					return this;
				},
				
				addIssue: function() {
					var isValid = true;
					
					if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issueName.val())) {
						issueName.val('Wrong name!').css('color', 'red');
						error.css({'color': 'red', 'textAlign': 'center', 'marginTop': '10px'}).html('Please fill the form correctly!');
						isValid = false;
					}
					
					if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issueCategory.val())) {
						issueCategory.val('Wrong category!').css('color', 'red');
						error.css({'color': 'red', 'textAlign': 'center', 'marginTop': '10px'}).html('Please fill the form correctly!');
						isValid = false;
					}
					
					if (!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issueDescription.val())) {
						issueDescription.val('Wrong description!').css('color', 'red');
						error.css({'color': 'red', 'textAlign': 'center', 'marginTop': '10px'}).html('Please fill the form correctly!');
						isValid = false;
					}
					
					if(isValid) {
						this.model.set( { mapPointer: $('#map-pointer').val(),
							name: $('#issue-name').val(),
							description: $('#issue-description').val(),
							category: $('#issue-category').val(),
							attachments: $('#issue-attachments').val()
						} );
						this.model.save();
					}
				},
				
				nextToDescription: function(e) {
					e.preventDefault();
					this.$el.find('#tab1-title').removeClass('active');
					this.$el.find('#tab2-title').addClass('active');
					this.$el.find('#tab1').removeClass('active');
					this.$el.find('#tab1').addClass('fade');
					this.$el.find('#tab2').removeClass('fade');
					this.$el.find('#tab2').addClass('active');
				},
				
				nextToPhoto: function(e) {
					e.preventDefault();
					this.$el.find('#tab2-title').removeClass('active');
					this.$el.find('#tab3-title').addClass('active');
					this.$el.find('#tab2').removeClass('active');
					this.$el.find('#tab2').addClass('fade');
					this.$el.find('#tab3').removeClass('fade');
					this.$el.find('#tab3').addClass('active');
				},
				
				tabChanger: function(e) {
					e.preventDefault();
					
					if(this.$el.find('#tab1-title').hasClass('active')) {
						this.$el.find('#tab1-title').removeClass('active');
						this.$el.find('#tab1').removeClass('active');
						this.$el.find('#tab1').addClass('fade');
					}
					else if(this.$el.find('#tab2-title').hasClass('active')) {
						this.$el.find('#tab2-title').removeClass('active');
						this.$el.find('#tab2').removeClass('active');
						this.$el.find('#tab2').addClass('fade');
					}
					else {
						this.$el.find('#tab3-title').removeClass('active');
						this.$el.find('#tab3').removeClass('active');
						this.$el.find('#tab3').addClass('fade');
					}
					
					if(e.currentTarget.parentNode.id == 'tab1-title') {
						this.$el.find('#tab1-title').addClass('active');
						this.$el.find('#tab1').removeClass('fade');
						this.$el.find('#tab1').addClass('active');
					}
					else if(e.currentTarget.parentNode.id == 'tab2-title') {
						this.$el.find('#tab2-title').addClass('active');
						this.$el.find('#tab2').removeClass('fade');
						this.$el.find('#tab2').addClass('active');
					}
					else {
						this.$el.find('#tab3-title').addClass('active');
						this.$el.find('#tab3').removeClass('fade');
						this.$el.find('#tab3').addClass('active');
					}
				}
			});
			
			return AddIssueView;
		})
