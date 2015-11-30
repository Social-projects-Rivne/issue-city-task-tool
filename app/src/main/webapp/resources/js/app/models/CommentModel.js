define([ 'underscore', 'backbone' ], function(_, Backbone) {

	return Backbone.Model.extend({
		url: 'comments/add',
		idAttribute: 'id',
		id:'',
		defaults: {
			comment: "",
			userName: "",
			email: "",
			issueId: 0,
			avatar: ""
		}
	});
	
});