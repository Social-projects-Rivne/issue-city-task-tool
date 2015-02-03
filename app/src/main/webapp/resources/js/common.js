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
		
		map:{
			deps :['markers'],
		}
    },
	paths:{

		// libs
		jquery: 'js/lib/jquery',
		underscore: 'js/lib/underscore',
		backbone: 'js/lib/backbone',
		text: 'js/lib/text',

		model: 'js/app/model',
		view: 'js/app/view',
		collection: 'js/app/collection',
		templates: 'js/app/templates',

		//bad scripts
		homeScript: 'js/home-script',
        validation: 'js/validation-script',
        markers: 'js/leaflet.awesome-markers',
        leaflet:'js/leaflet',
        bootstrap:[
			'js/bootstrap.min'
			//add here css
        ],
        map:[ 
            
        	'js/map-script',
        	'http://maps.googleapis.com/maps/api/js?sensor=true'
        ]
	}
});


//Load our app module and pass it to our definition function
require(['main']);
	/*

        	
        	'js/home-script',
        	'js/issue-script',
        	*/
