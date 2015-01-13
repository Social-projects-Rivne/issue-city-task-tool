<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=true"></script>
	<script src="<c:url value="/resources/js/map-script.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-2.1.3.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/home-script.js" />" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/validation-script.js" />" type="text/javascript"></script>
	<link href="<c:url value="/resources/css/styles2.css" />" rel="stylesheet">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
</head>
<body>
	<div id="menu">
		<a href="admin-toolpage">ToolPage</a>
	</div>
	
	<div id="map">
		<div id="map_canvas"></div>
	</div>
	
	<div id="add-issue-form" class="container-fluid">
          
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

                  <div class='row'>
                    <div class='left'>
                      <label for "problem name"> Problem name: </label>
                    </div>
                    <div class='right'>
                      <input type="text" id="problem_name" name ="problem_name" placeholder="enter name of problem"><span></span>
                    </div>
                  </div>
                  
                  <div class='row'>
                    <div class='left'>
                      <label for "problem name"> Category name: </label>
                    </div>
                    <div class='right'>
                        <div class="selectWrap">
                          <select id="category_name">
                          <option>Thefts</option>
                          <option>Fires</option>
                          <option>Murders</option>
                          </select>
                        </div>    
                    </div>
                  </div>
                 
                  <div class='row'>
                    <div class='left'>
                      <label for "problem name"> Description: </label>
                    </div>
                    <div class='right'>
                      <input type="text" id="description" name ="description" placeholder="enter description"><span></span>
                    </div>
                  </div>

                  <div class='row'>
                    <div class='left'>
                      <label for "problem name"> Attachments (url): </label>
                    </div>
                    <div class='right'>
                      <input type="text" id="url_attachments" name ="url_attachments" placeholder="enter attachments (url):"><span></span>
                    </div>
                  </div>

                  <div class='row'>
                    <div class='left'>
                      Problem Priority: 
                    </div>
                    <div class='right-radio'>
                      <input type="radio" value="low" id="low" name ="priority"> Low 
                      <input type="radio" value="medium" id="medium" name ="priority"> Medium 
                      <input type="radio" value="high" id="high" name ="priority" checked="checked"> High
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
    </div>
</body>
</html>
