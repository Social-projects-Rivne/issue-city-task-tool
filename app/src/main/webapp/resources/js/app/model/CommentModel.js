define([ 'underscore', 'backbone' ], function(_, Backbone) {

	return Backbone.Model.extend({
		url: 'add-comment',
		idAttribute: 'id',
		id:'',
		defaults: {
			comment: "",
			userName: "",
			email: "",
			issueId: 0
		}
	});
	
});