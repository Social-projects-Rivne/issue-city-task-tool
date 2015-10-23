define([ 'underscore', 'backbone' ], function(_, Backbone) {

	return Backbone.Model.extend({
		urlRoot: 'user',
		defaults: {
			id: null,
			name: "",
			email: "",
			login: "",
			password: "",
			role_id: "-1",
			avatar:""
		}
	});
	
});