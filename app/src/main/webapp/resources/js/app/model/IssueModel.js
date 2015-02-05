define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var IssueModel = Backbone.Model.extend({

		urlRoot: 'get-issue',
		defaults : {
			id : 0,
			categoryId : 0,
			priorityId : 0,
			name : "",
			description: "",
			mapPointer : "",
			attachments : "",
		}
	});
	
	return IssueModel;
});