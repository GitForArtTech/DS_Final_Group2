<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>GoogleSearch</title>
</head>
<body style="background-color:rgb(11,34,64);">
	<img src ="https://scontent-tpe1-1.xx.fbcdn.net/v/t1.0-9/81681097_2690325087722787_9056776148138065920_n.jpg?_nc_cat=109&_nc_ohc=T_5NIYPoloMAQliYMBbVmXUrIOONdu7O1-ZevjtSBCyCVuYZ_ZWJit9AQ&_nc_ht=scontent-tpe1-1.xx&oh=87463f733857e8c35ff1dd2a4cf05f85&oe=5EAF02BB" 
		width="40%" height="40%" style="display:block; margin:auto" /> 
	
<div style="text-align:center; vertical-align:middel;">
	<form action='${requestUri}' method='get'>
		<input type='text' name='keyword' placeholder = 'keyword' style="font-size:24px" />
		<input type='submit' value='submit' style="font-size:24px" />
	</form>
</div>
</body>
</html>