define([ 'underscore', 'backbone', 'model/UserModel' ], function(_,
		Backbone, UserModel) {

	var UserCollection = Backbone.Collection.extend({
		
		model : UserModel,
		url : 'get-users'
	});

	return UserCollection;
});
