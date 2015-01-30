require([
        'jquery',
        'underscore',
        'backbone',
        'model/CommentModel',
        'view/CommentView',
        'collection/CommentCollection'
        ]
, function($,_,Backbone,CommentModel,CommentView,CommentCollection) {
	
	var comments = null;
	
	jQuery(document).ready(function($) {
		console.log('comments initialization');
		comments = new CommentCollection({'issueId':'1'});
		comments.initialize();
		console.log('comments  init done');
		comments.fetch();
		console.log('comments fetched');
		console.log('comments render');
		global = comments;
		console.log('comments render done');
	});

	function onMarkerClick(e) {

			
		if(!issueDetails.style.display) {
			issueDetails.style.display = 'block';

			comments.render();
			$('#issue_name').text(issueList[this.title - 1].name);
			$('#issue_description').text(issueList[this.title - 1].description);
			
	
		}
		else
			issueDetails.style.display = '';
	}

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
		
		
		// add comment to collection
		comments.add(comment);
		comments.each(function(obj,index){
			var commV = new CommentView({model:obj}); 
			commV.render(); 
			console.log(obj.toJSON()); 
			$(document.body.getElementsByClassName('comments')[0]).append(commV.el);
	});
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

var global;