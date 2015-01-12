<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=true"></script>
	<script src="<c:url value="/resources/js/map-script.js" />"></script>
	<link href="<c:url value="/resources/css/styles2.css" />" rel="stylesheet">
</head>
<body>
	<div>
		<a href="admin-toolpage">ToolPage</a>
	</div>
	
	<div>
		<div id="map_canvas"></div>
	</div>
</body>
</html>
