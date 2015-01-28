requirejs.config({
	baseUrl:"",
	paths:{
		jquery: 'js/lib/jquery',
		underscore: 'js/lib/underscore',
		backbone: 'js/lib/backbone',
		model: 'js/app/model',
		view: 'js/app/view',
	}
});


//Load our app module and pass it to our definition function
require(['main']);

