define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel', 'text!templates/AddIssue.html' ],
		function($, _, Backbone, IssueModel, AddIssueTemplate) {
			var AddIssueView = Backbone.View.extend({
				template: _.template(AddIssueTemplate),
				
				initialize: function() {
					this.model = new IssueModel();
				},
				
				events: {
					'click #next-to-description': 'nextToDescription',
					'click #next-to-photo': 'nextToPhoto',
					'click #tab1-title > a': 'tabChanger',
					'click #tab2-title > a': 'tabChanger',
					'click #tab3-title > a': 'tabChanger',
				},
				
				render: function() {
					this.$el.html(this.template);
					
					return this;
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
