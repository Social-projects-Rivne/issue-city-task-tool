define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var CommentModel = Backbone.Model.extend({
		defaults : {
			id : 0,

			comment : "",

			userName : "",

			email : "",

			issueId : ""
		},
	});
	return CommentModel;
});