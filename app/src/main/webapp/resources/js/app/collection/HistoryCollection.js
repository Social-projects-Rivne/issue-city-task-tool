define([ 'underscore', 'backbone', 'model/HistoryIssueModel' ], function(_, Backbone, HistoryIssueModel) {

	return Backbone.Collection.extend({
			model : HistoryIssueModel
	});

});


