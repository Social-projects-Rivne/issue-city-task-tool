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

                var FILL_FORM_MESSAGE = "Please fill the form correctly!";
                var ERROR_FILL_FORM_CSS = "{'color': 'red', 'textAlign': 'center', 'marginTop': '10px'}";
                var ERROR_FILL_FIELD_CSS = "{'color', 'red'}";
                var WRONG_NAME = 'Wrong name!';
                var WRONG_CATEGORY = 'Wrong category!';
                var WRONG_DESCRIPTION = 'Wrong description!';
                var FIELD_VALIDATE_PATTERN = new RegExp("^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$");
			    var that;
                var categoryCollection;
	
			return Backbone.View.extend({

				template: _.template(AddIssueTemplate),
				notificationTemplate: _.template(NotificationTemplate),

				initialize: function() {
					that = this;
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

					categoryCollection.fetch( { success: function() {

						that.$el.html(that.template( { "categories": categoryCollection.toJSON() } ));

						var issueName = $('#issue-name');
						var issueCategory = $('#issue-category');
						var issueDescription = $('#issue-description');
						var error = $('#error');
						
                        issueName.on({
                            blur : function() {
                                onblur(this, WRONG_NAME);
                            },
                            focus : function() {
                                onfocus(this, WRONG_NAME);
                            }});
						
						issueCategory.on({
                            blur: function() {
                                onblur(this, WRONG_CATEGORY);
                            },
                            focus: function() {
                                onfocus(this, WRONG_CATEGORY);
                            }});
						
						issueDescription.on({
                            blur: function() {
                                onblur(this, WRONG_DESCRIPTION);
                            },
						    focus : function() {
                                onfocus(this, WRONG_DESCRIPTION);
						}});

                        var onblur = function (field, text) {
                            if (!FIELD_VALIDATE_PATTERN.test(field.value)) {
                                field.value = text;
                                field.style.color = 'red';
                            }
                        }
                        var onfocus = function (field, text) {
                            if (field.value == text) {
                                field.value ='';
                                field.style.color = 'black';
                            }
                        }

					} } ); 
					
					return this;
				},
				
				addIssue: function() {

                    var testField = function(field, error) {
                        if (!FIELD_VALIDATE_PATTERN.test(field.val())) {
                            field.val(WRONG_NAME);
                            error.html(FILL_FORM_MESSAGE);
                            field[0].style.cssText = ERROR_FILL_FIELD_CSS;
                            error[0].style.cssText = ERROR_FILL_FORM_CSS;
                            return false;
                        }
                        return true;
                    }


					if(testField( $('#issue-name'), $('#error')) && testField($('#issue-category'), $('#error')) && testField($('#issue-description'), $('#error'))) {
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
