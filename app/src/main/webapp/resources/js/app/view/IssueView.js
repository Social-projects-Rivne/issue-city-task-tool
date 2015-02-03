define([ 'jquery', 'underscore', 'backbone',
		'text!templates/IssueDetails.html', 'text!templates/AddIssue.html',
		'model/IssueModel',],
		function($, _, Backbone, IssueDetailsTemplate, AddIssueTemplate, IssueModel) {

			var IssueView = Backbone.View.extend({

				model : IssueModel,
				//className :'grid',
				//el: '#issue-form',

				IssueDetailsTemplate : _.template(IssueDetailsTemplate),
				AddIssueTemplate : _.template(AddIssueTemplate),

				events : {
					'click #cry-out' : 'addIssueForm',
					'click #myForestGump' : 'issueDetailsForm',
					'click .leaflet-clickable' : 'issueDetailsForm',
				},

				/*addIssueForm : function(e){
					console.log('cry-out button event');
					this.render();
				},*/

				runForest : function(e) {
					alert('I`m runninig! ');
				},
				// TODO: render from collection is incorrect! Remove it later
				render : function() {
					console.log('Render function working');

				},
				//this.model.toJSON())
				issueDetailsForm : function() {
					// clear block with form
					$('.grid #issue-form').innerHTML = '';
					var temp = this.$el;
					this.$el = $("#issue-form");
					this.$el.html(this.IssueDetailsTemplate);
					this.$el = temp;
					console.log('apended');
				},

				addIssueForm : function() {
					// clear block with form
					$('.grid #issue-form').innerHTML = '';
					var temp = this.$el;
					this.$el = $("#issue-form");
					this.$el.html(this.AddIssueTemplate);
					this.$el = temp;
					console.log('apended');
				}

			});

			return IssueView;

		});
