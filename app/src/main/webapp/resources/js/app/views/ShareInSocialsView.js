define([ 'jquery', 'underscore', 'backbone', ],
    function($, _, Backbone) {
      var ShareInSocialsView = Backbone.View.extend({
        events: {
          'click .share#facebook-share': 'facebook',
          'click #google-share': 'google',
          'click #twitter-share': 'twitter',
        },

        initialize: function() {
         // this.el = $('body')[0];
        },

        facebook: function(){
          var title = "Bawl";
          var url = window.location.href.toString();// this.getShortURL(window.location.href.toString())
          window.open("http://www.facebook.com/share.php?u=" + url + "&title=" + title, "_blank");
        },

        google: function(){
          var title = "Bawl";
          var url = window.location.href.toString();// this.getShortURL(window.location.href.toString()) 
          window.open("https://plus.google.com/share?url=" + url , "_blank");
        },

        twitter: function(){
          var title = "Bawl";
          var url = window.location.href.toString();// this.getShortURL(window.location.href.toString()) 
          window.open("http://twitter.com/home?status=" + title + "+" + url , "_blank");
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
