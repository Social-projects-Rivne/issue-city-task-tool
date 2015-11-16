define([ 'underscore', 'backbone' ], function(_, Backbone) {

	return Backbone.Model.extend({
		urlRoot: 'issue',
		defaults: {
			id: null,
			priorityId: 1,
			status: "NEW",
			name: "",
			description: "",
			mapPointer: "",
			attachments: "",
			category: "",
			categoryId: "1"
		}
	});
	
});