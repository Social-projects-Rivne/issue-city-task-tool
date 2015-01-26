<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<c:url value="/resources/js/testJS.js" />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" >
$(document).ready(function(){
  sendAjax();
});
 
function sendAjax() {
 
$.ajax({ 
    url: "/Bawl/cont/user", 
    type: 'POST', 
    dataType: 'json', 
    data: "{\"name\":\"hmkcode\",\"id\":2}", 
    contentType: 'application/json',
    mimeType: 'application/json',
    success: function(data) { 
        alert(data.id + " " + data.name);
    },
    error:function(data,status,er) { 
        alert("error: "+data+" status: "+status+" er:"+er);
    }
});
}
</script>
</body>
</html>