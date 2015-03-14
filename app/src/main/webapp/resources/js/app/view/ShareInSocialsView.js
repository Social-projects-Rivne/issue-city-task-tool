define([ 'jquery', 'underscore', 'backbone', ],
    function($, _, Backbone) {
      var ShareInSocialsView = Backbone.View.extend({
        events: {

        },

        initialize: function() {

        },

        getShortURL: function(longUrl){
          var shortUrl = "";
          $.ajax({
          type: "POST",
          contentType: "application/json; charset=utf-8",
          data: '{ "longUrl": "' + longUrl + '" }',
          url: "https://www.googleapis.com/urlshortener/v1/url",
          success: function(data) {
              //do something with the shortened url json data
              console.log(data.id);
              shortUrl = data.id;
            }
          });
          return shortUrl;
        },

      });

      return ShareInSocialsView;
    });
