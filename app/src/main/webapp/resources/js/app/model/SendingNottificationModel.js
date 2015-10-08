define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var SendingNottificationModel = Backbone.Model.extend({
		urlRoot: 'send-notification',
		defaults: {
			email: "",
      subject: "",
			message: "",
		}
	});
	
	return SendingNottificationModel;
});