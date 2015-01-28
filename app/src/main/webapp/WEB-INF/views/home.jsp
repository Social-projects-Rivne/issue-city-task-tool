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



<script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
<script src="<c:url value="/resources/js/leaflet.awesome-markers.js" />"></script>
<script src="<c:url value="/resources/js/leaflet.awesome-markers.min.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome-4.3.0/css/font-awesome.min.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/leaflet.awesome-markers.css" />">
<link rel="stylesheet" href="http://leafletjs.com/dist/leaflet.css">



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
									id="add-issue-button">Add</button><div></div>

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

		<div id="map" style="height: 100%; position: relative;" class="leaflet-container leaflet-fade-anim" tabindex="0"><div class="leaflet-map-pane" style="transform: translate3d(0px, 0px, 0px);">
		<div id="div22" style=" width: 10%; height: 100%; position: relative; margin-left:1px; border:2px solid grey; background-color: #f3f3f3; color: #494949; border: 2px solid; border-radius: 25px;">
		<div class="leaflet-tile-pane">
			<div class="leaflet-layer">
				<div class="leaflet-tile-container"></div>
			<!--	<div class="leaflet-tile-container leaflet-zoom-animated"><img class="leaflet-tile leaflet-tile-loaded" style="height: 256px; width: 256px; left: 56px; top: -91px;" src="./Leaflet Quick Start Guide Example_files/2723.png"><img class="leaflet-tile leaflet-tile-loaded" style="height: 256px; width: 256px; left: 312px; top: -91px;" src="./Leaflet Quick Start Guide Example_files/2723(1).png"><img class="leaflet-tile leaflet-tile-loaded" style="height: 256px; width: 256px; left: 56px; top: 165px;" src="./Leaflet Quick Start Guide Example_files/2724.png"><img class="leaflet-tile leaflet-tile-loaded" style="height: 256px; width: 256px; left: 312px; top: 165px;" src="./Leaflet Quick Start Guide Example_files/2724(1).png"><img class="leaflet-tile leaflet-tile-loaded" style="height: 256px; width: 256px; left: -200px; top: -91px;" src="./Leaflet Quick Start Guide Example_files/2723(2).png"><img class="leaflet-tile leaflet-tile-loaded" style="height: 256px; width: 256px; left: 568px; top: -91px;" src="./Leaflet Quick Start Guide Example_files/2723(3).png"><img class="leaflet-tile leaflet-tile-loaded" style="height: 256px; width: 256px; left: -200px; top: 165px;" src="./Leaflet Quick Start Guide Example_files/2724(2).png"><img class="leaflet-tile leaflet-tile-loaded" style="height: 256px; width: 256px; left: 568px; top: 165px;" src="./Leaflet Quick Start Guide Example_files/2724(3).png">
			-->	</div>
  		</div>
	 </div>
				<div class="leaflet-objects-pane">
					<div class="leaflet-shadow-pane"><img src="./Leaflet Quick Start Guide Example_files/marker-shadow.png" class="leaflet-marker-shadow leaflet-zoom-animated" style="margin-left: -12px; margin-top: -41px; width: 41px; height: 41px; transform: translate3d(300px, 247px, 0px);">
					</div>
					<div class="leaflet-overlay-pane"><svg class="leaflet-zoom-animated" width="1200" height="800" viewBox="-300 -200 1200 800" style="transform: translate3d(-300px, -200px, 0px);"><g><path stroke-linejoin="round" stroke-linecap="round" fill-rule="evenodd" stroke="red" stroke-opacity="0.5" stroke-width="5" fill="#f03" fill-opacity="0.5" class="leaflet-clickable" d="M183,130A42,42,0,1,1,182.9,130 z">
					</path></g>
					<g><path stroke-linejoin="round" stroke-linecap="round" fill-rule="evenodd" stroke="#0033ff" stroke-opacity="0.5" stroke-width="5" fill="#0033ff" fill-opacity="0.2" class="leaflet-clickable" d="M358 163L474 219L550 153z">
					</path></g></svg></div>
					<div class="leaflet-marker-pane"><img src="./Leaflet Quick Start Guide Example_files/marker-icon.png" class="leaflet-marker-icon leaflet-zoom-animated leaflet-clickable" tabindex="0" style="margin-left: -12px; margin-top: -41px; width: 25px; height: 41px; transform: translate3d(300px, 247px, 0px); z-index: 247;"></div>
						<div class="leaflet-popup-pane">
							<div class="leaflet-popup  leaflet-zoom-animated" style="opacity: 1; transform: translate3d(300px, 247px, 0px); bottom: 27px; left: -56px;"><a class="leaflet-popup-close-button" href="http://leafletjs.com/examples/quick-start-example.html#close"></a>
								<div class="leaflet-popup-content-wrapper">
									<div class="leaflet-popup-content" style="width: 74px;"><b>Hello world!</b><br>I am a popup.</div>
							 </div>
									<div class="leaflet-popup-tip-container">
										<div class="leaflet-popup-tip"></div>
								 </div>
						 </div>
						</div>
				</div>
									</div>
									<div class="leaflet-control-container">
										<div class="leaflet-top leaflet-left">
											<div class="leaflet-control-zoom leaflet-bar leaflet-control"><a class="leaflet-control-zoom-in" href="http://leafletjs.com/examples/quick-start-example.html#" title="Zoom in">+</a><a class="leaflet-control-zoom-out" href="http://leafletjs.com/examples/quick-start-example.html#" title="Zoom out">-</a></div>
											</div>
											<div class="leaflet-top leaflet-right"></div>
											<div class="leaflet-bottom leaflet-left"></div>
											</div>
											</div>
	</div>

</body>
</html>