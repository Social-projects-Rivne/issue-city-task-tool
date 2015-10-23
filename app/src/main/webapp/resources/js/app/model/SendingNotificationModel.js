define([ 'underscore', 'backbone' ], function(_, Backbone) {

	return Backbone.Model.extend({
		urlRoot: 'send-notification',
		defaults: {
			email: "",
      		subject: "",
			message: ""
		}
	});
	
});