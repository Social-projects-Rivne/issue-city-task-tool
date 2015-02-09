define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel','model/CommentModel', 'view/CommentView', 'text!templates/IssueDetails.html',  ],
		function($, _, Backbone, IssueModel, CommentModel, CommentView, IssueDetailsTemplate) {
			var IssueDetaisView = Backbone.View.extend({
				template: _.template(IssueDetailsTemplate),
				
				initialize: function() {
					this.model = new IssueModel();
				},
				
				render: function(id) {
					var that = this;
					
					this.model.set("id", id);
					this.model.fetch( { success: function() {
						that.$el.html(that.template(that.model.toJSON()));
					} } );

					return this;
				},
				
				events: {
					'click #add_comment_button': 'addComment'
				},
				
				addComment : function() {
					
				 comment = new CommentModel({
					 "email": $(document.getElementsByName('email')[0]).val(),
					 "userName": $(document.getElementsByName('userName')[0]).val(),
					 "comment":  $(document.getElementsByName('comment-text')[0]).val(),
					 "issueId": +  this.model.id });
				 try{
					 comment.save();
					 console.log('comment add function');
					
					 var commentView = new CommentView({
							model : comment
					 });
					 commentView.render();
					 
					 //clan fields
					 alert(comment.get('userName') + ", thanks you for yours comment!" );
						document.getElementsByName('email')[0].value = "";
						document.getElementsByName('userName')[0].value = "";
						document.getElementsByName('comment-text')[0].value = "";
						
				 }catch(error){
					 alert(error);
				 }
				 
				 
				}
				
			});	
			
			return IssueDetaisView;
		})
