
jQuery(document).ready(function($) {
	sendAjax();
});

function sendAjax() {

	$
			.ajax({
				url : "/Bawl/cont/add-comment",
				type : 'POST',
				dataType : 'json',
				data : '{"email":"bbb5b@vv.cz","login":"122","role_id":0,"avatar":null,"password":"$2a$11$MBy8F2zEL.RvR5yvRFJqEekGoMJpn4q6boxTuJqPt99NhGTJ.kyXu","name":"motir"}',
				contentType : 'application/json',
				mimeType : 'application/json',
				success : function(data) {
					alert(data.id + " " + data.name);
				},
				error : function(data, status, er) {
					alert("error: " + data + " status: " + status + " er:" + er);
				}
			});
}


var CommentModel = Backbone.Model.extend({
	
	  id: 0,
	
	  comment: "",
	
	  userName: "",
		 
	  email: "",
	
	  issueId: ""

});

var CommentsCollection = Backbone.Collection.extend({
	
});

