function sendNewComment(){
	var comment = new CommentModel();
	sendAjax();
}


function sendAjax() {

	$
			.ajax({
				url : "/Bawl/cont/testPage",
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

var CommentModel = Backbone.Model.extend({

	id : 0,

	comment : "",

	userName : "",

	email : "",

	issueId : ""

});

var CommentsCollection = Backbone.Collection.extend({

});