define([ 'underscore', 'backbone', 'model/CommentModel', 'view/CommentView' ], function(_,
		Backbone, CommentModel, CommentView) {

	var CommentCollection = Backbone.Collection.extend({
		
		issueId : '1',
		url : '/Bawl/all-comments/1', /* add here issueId */
		model : CommentModel,
		

		intialize : function(issueId) {
			this.issueId = issueId;
			//this.fetch();
			console.log('commentsCollection initializes');
		},
		
		
		//TODO: render from collection is incorrect! Remove it later
		render : function() {
			this.each(function(obj, index) {
				var commV = new CommentView({
					model : obj
				});
				commV.render();
				console.log(obj.toJSON());
			});
		}
	});

	return CommentCollection;

});
