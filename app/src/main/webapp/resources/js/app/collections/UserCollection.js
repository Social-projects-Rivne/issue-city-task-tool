define([ 'underscore', 'backbone', 'model/UserModel' ], function(_, Backbone, UserModel) {

	return Backbone.Collection.extend({
		model : UserModel,
		url : 'users/all'
	});

});
