define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var CommentModel = Backbone.Model.extend({
		url: 'add-comment',
		idAttribute: 'id',
		id:'',
		defaults: {
			//id: 0,
			comment: "",
			userName: "",
			email: "",
			issueId: 0
		}
	});
	
	return CommentModel;
});