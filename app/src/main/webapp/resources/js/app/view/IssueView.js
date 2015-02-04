define([ 'jquery', 'underscore', 'backbone',
		'text!templates/IssueDetails.html', 'text!templates/AddIssue.html',
		'model/IssueModel', 'collection/CommentCollection', 'model/CommentModel', ], function($, _,
		Backbone, IssueDetailsTemplate, AddIssueTemplate, IssueModel,
		CommentCollection, CommentModel) {

	var IssueView = Backbone.View.extend({

		model : IssueModel,
		//className :'grid',
		el: '#issue-form',

		IssueDetailsTemplate : _.template(IssueDetailsTemplate),
		AddIssueTemplate : _.template(AddIssueTemplate),
		
		events : {
			'click #add_comment_button' : 'addComment',
			'click #cry-out' : 'addIssueForm',
			'click #myForestGump' : 'addIssueForm',
			'click .leaflet-clickable' : 'issueDetailsForm',
		},

		runForest : function(e) {
			alert('I`m runninig! ');
		},
		// TODO: render from collection is incorrect! Remove it later
		render : function() {
			console.log('Render function working');

		},

		//this.model.toJSON())
		issueDetailsForm : function() {
			// clear block with form
			$('.grid #issue-form').innerHTML = '';
			var temp = this.$el;
			this.$el = $("#issue-form");
			this.$el.html(this.IssueDetailsTemplate);
			this.$el = temp;
			console.log('apended');
/*
			var comments = new CommentCollection;
			comments.setID(2);
			console.log('comments created with issue_id: ' + comments.get('id'));
		
			comments.fetch();
			console.log('timer start ');
			setTimeout(function(){alert(3)}, 1000);
			console.log('timer end ');
			comments.render();

		*/
		},
		addIssueForm : function() {
			// clear block with form
			$('.grid #issue-form').innerHTML = '';
			var temp = this.$el;
			this.$el = $("#issue-form");
			this.$el.html(this.AddIssueTemplate);
			this.$el = temp;
			console.log('apended');
		},
		
		addComment : function() {
			console.log('adding comment ' + $(document.getElementsByName('userName')[0]).val());
			var comment = new CommentModel({
				'userName':$(document.getElementsByName('userName')[0]).val(),
				'comment':$(document.getElementsByName('comment-text')[0]).val(),
				'email' : $(document.getElementsByName('email')[0]).val(),
				'issueId' : ""
			});
			
			sendAjax('{"email":"' + document.getElementsByName("email")[0].value +
					'","userName":"' + document.getElementsByName("userName")[0].value  +
					'", "comment": "' + document.getElementsByName("comment-text")[0].value  + 
					'", "issueId":"1"}');
		}
	});

	return IssueView;

});

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
