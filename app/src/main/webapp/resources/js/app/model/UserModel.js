define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var UserModel = Backbone.Model.extend({

		urlRoot: '',
		defaults : {
			id : 0,
			name : "",
			email : "",
			login : "",
			password : "",
			avatar : "",
			roleId : 0
		}
	});
	
	return UserModel;
});