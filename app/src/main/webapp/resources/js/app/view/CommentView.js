define(
		[ 'underscore', 'backbone', 'model/CommentModel', 'text!templates/Comment.html'],
		function(_, Backbone, CommentModel, CommentTemplate) {

			var CommentView = Backbone.View
					.extend({
						
						initilize : function() {
							
						},
						
						events : {
							'click #add_comment_button' : 'addComment',
							
						},
						
						tagName : 'div',
						className : 'comment',
						template : _.template(CommentTemplate),

						render : function() {
							// console.log('Render function working');
							this.$el.html(this.template(this.model.toJSON()));
							$(document.body.getElementsByClassName('comments')[0]).append(this.el);
						},
						
						addComment: function() {
							console.log('add comment button event')
						},
					});
			return CommentView;
		});