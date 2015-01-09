window.onload = function(){
	addButton = document.getElementById('add');
	addPopup = document.getElementById('add-popup');
	editButtons = document.getElementsByName('edit');
	editPopup = document.getElementById('edit-popup');
	bg = document.getElementById('background');
	login = document.getElementsByName('login');
	password = document.getElementsByName('password');
	email = document.getElementsByName('email');
	role = document.getElementsByName('role');
	lh = document.getElementById('login-help');
	ph = document.getElementById('password-help');
	eh = document.getElementById('email-help');
	rh = document.getElementById('role-help');
	sh = document.getElementById('submit-help');
	aus = document.getElementById('add-user-submit');
	eus = document.getElementById('edit-user-submit');
	notWnd = document.getElementById('notification-window');
	notMsg= document.getElementById('notification-message');
	
	for(var i = 0; i < editButtons.length; i++) {
		editButtons[i].addEventListener('click', function(e){
			e.preventDefault();
			editPopup.style.display = "block"; 
			bg.style.display = "block";
			bg.style.zIndex = "4";
		}, false);
	};
	
	addButton.addEventListener('click', function(e){
		e.preventDefault();
		addPopup.style.display = "block"; 
		bg.style.display = "block";
		bg.style.zIndex = "4";
	}, false);
	
	bg.addEventListener('click', function(){
		addPopup.style.display = "none";
		editPopup.style.display = "none";
		bg.style.display = "none";
		bg.style.zIndex = "1";
		notMsg.innerHTML = "";
		notWnd.style.display = "none";
	}, false);

	login[0].addEventListener('blur', function(){
		if(!/^\w+$/.test(login[0].value)) lh.innerHTML = "Wrong Login!";
		else lh.innerHTML = "";
	}, false);

	password[0].addEventListener('blur', function(){
		if(!/^.+$/.test(password[0].value)) ph.innerHTML = "Wrong Password!";
		else ph.innerHTML = "";
	}, false);

	email[0].addEventListener('blur', function(){
		if(!/^[a-z0-9_.]+\@[a-z0-9]+\.[a-z0-9]+$/.test(email[0].value)) eh.innerHTML = "Wrong Email!";
		else eh.innerHTML = "";
	}, false);
	

	aus.addEventListener('click', function(event){
		if((!/^\w+$/.test(login[0].value)) || (!/^.+$/.test(password[0].value)) || (!/^[a-z0-9_.]+\@[a-z0-9]+\.[a-z0-9]+$/.test(email[0].value))){
			event.preventDefault();
			sh.innerHTML = "Please, fill the form!";
		}
	}, false);
	
	
	if(location.search){
		var str = location.search.substr(1);
		var pos = str.indexOf('=');
		var value = str.substr(pos + 1);
		value = decodeURIComponent(value);
		arr = value.split('+');
		var message = "";
		for(var elem in arr){
			message += arr[elem] + ' ';
		}
		notMsg.innerHTML = message;
	}
	
	if(notMsg.innerHTML){
		notWnd.style.display = "block";
		bg.style.display = "block";
		bg.style.zIndex = "4";
	}
}
