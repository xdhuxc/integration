<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Sign In</title>
	</head>
	<body>
		<form method="post" action="<c:url value="${pageContext.request.contextPath}/LoginServlet"/>">
			User Name : <input type="text" name="username"/> <br/>
			Password :  <input type="text" name="password"/> <br/>
			            <input type="submit" value="Sign In"/>
		</form>
	</body>
</html>
