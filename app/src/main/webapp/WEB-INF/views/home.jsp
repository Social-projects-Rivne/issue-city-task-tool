<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.css" rel="stylesheet">
<!-- delete it later or add to project -->
<script src="<c:url value="/resources/js/lib/jquery.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/leaflet.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/ionicons.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/leaflet.awesome-markers.css" />">
<script src="<c:url value="/resources/js/issue-script.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-responsive.min.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	<link href='http://fonts.googleapis.com/css?family=Oleo+Script:400,700|Josefin+Sans|Advent+Pro:400,200,300,500,600' rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="container" id="navbar">
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar-collapse">
						<span class="sr-only"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Bawl</a>
				</div>
				<div class="collapse navbar-collapse" id="navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="#cry-out" id="cry-out">Cry out</a></li>
						</ul>
					<ul class="nav navbar-nav">
						<li><a href="#admin" id="admin" style="  display: none;">Admin</a></li>
						</ul>
					<ul class="nav navbar-nav">
						<li><a href="#filter" id="filter">Filter</a></li>
						</ul>
					<ul class="nav navbar-nav">
						<li><a href="#statistics" id="stat">Statistics</a></li>
						</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a id="signUp" >Sign Up</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a id="login" >Login</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a id="manager" href="#manager"  style="  display: none;">Manager</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="login modal fade" >
	</div>
	<div class="signUp modal fade" >
	</div>
	<div id="container">
	</div>
<!-- Placed at the end of the document so the pages load faster -->
	<script data-main="<c:url value="/resources/js/common.js" />" src="<c:url value="/resources/js/lib/require.js" />"></script>
	<script>
 require(['main']);
 </script>
 <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>