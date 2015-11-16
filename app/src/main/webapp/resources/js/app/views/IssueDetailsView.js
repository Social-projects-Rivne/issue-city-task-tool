define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel','model/CommentModel', 'view/CommentView', 'view/ShareInSocialsView','text!templates/IssueDetails.html', 'text!templates/NotificationTemplate.html', ],
		function($, _, Backbone, IssueModel, CommentModel, CommentView, ShareInSocialsView, IssueDetailsTemplate, NotificationTemplate) {
			var IssueDetailsView = Backbone.View.extend({

				notificationTemplate: _.template(NotificationTemplate),
				template: _.template(IssueDetailsTemplate),
				shareInSocialsView :null,
				initialize: function() {
					this.model = new IssueModel();
					this.shareInSocialsView =  new ShareInSocialsView({el: this.el})
				},

				render: function(id) {
					var that = this;

					this.model.set("id", id);
					this.model.fetch( { success: function() {
						that.$el.hide();
						that.$el.html(that.template(that.model.toJSON()));
						commentListView.render(that.model.get('id'));
						that.$el.fadeIn();
						$('[name*="subscribe"]').popover();
					} } );



					return this;
				},

				events: {
					'click #add_comment_button': 'addComment',
					'click [name="resolve"]': 'changeStatus',
					'click [name*="send-folower-email"]': 'subscribe'

				},
				//event for btn Resolve
				changeStatus: function(e){
					var that = this;
					id = e.currentTarget.id;
					$.ajax({
							url: '/issue/resolve/'+id,
							type: 'POST',
							success: function(response) {
								if($('#notificationModal')) {
									$('#notificationModal').remove();
								}
								$(".signUp.modal").modal("hide");
								$('body').append(that.notificationTemplate( { 'data': response } ));
								$('#notificationModal').modal();
							},
							error: function(){
								if($('#notificationModal')) {
									$('#notificationModal').remove();
								}
								$('body').append(that.notificationTemplate( { 'data': { 'message': 'Error!' } } ));
								$('#notificationModal').modal();
							}});
				},

				//Subscribition method
				subscribe: function(e){
					var folowerEmail = $('[id="folower-email"]').val();
					console.log(folowerEmail);
					$.ajax({url:"/subscriptions/add", method:'POST', contentType:'application/json',data:'{"issueId":' + e.currentTarget.id + ',"email":"' + folowerEmail +'"}'})
					//notitfication
					if($('#notificationModal'))
						$('#notificationModal').remove();
					$('body').append(this.notificationTemplate( { 'data': { 'message': "Thank you for your email subscription. You have now been added to the mailing list!" } } ));
					$('#notificationModal').modal();
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
						//clean fields
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

			return IssueDetailsView;
		})
