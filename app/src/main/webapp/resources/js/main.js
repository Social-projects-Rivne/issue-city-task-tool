function sendNewComment(){
	
	var comment = new CommentModel({'userName':'Nazar','comment':'comment bla ba l lva'});
	var comm = "Comment bla bla";
	//comment.set("comment", comm);

	//comment.set("userName", "Nazar");
	var commentView = new CommentViev({
		model : comment
	});
	commentView.render();
	$(document.body.getElementsByClassName('comments')[0]).append(commentView.el);
	console.log(comment.toJSON());
	sendAjax();
}


function sendAjax() {

	$
			.ajax({
				url : "/Bawl/add-comment",
				type : 'POST',
				dataType : 'json',
				data : '{"email":"' + document.getElementsByName("email")[0].value + '","userName":"' + document.getElementsByName("userName")[0].value  + '", "comment": "' + document.getElementsByName("comment-text")[0].value  + '", "issueId":"1"}',
				contentType : 'application/json',
				mimeType : 'application/json',
				success : function(data) {
					alert(data.email + " " + data.issueId);
				},
				error : function(data, status, er) {
					alert("error: " + data + " status: " + status + " er:" + er);
				}
			});
}


var CommentsCollection = Backbone.Collection.extend({

});