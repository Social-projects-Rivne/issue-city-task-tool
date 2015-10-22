define([ 'underscore', 'backbone', 'model/StatusModel' ], function(_, Backbone, StatusModel) {

	return Backbone.Collection.extend({
		model : StatusModel,
		url : 'get-statuses'
	});

});
