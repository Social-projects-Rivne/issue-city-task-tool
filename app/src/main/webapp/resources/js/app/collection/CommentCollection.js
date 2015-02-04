define([ 'underscore', 'backbone', 'model/CommentModel', 'view/CommentView' ], function(_,
		Backbone, CommentModel, CommentView) {

	var CommentCollection = Backbone.Collection.extend({
		
		id : '',
		
		model : CommentModel,
		url :function(){
		    return '/Bawl/all-comments/' + this.id; 
		} ,
		
		
		setID :function(id){
		    this.id = id; 
		    console.log('id of comment colelction changed on ' + id);
		} ,
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
