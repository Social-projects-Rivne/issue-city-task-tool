<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title> Administrator Toolpage</title>
	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/script.js" />"></script>
</head>
<body>

	<div id="background"></div>
	<br />
	<div id="users-table">
		<table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Login</th>
			</tr>
			<c:forEach items="${Users}" var="usr">
				<tr>
					<td><c:out value="${usr.id}"></c:out></td>
					<td><c:out value="${usr.name}"></c:out></td>
					<td><c:out value="${usr.email}"></c:out></td>
					<td><c:out value="${usr.login}"></c:out></td>
					<td><button>Edit</button><button>Delete</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<br />
	<div id="add-user-button">
		<button id="add">Add</button>
	</div>
	
	<div class='wrapper' id="popup">
	<h1>Add User</h1>
		<div class='form-wrapper'>
			<form method="POST" action="">
				<div class='row'>
					<div class='left'><label for "firstname"> UserName: </label></div>
					<div class='right'><input type="text" id="name" name ="name" placeholder="enter your name"></div>
				<br clear='all'>
				</div>
				<div id="name-help" class="helper"></div>
				
				<div class='row'>
					<div class='left'><label for "email"> Email: </label></div>
					<div class='right'><input type="email" id="email" name ="email" placeholder="enter your email"></div>
				<br clear='all'>
				</div>
				<div id="email-help" class="helper"></div>
				
				<div class='row'>
					<div class='left'><label for "login"> Login: </label></div>
					<div class='right'><input type="text" id="login" name ="login" placeholder="enter your login"></div>
				<br clear='all'>
				</div>
				<div id="login-help" class="helper"></div>
				
				<div class='row'>
					<div class='left'><label for "password"> UserPassword: </label></div>
					<div class='right'><input type="password" id="password" name ="password" placeholder="enter your password"></div>
				<br clear='all'>
				</div>
				<div id="password-help" class="helper"></div>
				
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
				<div id="role-help" class="helper"></div>
							
				</div>
					<div class='buttons'>
						<input type="submit" id="add-user-submit" value="add user" class='button'> 
				</div>
				<div id="submit-help" class="helper"></div>
			</form>
		</div>
</div>

</body>
</html>
