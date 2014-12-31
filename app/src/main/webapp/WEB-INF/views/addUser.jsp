<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title> Admin tools page form</title>
	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/script.js" />"></script>
</head>
<body>

<div id="background"></div>
	<div id="popup">
		<form action="" method="POST">
			Login: <input class="add-user-form" type="text" name="login" /><br /><br />
			<span id="login-help" class="help"></span>
			Password: <input class="add-user-form" type="password" name="password" /><br /><br />
			<span id="password-help" class="help"></span>			
			Email: <input class="add-user-form" type="text" name="email" /><br /><br />
			<span id="email-help" class="help"></span>			
			Role: <select class="add-user-form" name="role">
			      	<option>Admin</option>
				<option>Manager</option>
				<option>User</option>
			      </select><br /><br />
			<center><input type="submit" id="add-user-submit" value="Add User" /></center>
			<span id="submit-help" class="help"></span>
		</form>	
	</div>
<div class='wrapper'>
	<h1>Bawl: Admin Toolpage Form</h1>
		<div class='form-wrapper'>
			<form>
				<div class='row'>
					<div class='left'><label for "firstname"> UserName: </label></div>
					<div class='right'><input type="text" id="firstname" name ="firstname" placeholder="enter your name"></div>
				<br clear='all'>
				</div>
				
				<div class='row'>
					<div class='left'><label for "email"> Email: </label></div>
					<div class='right'><input type="email" id="email" name ="email" placeholder="enter your email"></div>
				<br clear='all'>
				</div>
				
				<div class='row'>
					<div class='left'><label for "login"> Login: </label></div>
					<div class='right'><input type="text" id="login" name ="login" placeholder="enter your login"></div>
				<br clear='all'>
				</div>
				
				<div class='row'>
					<div class='left'><label for "password"> UserPassword: </label></div>
					<div class='right'><input type="password" id="password" name ="password" placeholder="enter your password"></div>
				<br clear='all'>
				</div>
				
				<div class='row'>
					<div class='left'><label for "avatar"> Avatar: </label></div>
					<div class='right'><input type="file" value="Browse" name="avatar"></div>
				<br clear='all'>
				</div>
				
				<div class='row'>
					<div class='left'><label for "notification_message"> Notification Message: </label></div>
					<div class='right'><input type="text" id="notification_message" name ="notification_message" placeholder="enter notification here"></div>
				<br clear='all'>
				</div>
				<div class='row'>
					<div class='left'>Role: </div>
					<div class='right-radio'><input type="radio" value="admin" id="admin" name ="role"> Admin <input type="radio" value="manager" id="manager" name ="role"> Manager <input type="radio" value="user" id="user" name ="role"> User </div>
				<br clear='all'>
				
							
				</div>
					
					<div class='buttons'>
						<input type="submit" id="add" 	value="add user" class='button'> 
						<input type="submit" name="edit" value="edit user" class='button'> 
						<input type="submit" name="remove" value="remove user" class='button'>
				</div>
			</form>
		</div>
</div>

<P>  User table
<br>

 ${userName}   ${userMail}   ${userLogin} </P>

</body>
</html>
