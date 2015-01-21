<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=true"></script>
	<script src="<c:url value="/resources/js/map-script.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/home-script.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/validation-script.js" />" type="text/javascript"></script>
	<link href="<c:url value="/resources/css/styles2.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
</head>
<body>
<!-- 	<div id="menu"> -->
<!-- 		<a href="admin-toolpage">ToolPage</a> -->


<!-- <div class="container" id="navbar"> -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">

                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                        <span class="sr-only"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Bawl</a>
                </div>

                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="#">Cry out</a>
                        </li>

                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Login</a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">About</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
<!--     </div> -->



	
	
	<div id="map">
		<div id="map_canvas"></div>
	</div>
	
	<form id="add-issue-form" class="container-fluid" method="POST" action="add-issue">
          
          <!-- Header -->
          <div class="row-fluid" id="header">
            <div class="span12">
            <h4>Bawl. User-> new issue form</h4>
            </div>
          </div>
          
          <!-- Content   -->
          <!-- Content.Sidebar -->
          <div class="row-fluid" id="content">
            <div class="span3 sidebar">
            <!-- User image -->
            <img class="img-polaroid" src="<c:url value="/resources/img/avatar.png" />" width="120" height="120" />
            <!-- Buttons -->
            <button class="btn" type="submit">Fetch all issues</button><br>
            <button class="btn" type="submit">Add new issue..</button><br>
            <button class="btn" type="submit">..</button><br>
            <button class="btn" type="submit">..</button><br>
          <!-- Content. General Content Area     -->
            </div>
            <div class="span9 text"> 
                <br>

				  <input type="hidden" name="mapPointer" id="mapPointer" />	
					
                  <div class='row'>
                    <div class='left'>
                      <label> Problem name: </label>
                    </div>
                    <div class='right'>
                      <input type="text" id="problem_name" name ="name" placeholder="enter name of problem"><span></span>
                    </div>
                  </div>
                  
                  <div class='row'>
                    <div class='left'>
                      <label"> Category name: </label>
                    </div>
                    <div class='right'>
                        <div class="selectWrap">
                          <select id="category_name" name="category">
	                          <option>Thefts</option>
	                          <option>Fires</option>
	                          <option>Murders</option>
                          </select>
                        </div>    
                    </div>
                  </div>
                 
                  <div class='row'>
                    <div class='left'>
                      <label> Description: </label>
                    </div>
                    <div class='right'>
                      <input type="text" id="description" name ="description" placeholder="enter description"><span></span>
                    </div>
                  </div>

                  <div class='row'>
                    <div class='left'>
                      <label> Attachments (url): </label>
                    </div>
                    <div class='right'>
                      <input type="text" id="url_attachments" name ="attachments" placeholder="enter attachments (url):"><span></span>
                    </div>
                  </div>

                  <div class='row'>
                    <div class='left'>
                      Problem Priority: 
                    </div>
                    <div class='right-radio'>
                      <input type="radio" value="1" id="low" name ="priorityId"> Low 
                      <input type="radio" value="2" id="medium" name ="priorityId"> Medium 
                      <input type="radio" value="3" id="high" name ="priorityId" checked="checked"> High
                    </div>
                  </div>
            </div>
          </div>

          <!-- Footer -->
          <div class="row-fluid" id="footer">
            <div class="span12">
            rv-009
            </div>
          </div>
    </form>
    
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="resources/js/jquery-1.11.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/js/bootstrap.min.js"></script>
    
    
</body>
</html>