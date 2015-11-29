define([ 'underscore', 'backbone' ], function(_, Backbone) {

	return Backbone.Model.extend({
		urlRoot: 'subscriptions/valid',
		defaults: {
			id: null,
			hash: ""
		}
	});
	
});