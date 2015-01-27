jQuery(document).ready(function($) {
	sendAjax();
});

function sendAjax() {

	$
			.ajax({
				url : "/Bawl/cont/testPage",
				type : 'POST',
				dataType : 'json',
				data : '{"name":""}',
				contentType : 'application/json ; charset=utf-8',
				success : function(data) {
					alert(data + " " + data.userName);
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

var CommentView = Backbone.View.extend({
	tagName : 'li',
	className : 'comment',
	id : 'add-comment'

});

var CommentsCollection = Backbone.Collection.extend({

});

var commentModel = new CommentModel();
var commentView = new CommentView({
	model : commentModel
});