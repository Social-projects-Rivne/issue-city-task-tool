function getIssue(id) {
	json = {"id": id};
	$.ajax({
		url: 'get-issue',
		type: 'POST',
		contentType: 'application/json',
		mimeType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(json),
		success: function(data) {
			alert(data.id);
		}
	});
}