define([ 'underscore', 'backbone', 'model/CommentModel', 'view/CommentView' ], function(_,
		Backbone, CommentModel, CommentView) {

	var CommentCollection = Backbone.Collection.extend({
		intialize : function(issueId) {
			this.issueId = issueId;
			this.fetch();
			console.log('commentsCollection initializes');
		},
		issueId : '1',
		url : 'http://localhost:8080/Bawl/all-comments/1', /* add here issueId */
		model : CommentModel,
		
		//TODO: render from collection is incorrect! Remove it later
		render : function() {
			this.each(function(obj, index) {
				var commV = new CommentView({
					model : obj
				});
				commV.render();
				console.log(obj.toJSON());
				$(document.body.getElementsByClassName('comments')[0]).append(
						commV.el);
			});
		}
	});

	return CommentCollection;

});
