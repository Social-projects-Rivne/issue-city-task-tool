define([ 'underscore', 'backbone', 'model/CommentModel' ], function(_,
		Backbone, CommentModel) {

	var CommentCollection = Backbone.Collection.extend({
		intialize : function(issue_id) {
			this.issue_id = issue_id; 
		},
		issue_id,
		url: 'http://localhost:8080/Bawl/add-comment', /* add here issue_id*/
		model : CommentModel
	});
	return CommentCollection;

});
