define([ 'underscore', 'backbone' ], function(_, Backbone) {

var Router = Backbone.Router.extend({

  routes: {
    "issue/:id":"issue",    // #issue
  },

  nitialize: function(){
	  console.log('router initialazed');
	  Backbone.history.start();  
  },
  
  issue: function(id) {
	  console.log('route to issue');
	  $('.col-1-3').hide();
	  issueDetailsView.render(id);
	  //remove it when comments will be rendering from issue details view fom
	  commentListView.render(id);
  },

});
	return Router
});
