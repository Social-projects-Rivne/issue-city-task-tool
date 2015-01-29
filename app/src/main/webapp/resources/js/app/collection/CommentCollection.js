define([ 'underscore', 'backbone', 'model/CommentModel' ], function(_,
		Backbone, CommentModel) {

	var CommentCollection = Backbone.Collection.extend({
		intialize : function() {

		},

		model : CommentModel
	});
	return CommentCollection;

});