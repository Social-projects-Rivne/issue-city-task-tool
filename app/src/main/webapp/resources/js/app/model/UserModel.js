define([ 'underscore', 'backbone' ], function(_, Backbone) {

	return Backbone.Model.extend({
		urlRoot: 'users',
		defaults: {
			id: null,
			name: "",
			email: "",
			login: "",
			password: "",
			roleId: "-1",
			avatar:""
		}
	});
	
});