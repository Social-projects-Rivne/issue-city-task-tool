define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var IssueModel = Backbone.Model.extend({
		urlRoot: 'issue',
		defaults: {
			id: null,
			priorityId: 1,
			status: "",
			name: "",
			description: "",
			mapPointer: "",
			attachments: "",
			category: ""
		}
	});
	
	return IssueModel;
});