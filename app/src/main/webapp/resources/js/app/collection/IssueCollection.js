define([ 'underscore', 'backbone', 'model/IssueModel' ], function(_,
		Backbone, IssueModel) {

	var IssueCollection = Backbone.Collection.extend({
		
		model : IssueModel,
		url : ''
	});

	return IssueCollection;
});
