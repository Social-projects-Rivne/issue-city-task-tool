require([
        'jquery',
        'underscore',
        'backbone',
        'model/CommentModel',
        'view/CommentView',
        'collection/CommentCollection'
        ]
, function($,_,Backbone,CommentModel,CommentViev,CommentCollection) {
	
	var comments = null;
	
	jQuery(document).ready(function($) {
		comments = new CommentCollection();
	});

	document.getElementById('add_comment_button').addEventListener('click', function(event) {
		event.preventDefault();
		sendNewComment();
	}, false);
	
	//add this 
	function sendNewComment(){
		
		//create new comment 
		var comment = new CommentModel({
			'userName':$(document.getElementsByName('userName')[0]).val(),
			'comment':$(document.getElementsByName('comment-text')[0]).val(),
			'email' : $(document.getElementsByName('email')[0]).val(),
			'issueId' : ""
		});
		
		//fetch comments from server
		comments.fetch();
		
		// add comment to collection
		comments.add(comment);
		
		
		comments.each(function(obj,index){
					var commV = new CommentViev({model:obj}); 
					commV.render(); 
					console.log(obj.toJSON()); 
					$(document.body.getElementsByClassName('comments')[0]).append(commV.el);});
			
	
		
		$(document.body.getElementsByClassName('comments')[0]).append(commentView.el);
		
		console.log(comment.toJSON());
		
		console.log(comment),
		
		sendAjax('{"email":"' + document.getElementsByName("email")[0].value +
				'","userName":"' + document.getElementsByName("userName")[0].value  +
				'", "comment": "' + document.getElementsByName("comment-text")[0].value  + 
				'", "issueId":"1"}');
	}
	// var comments = new CommentCollection;
});

// separate this function to another file
function sendAjax(comment) {

	$
			.ajax({
				url : "/Bawl/add-comment",
				type : 'POST',
				dataType : 'json',
				// data : '{"email":"' +
				// document.getElementsByName("email")[0].value +
				// '","userName":"' +
				// document.getElementsByName("userName")[0].value + '",
				// "comment": "' +
				// document.getElementsByName("comment-text")[0].value + '",
				// "issueId":"1"}',
				data: comment,
				contentType : 'application/json',
				mimeType : 'application/json',
				success : function(data) {
					alert(data.email + " " + data.issueId);
					console.log(comment);
				},
				error : function(data, status, er) {
					console.log(comment);	
					alert("error: " + data + " status: " + status + " er:" + er);
				}
			});
}

