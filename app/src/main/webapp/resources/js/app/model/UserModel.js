define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var UserModel = Backbone.Model.extend({
		url: 'add-new-user',
		id:'',
		defaults: {
			//id: 0,
			name: "",
			email: "",
			login: "",
			password: 0,
			avatar:""
		}
	});
	
	return UserModel;
});