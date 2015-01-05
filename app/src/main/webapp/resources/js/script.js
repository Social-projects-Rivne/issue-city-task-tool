window.onload = function(){
	button = document.getElementById('add');
	div = document.getElementById('popup');
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
	
	button.addEventListener('click', function(e){
		e.preventDefault();
		div.style.display = "block"; 
		bg.style.display = "block";
		bg.style.zIndex = "4";
	}, false);
	
	bg.addEventListener('click', function(){
		div.style.display = "none";
		bg.style.display = "none";
		bg.style.zIndex = "1";
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
}
