define([ 'underscore', 'backbone', 'model/CommentModel' ], function(_,
		Backbone, CommentModel) {

	var CommentCollection = Backbone.Collection.extend({
		model: CommentModel,
		url: 'get-comments'	
	});

	return CommentCollection;
});
