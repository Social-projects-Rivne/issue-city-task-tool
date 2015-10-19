define([ 'underscore', 'backbone', 'model/HistoryIssueModel' ], function(_,
		Backbone, HistoryIssueModel) {
	var HistoryCollection = Backbone.Collection.extend({
			model : HistoryIssueModel,

	});
	return HistoryCollection;
});


