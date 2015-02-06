define([ 'jquery', 'underscore', 'backbone', 'model/CommentModel', 
        'collection/CommentCollection', 'view/CommentView' ],
		function($, _, Backbone, CommentModel, CommentCollection, CommentView) {
			var CommentListView = Backbone.View.extend({
				initialize : function() {
					this.model = new CommentCollection();
				},
				
				render : function(id) {
					this.model.fetch( { data: {issueId: id} } );
					that = this;
					
					setTimeout(commentsRender, 1000);
				}
			});	
			
			function commentsRender() {
				that.model.each(function(comment) {
					var commentView = new CommentView( { model: comment } );
					//that.$el.append(commentView.render().$el);

					$(document.body.getElementsByClassName('comments')[0]).append(commentView.render().$el);
				});
			}
			
			return CommentListView;
		})
