define([ 'underscore', 'backbone', 'model/IssueModel' ], function(_, Backbone, IssueModel) {

	return Backbone.Collection.extend({
		model : IssueModel,
		url : 'issue/get'
	});

});
