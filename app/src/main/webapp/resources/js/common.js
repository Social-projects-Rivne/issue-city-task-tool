'use strict';

requirejs.config({

  baseUrl: "resources",

  shim: {

    bootstrap: {
      deps: ['jquery']
    },

    templates: {
      deps: ['text']
    },

    gmaps: {
      deps: ['jquery']
    },

    jquery_serialize: {
      deps: ['jquery']
    },

    main: {
      deps: ['bootstrap', 'leaflet', 'markers', 'router']
    },

    map: {
      deps: ['markers']
    },

    'view/MapView': {
      deps: ['map', 'leaflet']
    }
  },

  paths: {
    jquery: ['js/app/vendor/jquery', 'jquery.serializejson'],
    underscore: 'js/app/vendor/underscore-min',
    backbone_route_control: 'js/app/vendor/backbone-route-control',
    backbone: 'js/app/vendor/backbone',
    text: 'js/app/vendor/text',
    router: 'js/router',
    model: 'js/app/models',
    view: 'js/app/views',
    collection: 'js/app/collections',
    templates: 'js/app/templates',
    controllers: 'js/app/controllers',
    bootstrap: 'js/app/vendor/bootstrap.min',
    async: '../vendor/requirejs-plugins/src/async',
    jquery_serialize: 'js/app/vendor/jquery.serializejson',
    markers: 'js/app/vendor/leaflet.awesome-markers',
    leaflet: 'js/app/vendor/leaflet',

    /* Googl Server key*/
    googlkey: 'AIzaSyAa1n57LZd7gFR7cNc_YQD0sAaLZe6NDpc',
    gmaps: 'https://maps.googleapis.com/maps/api/js?key = googlkey',

    map: [
      'http://maps.googleapis.com/maps/api/js?sensor=true'
    ]
  }
});

//Load our app module and pass it to our definition function
require(['main', 'bootstrap', 'gmaps', 'jquery_serialize']);
