requirejs.config({
	baseUrl:"",
	paths:{
		jquery: 'lib/jquery',
		underscore: 'lib/underscore',
		backbone: 'lib/backbone',
	}
});


//Load our app module and pass it to our definition function
require(['console', 'app']);