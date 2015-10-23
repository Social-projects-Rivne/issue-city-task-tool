define([ 'underscore', 'backbone' ], function(_, Backbone) {

	return Backbone.Model.extend({
		urlRoot: 'categories/add',
		defaults: {
			id: null,
			name: ""
		}
	});
	
});