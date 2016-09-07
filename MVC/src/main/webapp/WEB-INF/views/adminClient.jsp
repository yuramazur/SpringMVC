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
	<h1>Client administration:</h1>

	<table border="1">
		<tr align="center">
			<td colspan="3">Client List:</td>
		</tr>

		<c:if test="${size==0}">
			<tr>
				<td colspan="3"><font color="red" size="15"> List is
						empty!</font></td>
			</tr>
		</c:if>
		<c:forEach items="${clients}" var="client">
			<tr>
				<td>${client.name.names}</td>
				<td>${client.lastName}</td>
				<td>${client.phone}</td>
				<th><a class="a1" href="/admin/client/delete/${client.id}">delete</a>

				</th>
			</tr>

		</c:forEach>

	</table>


	<form name="returnToAdmin" action="/admin">
		<p align="right">
			<b>Return to administrator menu:</b> <input type="submit"
				value="Return">
		</p>

	</form>


</body>
</html>