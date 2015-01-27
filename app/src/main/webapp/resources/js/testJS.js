jQuery(document).ready(function($) {
sendAjax();
});

function sendAjax() {

$
.ajax({				url : "/Bawl/cont/testPage",
				type : 'POST',
				dataType : 'json',
				data : '{"email":"bbb5b@vv.cz","userName":"motir", "comment": "adsfasdfasdf ", "issueId":"1"}',
				contentType : 'application/json',
				mimeType : 'application/json',
				success : function(data) {
					alert(data.email + " " + data.issueId );
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