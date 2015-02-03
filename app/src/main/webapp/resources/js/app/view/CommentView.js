define(
		[ 'underscore', 'backbone', 'model/CommentModel', 'text!templates/Comment.html'],
		function(_, Backbone, CommentModel, CommentTemplate) {

			var CommentView = Backbone.View
					.extend({
						initilize : function() {
							
						},
						
						tagName : 'div',
						className : 'comment',
						template : _.template(CommentTemplate),

						render : function() {
							// console.log('Render function working');
							this.$el.html(this.template(this.model.toJSON()));
							$(document.body.getElementsByClassName('comments')[0]).append(this.el);
						}
					});
			return CommentView;
		});