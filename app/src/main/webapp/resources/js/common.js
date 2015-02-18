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
		
		main:{
			deps :['bootstrap','leaflet','js/issue-script','markers','router']
		},
		
		map:{
			deps :['markers'],
		},
		'view/MapView':{
			deps :['map','leaflet'],
		}
    },
	paths:{

		// libs
		jquery: 'js/lib/jquery',
		underscore: 'js/lib/underscore',
		backbone: 'js/lib/backbone',
		text: 'js/lib/text',

		router: 'js/app/router',
		model: 'js/app/model',
		view: 'js/app/view',
		collection: 'js/app/collection',
		templates: 'js/app/templates',
		
		bootstrap:'js/bootstrap.min',
		
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
require(['main', 'bootstrap']);
