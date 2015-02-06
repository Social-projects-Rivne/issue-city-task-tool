define(
		[ 'underscore', 'backbone', 'model/CommentModel', 'text!templates/Comment.html'],
		function(_, Backbone, CommentModel, CommentTemplate) {
			var CommentView = Backbone.View
					.extend({
						template: _.template(CommentTemplate),
						
						initilize: function() {
							this.model = new CommentModel();
						},
						
						render: function() {
							this.$el.html(this.template(this.model.toJSON()));
							
							return this;
						}
					});
			
			return CommentView;
		});