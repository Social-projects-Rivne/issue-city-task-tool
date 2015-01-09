window.onload = function(){
	var problemName = document.getElementById('problem_name');
	var description = document.getElementById('description');
	var urlAttachments = document.getElementById('url_attachments');
	
	problemName.addEventListener('blur', function(){
		if(!/^\w+$/.test(this.value))
			this.nextSibling.innerHTML = 'Wrong name!';
		else
			this.nextSibling.innerHTML = '';
	}, false);
	
	description.addEventListener('blur', function(){
		if(!/^\w+$/.test(this.value))
			this.nextSibling.innerHTML = 'Wrong description!';
		else
			this.nextSibling.innerHTML = '';
	}, false);
	
	urlAttachments.addEventListener('blur', function(){
		if(!/^\w+\.[A-Za-z0-9_.-]+\.[A-Za-z]+$/.test(this.value))
			this.nextSibling.innerHTML = 'Wrong URL!';
		else
			this.nextSibling.innerHTML = '';
	}, false);
}