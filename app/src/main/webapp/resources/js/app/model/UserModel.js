define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var UserModel = Backbone.Model.extend({
		urlRoot: 'user',
		defaults: {
			id: null,
			name: "",
			email: "",
			login: "",
			password: "",
			avatar:"",
			role_id: 1
		}
	});
	
	return UserModel;
});