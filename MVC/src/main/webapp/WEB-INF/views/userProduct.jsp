<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>There will be a lot useful information about product:</h1>

	<table>
		<tr>
			
				<td>${product.productType.name}</td>
				<td>${product.producer.name}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
			
		</tr>



	</table>

	<p align="right">
		<a>Previous page:</a>
	<form action="/user">
		<input type="submit" value="BACK">
	</form>

</body>
</html>