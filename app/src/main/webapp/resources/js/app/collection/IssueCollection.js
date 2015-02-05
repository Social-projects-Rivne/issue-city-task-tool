define([ 'underscore', 'backbone', 'model/IssueModel', 'view/issueView' ], function(_,
		Backbone, IssueModel, IssueView) {

	var IssueCollection = Backbone.Collection.extend({
		
		model : IssueModel,
		url : 'Bawl/issues'
	});

	return IssueCollection;
});
