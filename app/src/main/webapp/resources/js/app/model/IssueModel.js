define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var IssueModel = Backbone.Model.extend({
		urlRoot: 'issue',
		defaults: {
			id: null,
			priorityId: 1,
			status: "new",
			name: "",
			description: "",
			mapPointer: "",
			attachments: "",
			category: "",
			statusId: "1",
			categoryId: "1"
		}
	});
	
	return IssueModel;
});