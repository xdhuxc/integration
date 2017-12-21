<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<html>
	<head>
		<title>Sign In</title>
	</head>
	<body style="width:100%">
	
	
		<form method="post" action="LoginServlet" style="margin:0 auto;width:60%;margin-top:20%">
			<label style="width:15%;margin-right:5%">User Name</label>
			<input style="width:80%" type="text" name="username"/>
			
			<label style="width:15%;margin-right:6.5%">Password</label>
			<input style="width:80%" type="text" name="username"/>
			
			<label style="width:15%;margin-right:6.5%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<input style="width:80%" type="submit" name="登录"/>
		</form>
		
	</body>
</html>
