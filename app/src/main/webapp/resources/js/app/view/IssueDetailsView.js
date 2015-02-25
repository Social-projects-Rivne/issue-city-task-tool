define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel','model/CommentModel', 'view/CommentView', 'text!templates/IssueDetails.html', 'text!templates/NotificationTemplate.html', ],
		function($, _, Backbone, IssueModel, CommentModel, CommentView, IssueDetailsTemplate, NotificationTemplate) {
			var IssueDetaisView = Backbone.View.extend({
				
				notificationTemplate: _.template(NotificationTemplate),
				template: _.template(IssueDetailsTemplate),
				
				initialize: function() {
					this.model = new IssueModel();
				},
				
				render: function(id) {
					var that = this;
					
					this.model.set("id", id);
					this.model.fetch( { success: function() {
						that.$el.html(that.template(that.model.toJSON()));
						commentListView.render(that.model.get('id'));
					} } );

					
					
					return this;
				},
				
				events: {
					'click #add_comment_button': 'addComment'
				},
				
				addComment : function() {
				if(($('#add-comment  [name*="userName"]').val() !='') && ($('#add-comment  [name*="email"]').val() !='')){	
					 comment = new CommentModel({
						 "email": $(document.getElementsByName('email')[0]).val(),
						 "userName": $(document.getElementsByName('userName')[0]).val(),
						 "comment":  $(document.getElementsByName('comment-text')[0]).val(),
						 "issueId":  this.model.id });
					 try{
						 comment.save();
						 console.log('comment add function');
						 
						 
						 var commentView = new CommentView({
								model : comment
						 });
						 commentView.render();
						//notification						
						if($('#notificationModal')) $('#notificationModal').remove();
						$('body').append(this.notificationTemplate( { 'data': { 'message': comment.get('userName') + ", thanks you for yours comment!" } } ));
						$('#notificationModal').modal();
						//clan fields
						document.getElementsByName('email')[0].value = "";
						document.getElementsByName('userName')[0].value = "";
						document.getElementsByName('comment-text')[0].value = "";
							
					 }catch(error){
						if($('#notificationModal')) $('#notificationModal').remove();
						$('body').append(this.notificationTemplate( { 'data': { 'message': error } } ));
						$('#notificationModal').modal();
					 }
					 
					 
					}else{
						if($('#notificationModal')) $('#notificationModal').remove();
						$('body').append(this.notificationTemplate( { 'data': { 'message': "Error! Input your name!" } } ));
						$('#notificationModal').modal();
				}	
			}
			});	
			
			return IssueDetaisView;
		})
