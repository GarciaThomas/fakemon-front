<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
$(document).ready(function(){
	$("#btnTest").click(test)
	function test(){
		$.ajax({
			type:"POST",
			url:'${pageContext.request.contextPath}/verif',
			success: function(msg){
				console.log("ici")
				$("#divTest").text(${sessionScope.msg});
			}
		});
	}
});
</script>
</head>
<body>
	<div id="divTest">test</div>
	<button id="btnTest">tester</button>
</body>
</html>