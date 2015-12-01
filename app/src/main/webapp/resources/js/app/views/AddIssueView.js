define([
		'jquery',
		'underscore',
		'backbone',
		'model/IssueModel',
		'text!templates/AddIssue.html',
        'model/CategoryModel',
		'collection/CategoryCollection',
		'text!templates/NotificationTemplate.html'
], function($, _, Backbone, IssueModel, AddIssueTemplate, CategoryModel, CategoryCollection, NotificationTemplate ) {

                'use strict';

                var WRONG_NAME = 'Wrong name!';
                var WRONG_CATEGORY = 'Wrong category!';
                var WRONG_DESCRIPTION = 'Wrong description!';
			    var that;
                var categoryCollection;
	
			return Backbone.View.extend({

				template: _.template(AddIssueTemplate),
				notificationTemplate: _.template(NotificationTemplate),

				initialize: function() {
					that = this;
					this.model = new IssueModel();
					this.categoryCollection = new CategoryCollection();
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

					this.categoryCollection.fetch( { success: function() {
						that.categoryCollection = new CategoryCollection(that.categoryCollection.where( {state : CATEGORY_NEW}));
						that.$el.html(that.template( { "categories": that.categoryCollection.toJSON() } ));

						var issueName = $('#issue-name');
						var issueCategory = $('#issue-category');
						var issueDescription = $('#issue-description');
						var error = $('#error');
						
                        issueName.on({
                            blur : function() {
                                validator.onblur(this, WRONG_NAME);
                            },
                            focus : function() {
								validator.onfocus(this, WRONG_NAME);
                            }});
						
						issueCategory.on({
                            blur: function() {
								validator.onCategoryBlur(this, WRONG_CATEGORY);
                            },
                            focus: function() {
								validator.onfocus(this, WRONG_CATEGORY);
                            }});
						
						issueDescription.on({
                            blur: function() {
								validator.onblur(this, WRONG_DESCRIPTION);
                            },
						    focus : function() {
								validator.onfocus(this, WRONG_DESCRIPTION);
						}});

					} } ); 
					
					return this;
				},
				
				addIssue: function() {
					if(validator.testField( $('#issue-name'), $('#error-add-issue')) && validator.testCategoryField($('#issue-category'), $('#error-add-issue'))
						&& validator.testField($('#issue-description'), $('#error-add-issue')) && !_.isEmpty($('#map-pointer').val())) {
						this.model.set( {
                            mapPointer: $('#map-pointer').val(),
							name: $('#issue-name').val(),
							description: $('#issue-description').val(),
							category: $('#issue-category').val(),
							attachments: $('#issue-attachments').val()
						} );
						this.model.save( {}, { 
							success: function(model, response) {
								mapView.render();
								if($('#notificationModal')) {
                                    $('#notificationModal').remove();
                                }
								that.$el.append(that.notificationTemplate( { 'data': response } ));
								$('#notificationModal').modal();
							},
							error: function() {
								if($('#notificationModal')) {
                                    $('#notificationModal').remove();
                                }
								that.$el.append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
								$('#notificationModal').modal();
							}
						} );
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
		})
