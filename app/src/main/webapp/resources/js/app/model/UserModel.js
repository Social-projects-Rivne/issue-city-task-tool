define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var UserModel = Backbone.Model.extend({
		urlRoot: '',
		defaults: {
			name: "",
			email: "",
			login: "",
			password: "",
			avatar: "",
			roleId: 0
		}
	});
	
	return UserModel;
});