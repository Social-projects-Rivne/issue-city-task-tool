define([ 'underscore', 'backbone', 'model/CategoryModel' ], function(_, Backbone, CategoryModel) {

	var CategoryCollection = Backbone.Collection.extend({
		
		model : CategoryModel,
		url : 'get-categories'
	});

	return CategoryCollection;
});
