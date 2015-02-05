define([ 'jquery', 'underscore', 'backbone',
		'text!templates/IssueDetails.html', 'text!templates/AddIssue.html',
		'model/IssueModel','model/CommentModel',],
		function($, _, Backbone, IssueDetailsTemplate, AddIssueTemplate, IssueModel,CommentModel) {

			var IssueView = Backbone.View.extend({

				model : IssueModel,
				//className :'grid',
				el: 'body',
				issueId: '',
				
				IssueDetailsTemplate : _.template(IssueDetailsTemplate),
				AddIssueTemplate : _.template(AddIssueTemplate),
				
				events : {
					'click #add_comment_button' : 'addComment',
					'click #cry-out' : 'addIssueForm',
					'click #myForestGump' : 'issueDetailsForm',
					'click .leaflet-clickable' : 'issueDetailsForm',
				},
				
				initialize: function() {
					console.log('initialized');

			        this.model = new IssueModel();
			    },
				
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
				},
				
				setIssueId : function(issueId) {
					this.issueId = issueId;
				},
				
				addComment : function() {
					var comment = new CommentModel({
						'userName':$(document.getElementsByName('userName')[0]).val(),
						'comment':$(document.getElementsByName('comment-text')[0]).val(),
						'email' : $(document.getElementsByName('email')[0]).val(),
						'issueId' : this.issueId});
					//console.log(model);
					console.log(this.model.get('id'));
					//console.log(this.model);
					$.ajax({
						url : "/Bawl/add-comment",
						type : 'POST',
						dataType : 'json',
						data : '{	"email":"' + $(document.getElementsByName('email')[0]).val() + 
									'","userName":"' + $(document.getElementsByName('userName')[0]).val() + 
									'","comment": "' + $(document.getElementsByName('comment-text')[0]).val() + 
									'","issueId":"' +  this.issueId +'"}',
						//data: comment,
						contentType : 'application/json',
						mimeType : 'application/json',
						success : function(data) {
							//alert(data.email + " " + data.issueId);
							console.log(data);
						},
						error : function(data, status, er) {
							console.log(data);	
						//	alert("error: " + data + " status: " + status + " er:" + er);
						}
					});
				}
			});

			return IssueView;

		});
