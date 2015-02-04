define([ 'underscore', 'backbone', 'model/CommentModel', 'view/CommentView' ], function(_,
		Backbone, CommentModel, CommentView) {

	var CommentCollection = Backbone.Collection.extend({
		
		id : '1',
		model : CommentModel,
		
		url :function(){
		    return '/Bawl/all-comments/' + this.id; 
		} ,
		  
		

		intialize : function(issueId) {
			this.issueId = issueId;
			//this.fetch();
			console.log('commentsCollection initializes');
		},
		
		
		//TODO: render from collection is incorrect! Remove it later
		render : function() {
			this.each(function(obj, index) {
				var commV = new CommentView({
					model : obj
				});
				commV.render();
				console.log(obj.toJSON());
			});
		},
		
	});

	return CommentCollection;

});
