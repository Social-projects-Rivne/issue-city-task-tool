requirejs.config({

	baseUrl:"resources",

	shim : { // add here another modules for correct functionflity
		
		bootstrap : { 
			deps :['jquery']
		},

		templates:{
			deps :['text']
		},

		gmaps: {
			deps: ['jquery']
		},

		jquery_serialize : {
			deps: ['jquery']
		},

		main:{
			deps :['bootstrap','leaflet','js/issue-script','markers','router'] /*'gmaps'*/
		},

		map:{
			deps :['markers']
		},
		'view/MapView':{
			deps :['map','leaflet']
		}
    },

	paths:{ // libs

		jquery: ['js/lib/jquery','jquery.serializejson'],
		underscore: 'js/lib/underscore',
		backbone: 'js/lib/backbone',
		text: 'js/lib/text',

		router: 'js/app/router',
		model: 'js/app/model',
		view: 'js/app/view',
		collection: 'js/app/collection',
		templates: 'js/app/templates',
		
		bootstrap:'js/lib/bootstrap.min',

		/* Googl Server key*/
		googlkey: 'AIzaSyAa1n57LZd7gFR7cNc_YQD0sAaLZe6NDpc',

		 gmaps: 'https://maps.googleapis.com/maps/api/js?key = googlkey',
		'async':'../vendor/requirejs-plugins/src/async',
		'jquery_serialize' : 'js/lib/jquery.serializejson',

		//bad scripts
		homeScript: 'js/home-script',
        validation: 'js/validation-script',
        markers: 'js/lib/leaflet.awesome-markers',
        leaflet:'js/lib/leaflet',
        
        map:[
        	'js/map-script',
        	'http://maps.googleapis.com/maps/api/js?sensor=true'
        ]
	}
});

//Load our app module and pass it to our definition function
require(['main', 'bootstrap', 'gmaps', 'jquery_serialize']);
