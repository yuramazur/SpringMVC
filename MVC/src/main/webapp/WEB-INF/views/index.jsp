<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Best Bikes!</title>
</head>
<body>
	
	<security:authorize access="!isAuthenticated()">
		<a href="/registration">Register</a>
	</security:authorize>
	<security:authorize
		access="isAuthenticated() and hasRole('ROLE_ADMIN')">
		<a href="/admin">Admin panel</a>
	</security:authorize>
	
	<p><a href="/user">Products</a></p>
	
	<div class="container">
	<img class="img-thumbnail" width="90%" src="/resources/image/index.jpg?version=1" />
	</div>

</body>
</html>