define([ 'underscore', 'backbone', 'model/StatusModel' ], function(_, Backbone, StatusModel) {

	var StatusCollection = Backbone.Collection.extend({
		
		model : StatusModel,
		url : 'get-statuses'
	});

	return StatusCollection;
});
