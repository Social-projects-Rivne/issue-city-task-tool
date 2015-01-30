define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var CommentModel = Backbone.Model.extend({

		url: 'http://localhost:8080/Bawl/add-comment',
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