define([ 'underscore', 'backbone' ], function(_, Backbone) {

	return Backbone.Model.extend({
			defaults: {
				date: "",
				username: "",
				statusId:"",
				roleName:"",
				issueName: ""
			}
		});
		
});

	