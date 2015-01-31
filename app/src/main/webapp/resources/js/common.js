requirejs.config({
	baseUrl:"resources",
	shim : {
		bootstrap : { 
			deps :['jquery']
		}
    },
	paths:{
		jquery: 'js/lib/jquery',
		underscore: 'js/lib/underscore',
		backbone: 'js/lib/backbone',
		model: 'js/app/model',
		view: 'js/app/view',
		collection: 'js/app/collection',
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
