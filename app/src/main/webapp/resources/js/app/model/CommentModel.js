define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var CommentModel = Backbone.Model.extend({
		url: 'add-comment',
		defaults: {
			comment: "",
			userName: "",
			email: "",
			issueId: 0
		}
	});
	
	return CommentModel;
});