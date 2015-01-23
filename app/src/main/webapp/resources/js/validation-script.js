function validate(){
	var submitButton = document.getElementById('add-issue');
	var issueName = document.getElementById('issue-name');
	var issueCategory = document.getElementById('issue-category');
	
	issueName.addEventListener('blur', function(){
		if(!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
			this.value = 'Wrong name!';
			this.style.color = 'red';
		}
	}, false);
	
	issueName.addEventListener('keydown', function(){
			this.style.color = 'black';
	}, false);
	
	issueCategory.addEventListener('blur', function(){
		if(!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(this.value)) {
			this.value = 'Wrong category!';
			this.style.color = 'red';
		}
		else {
			this.style.color = 'black';
		}
	}, false);
	
	issueCategory.addEventListener('keydown', function(){
			this.style.color = 'black';
	}, false);
	
	issueCategory.addEventListener('change', function(){
		submitButton.nextSibling.innerHTML = '';
	}, false);
	
	issueName.addEventListener('change', function(){
		submitButton.nextSibling.innerHTML = '';
	}, false);
	
	submitButton.addEventListener('click', function(event) {
		if(!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issueName.value)) {
			event.preventDefault();
			issueName.value = 'Wrong name!';
			issueName.style.color = 'red';
			this.nextSibling.innerHTML = 'Please fill the form correctly!';
			this.nextSibling.style.color = 'red';
			this.nextSibling.style.textAlign = 'center';
			this.nextSibling.style.marginTop = '10px';
		}
		if(!/^[A-Za-z0-9]+[A-Za-z0-9\s]+[A-Za-z0-9]+$/.test(issueCategory.value)) {
			event.preventDefault();
			issueCategory.value = 'Wrong category!';
			issueCategory.style.color = 'red';
			this.nextSibling.innerHTML = 'Please fill the form correctly!';
			this.nextSibling.style.color = 'red';
			this.nextSibling.style.textAlign = 'center';
			this.nextSibling.style.marginTop = '10px';
		}
	}, false);
}