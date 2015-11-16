define([ 'underscore', 'backbone', 'model/CommentModel' ], function(_, Backbone, CommentModel) {

	return Backbone.Collection.extend({
		model: CommentModel,
		url: 'comments/get'
	});

});
