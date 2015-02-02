define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var CommentModel = Backbone.Model.extend({

		url: '/Bawl/add-comment',
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