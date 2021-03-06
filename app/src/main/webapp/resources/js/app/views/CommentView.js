define(
		[ 'underscore', 'backbone', 'model/CommentModel', 'text!templates/Comment.html' ],
		function(_, Backbone, CommentModel, CommentTemplate) {
			var CommentView = Backbone.View
					.extend({
						template: _.template(CommentTemplate),
						
						initilize: function() {
							this.model = new CommentModel();
						},
						
						render: function() {
							this.$el.html(this.template(this.model.toJSON()));
							$(document.body.getElementsByClassName('comments')[0]).prepend(this.el);
							return this;
						},
						
						renderNewComment : function() {
							template = _.template('<label class="comments_user_name"> <%= userName %> </label><br>'
									+ ' <label	class="comment_name"> <%= comment %> </label><hr width="100%" size="2">'),

							this.$el.html(template(this.model.toJSON()));
							$(document.body.getElementsByClassName('comments')[0]).prepend(this.el);
				}
					});
			
			return CommentView;
		});