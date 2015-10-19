define([ 'underscore', 'backbone' ], function(_, Backbone) {
	var HistoryIssueModel = Backbone.Model.extend({
			//url: 'issue',
			defaults: {
				date: "",
				username: "",
				statusId:"",
				roleName:"",
				issueName: "",
			}
		});
		
	return HistoryIssueModel;
});

	