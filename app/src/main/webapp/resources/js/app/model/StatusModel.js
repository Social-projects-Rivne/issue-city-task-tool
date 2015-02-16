define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var StatusModel = Backbone.Model.extend({
		urlRoot: 'status',
		defaults: {
			id: null,
			name: ""
		}
	});
	
	return StatusModel;
});