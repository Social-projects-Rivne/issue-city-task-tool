<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<!-- delete it later or add to project -->
<script src="<c:url value="/resources/js/lib/jquery.js" />"></script>

<!-- UNDESCORE.JS -->
<script src="<c:url value="/resources/js/lib/underscore.js" />"
	type="text/javascript"></script>

<!-- BACKBONE.JS -->
<script src="<c:url value="/resources/js/lib/backbone.js" />"
	type="text/javascript"></script>

<script src="<c:url value="/resources/js/main.js" />"></script>

<script src="<c:url value="/resources/js/app/model/CommentModel.js" />"></script>
<script src="<c:url value="/resources/js/app/view/CommentView.js" />"></script>


<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script src="<c:url value="/resources/js/map-script.js" />"></script>
<script src="<c:url value="/resources/js/issue-script.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/home-script.js" />"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/validation-script.js" />"
	type="text/javascript"></script>
<link href="<c:url value="/resources/css/styles2.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link
	href='http://fonts.googleapis.com/css?family=Jura:400,600|Averia+Sans+Libre:400,700'
	rel='stylesheet' type='text/css'>
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
						<li><a href="#" id="cry-out">Cry out</a></li>

					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">Login</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">About</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div>
		<div class="grid" id="add-issue">
			<div class="col-1-3">
				<div class="tabbable">
					<form method="POST" action="add-issue">
						<!-- Only required for left/right tabs -->
						<h4>Add issue</h4>
						<ul class="nav nav-tabs">

							<li class="active" id="tab1-title"><a href="#tab1"
								data-toggle="tab">Point</a></li>

							<li id="tab2-title"><a href="#tab2" data-toggle="tab">Description</a>
							</li>
							<li id="tab3-title"><a href="#tab3" data-toggle="tab">Photo</a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab1">
								<p>Mark the place of issue on the map</p>
								<button class="btn" id="next-to-description">Next</button>
							</div>
							<div class="tab-pane fade" id="tab2">
								<p>
								<div class="form-group">
									<label for="issue-name">Short issue name</label> <input
										type="text" class="form-control" id="issue-name"
										name="issueName" />
								</div>
								<div class="form-group">
									<label for="issue-category">Issue category</label> <input
										type="text" class="form-control" id="issue-category"
										list="categories" name="issueCategory" />
									<datalist id="categories">
										<c:forEach items="${categories}" var="category">
											<option>${category.name}</option>
										</c:forEach>
									</datalist>

								</div>
								<div class="form-group">
									<label for="issue-description">Issue description</label>
									<textarea class="form-control" rows="3" id="issue-description"
										name="issueDescription"></textarea>
								</div>
								<div class="form-group">
									<label for="propose">Resolution</label>
									<textarea class="form-control" rows="3" id="propose"
										name="issueResolution"></textarea>
								</div>
								<button class="btn" id="next-to-photo">Next</button>
							</div>

							<div class="tab-pane fade" id="tab3">
								<p></p>
								<div class="form-group">
									<label for="exampleInputFile">Click to upload file</label> <input
										id="input-1" type="file" class="input-file"
										name="issueAttachments">
									<!-- <input id="fileInput" class="input-file" type="file"> -->
								</div>
								<button type="submit" class="btn btn-default"
									id="add-issue-button">Add</button>
								<div></div>

							</div>
						</div>
					</form>
				</div>
			</div>
		</div>


		<div class="grid" id="issue-details">
			<div class="col-1-3">
				<div class="tabbable">

					<div align=right>
						<button type="submit" class="btn btn-small" id="resolve_button">Resolve</button>
					</div>

					<h4>
						<label id="issue_name">Traffic light doesn't work. </label>
					</h4>

					<label id="issue_description"> Not working traffic lights
						at the intersection of Soborna-Kievskaya. </label>

					<!-- <div class="thumbnail"> -->
					<img src="http://placehold.it/130x70"> <img
						src="http://placehold.it/130x70">
					<!-- </div> -->

					<br>
					<h4>Comments</h4>
					<div class="comments">
					
					
					</div>
					<div class="comment" id="add-comment">
						<h4>Your comment</h4>

						<div>
							<label>Name</label> <input type="text" name="userName">
						</div>

						<div>
							<label>Email</label> <input type="text" name="email">
						</div>

						<div>
							<label>Comment</label>
							<textarea name="comment-text" rows="3"></textarea>
						</div>

						<br>
						<div align="center">
							<button type="submit" class="btn btn-small" id="resolve_button"
								onclick="sendNewComment()">Comment</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="map">
			<div id="map_canvas"></div>
		</div>
	</div>

</body>
</html>