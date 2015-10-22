define([ 'underscore', 'backbone', 'model/CategoryModel' ], function(_, Backbone, CategoryModel) {

	return Backbone.Collection.extend({
		model : CategoryModel,
		url : 'categories'
	});

});
