<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>

</head>
<body
	style="background: linear-gradient(to right, rgb(11, 34, 64), white); color: white; font-family: Suez One; font-size: 20px;">
	<%
		String kw1 = request.getParameter("keyword");
	%>
	<style type="text/css">
#sitebody {
	width: auto;
	margin: 0 auto;
}

#header {
	height: 80px;
	text-align: left;
	line-height: 80px;
	font-size: 25px;
}

#sidebar_left {
	text-align: center;
	float: left;
}
#sidebar_right {
	text-align: center;
	float: right;
}
#content {
	margin-left: 175px;
	margin-right: 175px;
	text-align: left;
}

#footer {
	clear: both;
	text-align: center;
}
</style>
	<div id="sitebody">
		<div id="header">
			<form action='${requestUri}' method='get'>
				<img src="https://images.yappy.com/yappicon/samoyed/samoyed-01.png"
					width="50" height="50" /> GOOPETS <input type='text'
					name='keyword' placeholder='<%=kw1%>' style="font-size: 20px" /> <input
					type='submit' value='submit' style="font-size: 20px" />
			</form>
			<br>
		</div>
		<div id="sidebar_left"></div>
		<div id="sidebar_right"></div>
		<div id="content">
			<%
		String[][] orderList = (String[][]) request.getAttribute("query");
		for (int i = 0; i < orderList.length; i++) {
	%>
			<a href='<%=orderList[i][1]%>'
				style="color: rgb(255, 0, 128); font-family: Microsoft JhengHei; font-size: 20px;"><%=orderList[i][0]%></a>
			<br>
			<h style="font-size:5px ;color:white;font-family:monospace;"><%=orderList[i][1]%></h>
			<br> <br>
			<%
		}
	%>
		</div>
		<div id="footer"><img src="https://media2.giphy.com/media/AgO9VR2a9KW1MSP73I/giphy.gif"
					width="50" height="50" /></div>
	</div>



</body>
</html>