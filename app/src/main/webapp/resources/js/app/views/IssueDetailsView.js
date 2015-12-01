define([ 'jquery', 'underscore', 'backbone', 'model/IssueModel','model/CommentModel', 'view/CommentView', 'view/ShareInSocialsView','text!templates/IssueDetails.html', 'text!templates/NotificationTemplate.html'],
		function($, _, Backbone, IssueModel, CommentModel, CommentView, ShareInSocialsView, IssueDetailsTemplate, NotificationTemplate) {

			var that = null;
			return Backbone.View.extend({

				notificationTemplate: _.template(NotificationTemplate),
				template: _.template(IssueDetailsTemplate),
				shareInSocialsView :null,
				initialize: function() {
					this.model = new IssueModel();
					this.shareInSocialsView =  new ShareInSocialsView({el: this.el})
				},

				render: function(id) {
					that = this;
					this.model.set("id", id);
					this.model.fetch( { success: function() {
						that.$el.hide();
						that.$el.html(that.template(that.model.toJSON()));
						commentListView.render(that.model.get('id'));
						if (loginView.currentUser != null && loginView.currentUser.get("id") != null){
							// user
							$("#comment-input-form").hide();
                            $(".resolve-btn").show();

                            $(".resolve-subscribe-user").show();
                            $(".resolve-subscribe-sub").hide();
							$('[name*="subscribe"]').popover();
						}
						else {// sub
							$(".resolve-subscribe-user").hide();
							$(".resolve-subscribe-sub").show();
							$('[name*="subscribe"]').popover();
						}
						that.$el.fadeIn();


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
					that = this;
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
								$('body').append(that.notificationTemplate( { 'data': response } ));
								$('#notificationModal').modal();
							}});
				},


				subscribe: function(e){	//Subscribe method

					if (loginView.currentUser != null && loginView.currentUser.get("id") != null){ // user
//!!!
						var folowerEmail = loginView.currentUser.get("email");
						//var issueId = loginView.currentUser.get("issueId");
						console.log("## email = "+folowerEmail);
						//console.log("## issueId = "+issueId);

						//console.log("email");

					} else { // sub
						var folowerEmail = $('[id="folower-email"]').val();
						console.log("## folowerEmail = "+ folowerEmail);
					}
					$.ajax({
						url: '/subscriptions/add',
						method:'POST',
						contentType:'application/json',
						data:'{"issueId":' + e.currentTarget.id + ',"email":"' + folowerEmail +'"}',
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
							$('body').append(that.notificationTemplate( { 'data': response } ));
							$('#notificationModal').modal();

						}});
				},

				addComment : function() {
					var comment = new CommentModel;
					if (!_.isNull(loginView.currentUser) && !_.isNull(loginView.currentUser.get("id"))) { //logined user
						comment.set({
							"email": loginView.currentUser.get('email'),
							"userName": loginView.currentUser.get('name')
						})
					}
					else if (($('.comment-email').val() != '') && ($('.comment-username').val() != '')) {
						comment.set({
							"email": $('.comment-email').val(),
							"userName": $('.comment-username').val()
						})
					}
					if (comment.get('userName') != "" && comment.get('email') != "" && $('.comment-text').val() != "") {
						comment.set({
							"comment": $('.comment-text').val(),
							"issueId": this.model.id
						})
						comment.save({}, {
							success: function (model, response) {
								if ($('#notificationModal')) {	$('#notificationModal').remove();}
								that.$el.append(that.notificationTemplate({'data': response}));
								$('#notificationModal').modal();
								var commentView = new CommentView({
									model: comment
								});
								commentView.render();
								that.cleanFields();
							},
							error: function () {
								if ($('#notificationModal')) {$('#notificationModal').remove();	}
								that.$el.append(that.notificationTemplate({'data': {'message': 'Error!'}}));
								$('#notificationModal').modal();
							}
						});
					}
					else {	//if no username in field or no loged in user
						if ($('#notificationModal')) $('#notificationModal').remove();
						$('body').append(this.notificationTemplate({'data': {'message': "Error! Fill correct all fields!"}}));
						$('#notificationModal').modal();
					}
				},

				cleanFields : function () {
					document.getElementsByName('email')[0].value = "";
					document.getElementsByName('userName')[0].value = "";
					document.getElementsByName('comment-text')[0].value = "";
				}
			});
		})


