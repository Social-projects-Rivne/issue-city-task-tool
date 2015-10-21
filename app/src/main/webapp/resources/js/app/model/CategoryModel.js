define([ 'underscore', 'backbone' ], function(_, Backbone) {

	var CategoryModel = Backbone.Model.extend({
		urlRoot: 'categories/add',
		defaults: {
			id: null,
			name: ""
		}
	});
	
	return CategoryModel;
});