define([ 'underscore', 'backbone', 'model/CategoryModel' ], function(_, Backbone, CategoryModel) {

	var CategoryCollection = Backbone.Collection.extend({
		
		model : CategoryModel,
		url : 'categories'
	});

	return CategoryCollection;
});
