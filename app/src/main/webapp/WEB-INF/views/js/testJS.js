jQuery(document).ready(function($) {
	sendAjax();
});

function sendNewComment() {
	var comment = new CommentModel();
	sendAjax();
}

function sendAjax() {

	$.ajax({
		url : "/Bawl/cont/testPage",
		type : 'POST',
		dataType : 'json',
		data : '{"email":"' + document.getElementsByName("email")[0].value
				+ '","userName":"'
				+ document.getElementsByName("userName")[0].value
				+ '", "comment": "'
				+ document.getElementsByName("comment-text")[0].value
				+ '", "issueId":"1"}',
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
	defaults : {
		id : 1,

		comment : "He always breaks.",

		userName : "Alex",

		email : "@",

		issueId : "1"
	}

});

var CommentViev = Backbone.View
		.extend({
			initilize : function() {

			},
			tagName : 'div',
			className : 'comment',
			template : _
					.template('<label class="comments_user_name"> <%= userName %> </label><br>'+
							' <label	class="comment_name"> <%= comment %> </label><hr width="100%" size="2">'),

			render : function() {
				// console.log('Render fnction worcking');
				this.$el.html(this.template(this.model.toJSON()));

			}
		});

var comm = new CommentModel();
var cv = new CommentViev({
	model : comm
});
cv.render();
$(document.body).append(cv.el);
$(document.body.getElementsByClassName('comments')[0]).append(cv.el);

