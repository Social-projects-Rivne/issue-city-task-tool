requirejs.config({
	baseUrl:"resources",
	paths:{
		jquery: 'js/lib/jquery',
		underscore: 'js/lib/underscore',
		backbone: 'js/lib/backbone',
		model: 'js/app/model',
		view: 'js/app/view',
		collection: 'js/app/collection',
	}
});


//Load our app module and pass it to our definition function
require(['main']);

