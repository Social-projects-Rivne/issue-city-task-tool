define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var CategoryModel = Backbone.Model.extend({
		urlRoot: 'category',
		defaults: {
			id: null,
			name: ""
		}
	});
	
	return CategoryModel;
});