requirejs.config({
	baseUrl:"resources",

	shim : {
		// add here another modules for correct functionflity
		
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
	paths:{

		// libs
		jquery: ['js/lib/jquery','jquery.serializejson'],
		underscore: 'js/lib/underscore',
		backbone: 'js/lib/backbone',
		text: 'js/lib/text',

		router: 'js/app/router',
		model: 'js/app/model',
		view: 'js/app/view',
		collection: 'js/app/collection',
		templates: 'js/app/templates',
		
		bootstrap:'js/bootstrap.min',

		gmaps: 'https://maps.googleapis.com/maps/api/js?key=AIzaSyDLXIjNBbjQ5nI9I8KIuR3hslBebOYNH4s&signed_in=true&callback=initMap',
		'async':'../vendor/requirejs-plugins/src/async',
		'jquery_serialize' : 'js/lib/jquery.serializejson',


		//bad scripts
		homeScript: 'js/home-script',
        validation: 'js/validation-script',
        markers: 'js/leaflet.awesome-markers',
        leaflet:'js/leaflet',
        
        map:[ 
            
        	'js/map-script',
        	'http://maps.googleapis.com/maps/api/js?sensor=true'
        ]
	}
});


//Load our app module and pass it to our definition function
require(['main', 'bootstrap', 'gmaps', 'jquery_serialize']);
